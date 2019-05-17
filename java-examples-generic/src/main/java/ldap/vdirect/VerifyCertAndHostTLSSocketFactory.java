package ldap.vdirect;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.ldaptive.ssl.DefaultHostnameVerifier;

public class VerifyCertAndHostTLSSocketFactory extends SocketFactory  {
    private static SSLContext context;
    private static SSLSocketFactory trustFactory = null;
    private static DefaultHostnameVerifier hostNameVerifier;
    
    public static void init(final String keystorePath, char[] keystorePassword) {
        TrustManagerFactory trustedFactory;
		try {
			trustedFactory = TrustManagerFactory.getInstance("SunX509");
	        KeyStore keyStore = KeystoreTools.getKeystore(keystorePath, keystorePassword);
	        trustedFactory.init(keyStore);
	        context = SSLContext.getInstance("TLS");
	        context.init(null, trustedFactory.getTrustManagers(), new SecureRandom());
	        trustFactory = context.getSocketFactory();
	        hostNameVerifier = new DefaultHostnameVerifier();
		} catch (NoSuchAlgorithmException | KeyStoreException | CertificateException | KeyManagementException |
				IOException e) {
			throw new IllegalStateException(e);
		} 
    }

	public static SocketFactory getDefault() {
		return new VerifyCertAndHostTLSSocketFactory();
	}

	@Override
	public Socket createSocket(String arg0, int arg1) throws IOException,
			UnknownHostException {
		return initSSLSocket((SSLSocket) trustFactory.createSocket(arg0, arg1));
	}

	@Override
	public Socket createSocket(InetAddress arg0, int arg1) throws IOException {
		return initSSLSocket((SSLSocket) trustFactory.createSocket(arg0, arg1));
	}

	@Override
	public Socket createSocket(String arg0, int arg1, InetAddress arg2, int arg3)
			throws IOException, UnknownHostException {
		return initSSLSocket((SSLSocket) trustFactory.createSocket(arg0, arg1, arg2, arg3));
	}

	@Override
	public Socket createSocket(InetAddress arg0, int arg1, InetAddress arg2,
			int arg3) throws IOException {
		return initSSLSocket((SSLSocket) trustFactory.createSocket(arg0, arg1, arg2, arg3));
	}
	
	protected SSLSocket initSSLSocket(final SSLSocket socket)
	    throws IOException {

		// calling getSession() will initiate the handshake if necessary
		final String hostname = socket.getSession().getPeerHost();
		if (!hostNameVerifier.verify(hostname, socket.getSession())) {
			socket.close();
			socket.getSession().invalidate();
			throw new RuntimeException(
			String.format(
			"Hostname '%s' does not match the hostname in the server's " +
			"certificate",
			hostname));
		}
		return socket;
	}

}
