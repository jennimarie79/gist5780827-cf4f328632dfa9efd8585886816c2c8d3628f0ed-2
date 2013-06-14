import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import java.security.*;  
import java.security.cert.*;

public class Hello { 
  
	public static void debug(HttpURLConnection con) throws Exception
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}
	
	private static HttpsURLConnection getConnection(String url) throws IOException, NoSuchAlgorithmException, KeyManagementException {
		SSLContext context = SSLContext.getInstance("SSL");
		context.init(null, new TrustManager[] { new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}
		} }, new SecureRandom());
		HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
		con.setSSLSocketFactory(context.getSocketFactory());
		con.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		});
		return con;
	}

	public static void main(String[] args) throws Exception
	{
		HttpsURLConnection con_1 = getConnection("https://graph.qq.com/oauth2.0/authorize");// new URL("https://graph.qq.com/oauth2.0/authorize").openConnection();
		con_1.connect();
		System.out.println(con_1);	
		
		System.out.println("======================="); 
		
		HttpURLConnection con_2 = getConnection("https://openapi.360.cn/oauth2/access_token");//(HttpURLConnection) new URL("https://openapi.360.cn/oauth2/access_token").openConnection();
		con_2.connect();
		System.out.println(con_2);	
		
		System.out.println("Hello World!"); 
	}
}