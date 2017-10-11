package test_HttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * liulu5 2014-6-5
 */
public class Test {
	
//	public static void test(){
		HttpClient client = new DefaultHttpClient();
//		client.getState().setCredentials(
//				new AuthScope("www.verisign.com", 443, "realm"),
//				new UsernamePasswordCredentials("username", "password")
//				);
//		GetMethod get = new GetMethod("https://www.verisign.com/products/index.html");
//		get.setDoAuthentication( true );
//		try {
//			int status = client.execute();
//			System.out.println(status + "\n" + get.getResponseBodyAsString());
//		} finally {
//			get.releaseConnection();
//		}
//	}
	
	public static void testAmbari(){
		try{
//			String resultString = load("--user admin:admin http://10.1.253.177:8080/api/v1/clusters",null);
//			System.out.println(resultString);
			
			JSONObject retJSON = new JSONObject();
            String response = retJSON.getString("cluster_name");
            System.out.println(response);
            JSONArray arr = retJSON.getJSONArray("disk_info");
            System.out.println(arr.getJSONObject(0).get("percent"));
            System.out.println(arr.getJSONObject(1).get("percent"));
            System.out.println(arr.getJSONObject(2).get("percent"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void testHmcRest(){
		try{
			Test restUtil = new Test();
//			String resultString = restUtil.load("http://10.1.253.176:16080/hmc_rest/host/manage",null);
//			System.out.println(resultString);
			
			JSONObject retJSON = new JSONObject();
            String response = retJSON.getString("totalCount");
            System.out.println(response);
            JSONArray arr = retJSON.getJSONArray("list");
            System.out.println(arr.getJSONObject(0).get("ipAddress"));
            System.out.println(arr.getJSONObject(1).get("ipAddress"));
            System.out.println(arr.getJSONObject(2).get("ipAddress"));
            
            System.out.println();
//			resultString = restUtil.load("http://10.1.253.176:16080/hmc_rest/host/monitor/metrics?hostId=1922",null);
//			System.out.println(resultString);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub


//		String a = com.sun.org.apache.xml.internal.security.utils.Base64.encode(new String("admin:admin").getBytes());
//		String b = org.apache.commons.net.util.Base64.encodeBase64String(new String("admin:admin").getBytes());
//		System.out.println("[" + a + "]");
//		System.out.println("[" + b + "]");
//		System.out.println(a.equals(b));
	}

}
