package ldap.vdirect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class KeystoreTools {
	
	public static KeyStore putCertIntoKeystore(String keystorePath, char[] keystorePassword, String certAlias, Certificate certificate) throws Exception {
		KeyStore keystore = getKeystore(keystorePath, keystorePassword);
		Certificate cert = keystore.getCertificate(certAlias);
		if (cert != null) {
			keystore.deleteEntry(certAlias);
		}
		keystore.setCertificateEntry(certAlias, certificate);
		try (FileOutputStream out = new FileOutputStream(keystorePath)){
			keystore.store(out, keystorePassword);
			out.close();
		}
		return keystore;
	}
	
	public static KeyStore getKeystore(String keystorePath, char[] keystorePassword) throws KeyStoreException,
		FileNotFoundException, CertificateException, NoSuchAlgorithmException, IOException{
		KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		File keystoreFile = new File(keystorePath);
		if (keystoreFile.exists()) {
			try (FileInputStream is = new FileInputStream(keystorePath)){	
				keystore.load(is, keystorePassword);
			}
		} else {
			keystore.load(null, keystorePassword);
		}
		return keystore;
	}
	
	public static KeyStore newKeystore(char[] keystorePassword) throws Exception{
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(null, keystorePassword);
		return keyStore;
	}
	
    public static Certificate[] getCertificates(String url) throws NoSuchAlgorithmException, KeyManagementException, MalformedURLException,
		IOException{
	    // Create a trust manager that does not validate certificate chains
	    TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
	            @Override
				public X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	            @Override
				public void checkClientTrusted(X509Certificate[] certs, String authType) {
	            }
	            @Override
				public void checkServerTrusted(X509Certificate[] certs, String authType) {
	            }
	        }
	    };
	    // Install the all-trusting trust manager
	    SSLContext sc = SSLContext.getInstance("SSL");
	    sc.init(null, trustAllCerts, new java.security.SecureRandom());	
	    URL httpsURL = new URL(url);
	    HttpsURLConnection connection = (HttpsURLConnection) httpsURL.openConnection();
	    connection.setSSLSocketFactory(sc.getSocketFactory());
	    connection.setHostnameVerifier(new HostnameVerifier() {
	    	@Override
			public boolean verify(String hostname, SSLSession session){
	    		return true;
	    	}
	    });
	    connection.connect();
	    Certificate[] certs = connection.getServerCertificates();
	    return certs;	
	}

	public static String getFingerprint(X509Certificate cert, String algorithm) 
	    	throws NoSuchAlgorithmException, CertificateEncodingException {
	    	MessageDigest md = MessageDigest.getInstance(algorithm);
	    	byte[] der = cert.getEncoded();
	    	md.update(der);
	    	byte[] digest = md.digest();
	    	return bytesToHex(digest);
	
	    }
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 3];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 3] = hexArray[v >>> 4];
	        hexChars[j * 3 + 1] = hexArray[v & 0x0F];
	        hexChars[j * 3 + 2] = ':';
	    }
	    return new String(hexChars, 0, hexChars.length-1);
	}	
	
	public static String getIdentifyingString(X509Certificate x) throws Exception {
		StringBuffer str = new StringBuffer();
		str.append("Name\n");
		str.append(x.getSubjectX500Principal().getName());
		str.append("\nIsser DN\n");
		str.append(x.getIssuerX500Principal().getName());
		str.append("\nValid beginning\n");
		str.append(x.getNotBefore().toString());
		str.append("\nValid ending\n");
		str.append(x.getNotAfter().toString());
		str.append("\nSHA-256 Fingerprint\n");
		str.append(getFingerprint(x, "SHA-256"));
		str.append("\nSHA-1 Fingerprint\n");
		str.append(getFingerprint(x, "SHA-1"));		
		return str.toString();
	}
	
	public static void main(String[] args) throws Exception{
		Certificate[] certs = getCertificates("https://www.google.com");
		for (Certificate cert : certs){
			if (cert instanceof X509Certificate){
				X509Certificate x = (X509Certificate)cert;
				System.out.println(getIdentifyingString(x));
			}
		}
	}
	
}