package ldap.vdirect;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class IgnoreCert_SSLSocketFactory extends SocketFactory {
	private static SocketFactory blindFactory = null;
	
	static {
		TrustManager[] blindTrustMan = new TrustManager[] { new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() { return null; }
			@Override
			public void checkClientTrusted(X509Certificate[] c, String a) { }
			@Override
			public void checkServerTrusted(X509Certificate[] c, String a) { }
		} };

		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, blindTrustMan, new java.security.SecureRandom());
			blindFactory = sc.getSocketFactory();
		} catch (GeneralSecurityException e) {
			throw new IllegalStateException(e);
		}
	}

	public static SocketFactory getDefault() {
		return new IgnoreCert_SSLSocketFactory();
	}

	@Override
	public Socket createSocket(String arg0, int arg1) throws IOException,
			UnknownHostException {
		return blindFactory.createSocket(arg0, arg1);
	}

	@Override
	public Socket createSocket(InetAddress arg0, int arg1) throws IOException {
		return blindFactory.createSocket(arg0, arg1);
	}

	@Override
	public Socket createSocket(String arg0, int arg1, InetAddress arg2, int arg3)
			throws IOException, UnknownHostException {
		return blindFactory.createSocket(arg0, arg1, arg2, arg3);
	}

	@Override
	public Socket createSocket(InetAddress arg0, int arg1, InetAddress arg2,
			int arg3) throws IOException {
		return blindFactory.createSocket(arg0, arg1, arg2, arg3);
	}

}
