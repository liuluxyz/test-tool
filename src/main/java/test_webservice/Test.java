package test_webservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


import org.apache.activemq.kaha.Store;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import sun.net.www.http.HttpClient;

/**
 * liulu5 2014-6-5
 */
public class Test {

	public static String load(String url, String query) throws Exception {
		URL restURL = new URL(url);
		
		HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
		
//		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		
		 // 设置代理上外网
//		  System.getProperties().put("proxySet", "false");
//		  System.getProperties().put("proxyHost", "proxy.asiainfo.com");
//		  System.getProperties().put("proxyPort", "8080");

		
		conn.setRequestMethod("GET");
//		conn.setDoOutput(true);
//		conn.setAllowUserInteraction(false);
//		PrintStream ps = new PrintStream(conn.getOutputStream());
//		ps.println();
//		ps.print(query);
//		ps.close();
		
		BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line, resultStr = "";
		while (null != (line = bReader.readLine())) {
			resultStr += line;
		}
		bReader.close();
//		System.out.println(resultStr);
		return resultStr;
	}
	
//	public static void test(){
//		HttpClient client = new HttpClient();
//		client.getState().setCredentials(
//				new AuthScope("www.verisign.com", 443, "realm"),
//				new UsernamePasswordCredentials("username", "password")
//				);
//		GetMethod get = new GetMethod("https://www.verisign.com/products/index.html");
//		get.setDoAuthentication( true );
//		try {
//			int status = client.executeMethod( get );
//			System.out.println(status + "\n" + get.getResponseBodyAsString());
//		} finally {
//			get.releaseConnection();
//		}
//	}
	
	public static void testAmbari(){
		try{
			String resultString = load("--user admin:admin http://10.1.253.177:8080/api/v1/clusters",null);
			System.out.println(resultString);
			
			JSONObject retJSON = new JSONObject(resultString);
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
			String resultString = restUtil.load("http://10.1.253.176:16080/hmc_rest/host/manage",null);
			System.out.println(resultString);
			
			JSONObject retJSON = new JSONObject(resultString);
            String response = retJSON.getString("totalCount");
            System.out.println(response);
            JSONArray arr = retJSON.getJSONArray("list");
            System.out.println(arr.getJSONObject(0).get("ipAddress"));
            System.out.println(arr.getJSONObject(1).get("ipAddress"));
            System.out.println(arr.getJSONObject(2).get("ipAddress"));
            
            System.out.println();
			resultString = restUtil.load("http://10.1.253.176:16080/hmc_rest/host/monitor/metrics?hostId=1922",null);
			System.out.println(resultString);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		testAmbari();
		
//		 Store store = new Store("");// 准备参数
//		 // 首先创建一个webservice客户端，参数依次为：webservice的url, webservice的名称,
//		 webservice的方法, 参数列表, 返回类型, 泛型的类型(不需要泛型就传入null)
//		 // WebserviceClient client = new
//		 WebServiceClient("http://localhost:8088/TestWeb/webservice", "store",
//		 "find", new Object[] { store }, List.class, Store.class);
//		 WebServiceClient client = new
//		 WebServiceClient("http://localhost:8088/TestWeb/webservice", "store",
//		 "find", null, List.class, null);
//		 List<Store> list = client.execute();// 调用webservice
//		 System.out.println("从服务器返回" + list.size() + "个商品");// 得到了服务端返回的数据

	}

}
