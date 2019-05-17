package ldap.vdirect;

import java.io.File;
import java.util.Set;

//import com.radware.alteon.api.AdcAuthException;
//import com.radware.alteon.api.VDirectUser;
//import com.radware.alteon.api.impl.access.AbstractAccessImpl;
//import com.radware.vdirect.client.VDirectConfiguration;

public class LdapAccessImpl { //extends AbstractAccessImpl{
	/*
	ActiveDirectoryCommunicator adc;
	public LdapAccessImpl (String name, VDirectConfiguration config){
		super(name, config);
		String serverURL = config.getProperty(VDirectConfiguration.AuthADServiceURLProperty); 
		String fqDomainName = config.getProperty(VDirectConfiguration.AuthADServiceFQDomainName);
		boolean validateCert = config.getProperty(VDirectConfiguration.AuthADServiceValidateCertProperty, "False").toUpperCase().startsWith("T");
		boolean verifyHost = config.getProperty(VDirectConfiguration.AuthADServiceVerifyHostProperty, "False").toUpperCase().startsWith("T");
		String[] searchBases = config.getProperty(VDirectConfiguration.AuthADServiceSearchBasesProperty, "").split(":");
		if (searchBases == null || searchBases.length == 0) {
			searchBases = new String[]{""};
		}
		String nameAttribute = config.getProperty(VDirectConfiguration.AuthADServiceNameAttributeProperty, "sAMAccountName");
		String userObjectClass = config.getProperty(VDirectConfiguration.AuthADServiceUserObjectClassProperty, "user");
		String groupObjectClass = config.getProperty(VDirectConfiguration.AuthADServiceGroupObjectClassProperty, "group");
		String truststorePath = System.getProperty("javax.net.ssl.trustStore");
		char[] truststorePassword = System.getProperty("javax.net.ssl.trustStorePassword","").toCharArray();
		
		//validate configuration
		if (serverURL == null) {
			throw new IllegalStateException("Active Directory server URL must be set in " + VDirectConfiguration.AuthADServiceURLProperty);
		}
		if (fqDomainName == null){
			throw new IllegalStateException("Active Directory fully qualified domain name must be set in " + 
					VDirectConfiguration.AuthADServiceFQDomainName);
		}
		if (!serverURL.toUpperCase().startsWith("LDAP")){
			throw new IllegalStateException("Active Directory server URL not in proper format: " +
				serverURL + " in " + VDirectConfiguration.AuthADServiceURLProperty);
		}
		if (validateCert) {
			if (truststorePath == null || truststorePath.length() == 0) {
				throw new IllegalStateException("LDAPS certificate validation enabled but no trust store path has been set in system properties");
			}
			if ((!new File(truststorePath).exists())) {
				throw new IllegalStateException("Trust store path: \"" + truststorePath + "\" does not exist in filesystem");
			}
		}
		adc = new ActiveDirectoryCommunicator(serverURL, 
				                              validateCert,
				                              verifyHost,
				                              truststorePath,
				                              truststorePassword,
				                              searchBases, 
				                              fqDomainName,
				                              nameAttribute,
				                              userObjectClass,
				                              groupObjectClass);
	}
	
	@Override
	public VDirectUser authenticate(String user, String password)
			throws AdcAuthException {
		Set<String> groups = adc.authenticate(user, password);
		return new ActiveDirectoryUser(user, password, getName(), groups);
	}

	@Override
	public Set<String> listGroups(VDirectUser user) {
		try {
			ActiveDirectoryUser adUser = (ActiveDirectoryUser) user;
			return adc.listGroups(adUser.getName(), adUser.getPassword());
		} catch (Exception e){
			return null;
		}
	}

	@Override
	public Set<String> listUsers(VDirectUser user) {
		try {
			ActiveDirectoryUser adUser = (ActiveDirectoryUser) user;
			return adc.listUsers(adUser.getName(), adUser.getPassword());
		} catch (Exception e){
			return null;
		}
	}
	*/
}
