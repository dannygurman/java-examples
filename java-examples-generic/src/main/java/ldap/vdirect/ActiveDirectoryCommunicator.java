package ldap.vdirect;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import org.apache.commons.lang.StringUtils;



public class ActiveDirectoryCommunicator {
	protected Hashtable<String,String> authEnvironment;
	final protected String[] searchBases;
	final protected String nameAttribute;
	final protected String userObjectClass;
	final protected String groupObjectClass;
	final protected String fqDomainName;
	final public static String DN_ATTRIBUTE = "distinguishedName";
	
	public ActiveDirectoryCommunicator(String serverURL, boolean validateCert, boolean validateHostName,
			String truststorePath, char[] truststorePassword, String[] searchBases, String fqDomainName, 
			String nameAttribute, String userObjectClass, 
			String groupObjectClass){
		authEnvironment = new Hashtable<String,String>();
		this.searchBases = searchBases;
		this.nameAttribute = nameAttribute;
		this.fqDomainName = fqDomainName;
		this.userObjectClass = userObjectClass;
		this.groupObjectClass = groupObjectClass;
		if(isEncryptedURL(serverURL)){	
			if (validateCert) {
				// if not validating hostname, default java.naming.ldap.factory.socket will work fine
				if (validateHostName){
					// truststorePath must point to java keystore that contains cert from MSAD trust chain 
					VerifyCertAndHostTLSSocketFactory.init(truststorePath, truststorePassword);
					authEnvironment.put("java.naming.ldap.factory.socket", VerifyCertAndHostTLSSocketFactory.class.getName());
				} else {
					VerifyCertNotHostTLSSocketFactory.init(truststorePath, truststorePassword);
					authEnvironment.put("java.naming.ldap.factory.socket", VerifyCertNotHostTLSSocketFactory.class.getName());
				}
			} else {
				authEnvironment.put("java.naming.ldap.factory.socket", IgnoreCert_SSLSocketFactory.class.getName());
			}
		}
		authEnvironment.put(Context.PROVIDER_URL, serverURL);
		authEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		authEnvironment.put(Context.SECURITY_AUTHENTICATION,"simple");
		authEnvironment.put(Context.REFERRAL, "follow");//http://stackoverflow.com/questions/5505351/jndi-with-active-directory-partialresultexception	
		// used when getting groups for user
		authEnvironment.put("java.naming.ldap.attributes.binary","tokenGroups");
	}
	
	public static boolean isEncryptedURL(String url){
		return url.toUpperCase().startsWith("LDAPS");
	}
	
	@SuppressWarnings("unchecked")
	public DirContext makeConnection(String authName, String password) throws NamingException{
		Hashtable<String,String> environment = (Hashtable<String,String>)authEnvironment.clone();
		environment.put(Context.SECURITY_PRINCIPAL, authName);
		environment.put(Context.SECURITY_CREDENTIALS, password); 
        return new InitialLdapContext(environment, null);		
	}
	
	//returns null if can't find username
	//throws NamingException if can't login
	public String getDNForUser(DirContext ctx, String name) throws NamingException, LdapAuthException {
		for (String searchBase : this.searchBases){
			SearchControls searchCtls = new SearchControls();
			String returnedAtts[]={DN_ATTRIBUTE};
			searchCtls.setReturningAttributes(returnedAtts);
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String searchFilter = "(&(objectClass=user)(" + this.nameAttribute + "=" + name + "))";
			NamingEnumeration<SearchResult> answer;
			try {
				answer = ctx.search(searchBase, searchFilter, searchCtls);
			} catch (NamingException e) {
				throw new LdapAuthException(String.format("Configuration error, problem with search base: %1$s",
						searchBase), e);
			}
			if(answer == null || !answer.hasMoreElements()){
				continue;
			}
		    SearchResult sr = answer.next();
			Attributes attrs = sr.getAttributes();
			if(null == attrs){
				continue;
			}
			return attrs.get(DN_ATTRIBUTE).get().toString();
		}
        throw new LdapAuthException(String.format("Could not find DN for user \"%1$s\" from search bases: %2$s",
            name, StringUtils.join(this.searchBases, ":")));
	}
	
	protected Set<String> list(String name, String password, String returnAttribute, String objectClass) throws NamingException{
		Set<String> users = new HashSet<String>();
		String returnedAtts[]={returnAttribute};
		SearchControls searchCtls = new SearchControls();
		searchCtls.setReturningAttributes(returnedAtts);
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		String searchFilter = "(&(objectClass=" + objectClass + "))";
		for (String searchBase : this.searchBases){
			DirContext ctx =  makeConnection(toAuthName(name), password);
			NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, searchCtls);
			while(answer.hasMore()){
			    SearchResult sr = answer.next();
				Attributes attrs = sr.getAttributes();
				if(null == attrs){
					continue;
				}
				String dn = attrs.get(returnAttribute).get().toString();
				users.add(dn);
			}
			ctx.close();
		}
		return users;		
	}
	
	public Set<String> listUsers(String name, String password) throws NamingException{
		return list(name, password, nameAttribute, userObjectClass);
	}	
	
	public Set<String> listGroups(String name, String password) throws NamingException{
		return list(name, password, nameAttribute, groupObjectClass);
	}	
	
	public final String toAuthName(String name){
		return name + "@" + fqDomainName;
	}
	
	/**
	 * 
	 * @param name string corresponding to constructor's nameAttribute of user 
	 * @param password sAMAccountName password
	 * @return a set of groups that user belongs to
	 * @throws LdapAuthException If authentication fails
	 */
	public Set<String> authenticate(String name, String password) throws LdapAuthException {
		try{
			DirContext ctx = makeConnection(toAuthName(name), password);
			Set<String> groups = getGroupsForUser(ctx, name);
			ctx.close();
			return groups;
		}catch(NamingException ne){
			throw new LdapAuthException("Login failed");
		}
	}
	
    public static String getDefaultNamingContext(String url, String username, String password, String fqDomainName) {
        Hashtable<String,String> env = new Hashtable<String,String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, url);
		env.put(Context.SECURITY_AUTHENTICATION,"simple");
		env.put("java.naming.ldap.factory.socket", IgnoreCert_SSLSocketFactory.class.getName());
        env.put(Context.REFERRAL, "follow");
        env.put(Context.SECURITY_PRINCIPAL, username + "@" + fqDomainName);
        env.put(Context.SECURITY_CREDENTIALS, password); 
        try {
             LdapContext context = new InitialLdapContext(env, null);
             SearchControls controls = new SearchControls();
             controls.setSearchScope(SearchControls.OBJECT_SCOPE);
             String returnedAtts[]={"defaultNamingContext"};
             controls.setReturningAttributes(returnedAtts);
             NamingEnumeration<SearchResult> answer = context.search("", "(objectclass=*)", controls);
              while (answer.hasMoreElements()) {
                  SearchResult sr = answer.next();
                  Attributes attrs = sr.getAttributes();
                  if (attrs != null) {
                       try {
                            return attrs.get("defaultNamingContext").get().toString();
                       }
                       catch (NullPointerException e)     {
                       }
                  }
             }
        } 
        catch (NamingException e) {}
        return null;
    }	
    
	public Set<String> getGroupsForUser(DirContext dirContext, String username) throws LdapAuthException {
		try {
			SearchControls userSearchCtls = new SearchControls();
			userSearchCtls.setSearchScope(SearchControls.OBJECT_SCOPE);
			String userSearchFilter = "(objectClass=" + userObjectClass + ")";
	        StringBuffer groupsSearchFilter = new StringBuffer();
	        groupsSearchFilter.append("(|");
			String userSearchBase = getDNForUser(dirContext, username);
			String userReturnedAtts[]={"tokenGroups"};
			userSearchCtls.setReturningAttributes(userReturnedAtts);
			NamingEnumeration<SearchResult> userAnswer = dirContext.search(userSearchBase, userSearchFilter, userSearchCtls);
			while (userAnswer.hasMoreElements()) {
				SearchResult sr = (SearchResult)userAnswer.next();
				Attributes attrs = sr.getAttributes();                                     
				if (attrs != null) {
					NamingEnumeration<?> ae = attrs.getAll();
					while (ae.hasMore()) {
						Attribute attr = (Attribute)ae.next();
						NamingEnumeration<?> e = attr.getAll();
						while (e.hasMore()) {                                                                              
                            byte[] sid = (byte[])e.next();
                            groupsSearchFilter.append("(objectSid=" + binarySidToStringSid(sid) + ")");
						}
                        groupsSearchFilter.append(")");
					}
				}
			}	
			SearchControls groupsSearchCtls = new SearchControls();
			groupsSearchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);                       
			String groupsReturnedAtts[]={nameAttribute};
			groupsSearchCtls.setReturningAttributes(groupsReturnedAtts);
			Set<String> groupsForUser = new HashSet<String>();
			for (String searchBase : this.searchBases){
				NamingEnumeration<SearchResult> groupsAnswer = dirContext.search(searchBase, groupsSearchFilter.toString(), groupsSearchCtls);
				while (groupsAnswer.hasMoreElements()) {          
					SearchResult sr = (SearchResult)groupsAnswer.next();
					Attributes attrs = sr.getAttributes();                                             
					if (attrs != null) {
						groupsForUser.add(attrs.get(nameAttribute).get().toString());
					}
				}  
			}
			return groupsForUser;
		} catch (NamingException e) {
			throw new LdapAuthException("Active Directory over LDAP Configuration error", e);
		}
	}

	private static final String binarySidToStringSid(byte[] SID) {
		//convert the SID into string format
		String strSID = "S-" + Long.toString(SID[0]);
		long authority = SID[4];
		long count;
		long rid;
		for (int i = 0; i < 4; i++) {
			authority <<= 8;
			authority += SID[4+i] & 0xFF;
		}
		strSID += "-" + Long.toString(authority);
		count = SID[2];
		count <<= 8;
		count += SID[1] & 0xFF;
		for (int j = 0; j < count; j++) {
			rid = SID[11 + (j * 4)] & 0xFF;
			for (int k = 1; k < 4 ; k++) {
				rid <<= 8;
				rid += SID[11 - k + (j * 4)] & 0xFF;
			}
			strSID += "-" + Long.toString(rid);
		}   
		return strSID;    	
	}    
    
	
	public static void main(String args[]) throws Exception{
		String keystorePath = "./testTruststore.jks";
		char[] keystorePass = "testtest".toCharArray();
		//Certificate[] certs = KeystoreTools.getCertificates("https://192.168.50.25:636");
		//KeystoreTools.putCertIntoKeystore(keystorePath, keystorePass, "certAlias", certs[0]);
		//String[] searchBases = new String[] {"CN=Users,DC=ADTest,DC=nc,DC=radware,DC=com", "CN=TestDistributionGroup,CN=Users,DC=ADTest,DC=nc,DC=radware,DC=com"};
		String[] searchBases = new String[] {"CN=Users,DC=ADTest,DC=nc,DC=radware,DC=com"};
		ActiveDirectoryCommunicator adc = new ActiveDirectoryCommunicator("ldaps://dc1.ADTest.nc.radware.com:636", 
				true,
				false,
				keystorePath, 
				keystorePass, 
				searchBases,
				"ADTest.nc.radware.com",
                "sAMAccountName",
                "user",
                "group");
		System.out.println("JoeActiveDirectory" + ":  " + adc.authenticate("JoeActiveDirectory", "JoeActiveDirectory"));
		try {
			System.out.println(adc.authenticate("testuser1", "testuserbad"));
			System.out.println("Failed, should not have authenticated");
		}catch (Exception e){
			System.out.println("Correct- not authenticate");
		}
		System.out.println(adc.authenticate("testusera", "testusera"));
		try {
			System.out.println(adc.authenticate("testusera", "testuserbad"));
			System.out.println("Failed, should not have authenticated");
		}catch (Exception e){
			System.out.println("Correct- not authenticate");
		}			
		System.out.println("Above was important");
		//System.out.println(getDefaultNamingContext(String url, String username, String password, String fqDomainName));
		//System.out.println(adc.getDNForUser("testusera", "testusera"));
		Set<String> users = adc.listUsers("domainuser", "domainuser");
		System.out.println("Users*****:");
		for (String user : users){
			System.out.println(user);
		}
		Set<String> groups = adc.listGroups("testuser1", "testuser1");
		System.out.println("Groups*****:");
		for (String group : groups){
			System.out.println(group);
		}
		
		String[] searchBases2 = new String[] {"CN=Configuration,DC=ADTest,DC=nc,DC=radware,DC=com"};
		ActiveDirectoryCommunicator adc2 = new ActiveDirectoryCommunicator("ldaps://dc1.ADTest.nc.radware.com:636", 
				true,
				false,
				keystorePath, 
				keystorePass, 
				searchBases2,
				"ADTest.nc.radware.com",
                "sAMAccountName",
                "user",
                "group");
		try {
			System.out.println("JoeActiveDirectory" + ":  " + adc2.authenticate("JoeActiveDirectory", "JoeActiveDirectory"));
			System.out.println("**Failure, should not have authenticated");
		} catch (LdapAuthException e){
			System.out.println("Threw Exception, correct");
			e.printStackTrace();
		}
	}
}
