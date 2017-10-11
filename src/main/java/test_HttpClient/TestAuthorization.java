package test_HttpClient;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

//import com.sun.org.apache.xml.internal.security.utils.Base64;

public class TestAuthorization {

	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();
//		try {
//			HttpGet request = new HttpGet();
//			request.setURI(new URI("http://10.1.253.190:8080/api/v1/clusters/"));
//			String authCode = Base64.encode(new String("admin:admin").getBytes());
//			request.addHeader("Authorization", "Basic " + authCode);
//			HttpResponse httpResponse = httpClient.execute(request);
//			String retString = EntityUtils.toString(httpResponse.getEntity());
//			System.out.println(retString);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
