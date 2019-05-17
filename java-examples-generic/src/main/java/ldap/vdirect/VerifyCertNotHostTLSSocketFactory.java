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
import javax.net.ssl.TrustManagerFactory;

public class VerifyCertNotHostTLSSocketFactory extends SocketFactory {
    private static SSLContext context;
    private static SocketFactory trustFactory = null;
    public static void init(final String keystorePath, char[] keystorePassword) {
        TrustManagerFactory trustedFactory;
		try {
			trustedFactory = TrustManagerFactory.getInstance("SunX509");
	        KeyStore keyStore = KeystoreTools.getKeystore(keystorePath, keystorePassword);
	        trustedFactory.init(keyStore);
	        context = SSLContext.getInstance("TLS");
	        context.init(null, trustedFactory.getTrustManagers(), new SecureRandom());
	        trustFactory = context.getSocketFactory();
		} catch (NoSuchAlgorithmException | KeyStoreException | CertificateException | KeyManagementException |  
				IOException e) {
			throw new IllegalStateException(e);
		}
    }

	public static SocketFactory getDefault() {
		return new VerifyCertNotHostTLSSocketFactory();
	}

	@Override
	public Socket createSocket(String arg0, int arg1) throws IOException,
			UnknownHostException {
		return trustFactory.createSocket(arg0, arg1);
	}

	@Override
	public Socket createSocket(InetAddress arg0, int arg1) throws IOException {
		return trustFactory.createSocket(arg0, arg1);
	}

	@Override
	public Socket createSocket(String arg0, int arg1, InetAddress arg2, int arg3)
			throws IOException, UnknownHostException {
		return trustFactory.createSocket(arg0, arg1, arg2, arg3);
	}

	@Override
	public Socket createSocket(InetAddress arg0, int arg1, InetAddress arg2,
			int arg3) throws IOException {
		return trustFactory.createSocket(arg0, arg1, arg2, arg3);
	}
}
