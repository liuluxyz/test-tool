package test_HttpClient;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class TestGrowingIOHttp {

	
	public static void request() throws Exception{
//		GrowingDownloadApi api = new GrowingDownloadApi();
//		api.download("2017072300");
		String secret = "pEtrA5eNESPuY6duWraQ65ReneMEpheg";
		String project = "AwoVG292";
		String ai = "51f75ee222e44a81b9e5c84f570d28c2";
		String xClientId = "Ju2eW4Q5tEphudantu67swexed2wUpHe";
		String url = "https:/www.growingio.com/insights/"+ai+"/2017072300.json";
	            
//		Long tm = Time.now();
		Long tm = System.currentTimeMillis();
		
		String auth = authToken(secret,project,ai,tm);
		System.out.println(auth);
		
		//进行认证获取token
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("https://www.growingio.com/auth/token");

		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		
		parameters.add(new BasicNameValuePair("project", project));
		parameters.add(new BasicNameValuePair("ai", ai));
		parameters.add(new BasicNameValuePair("auth", auth));
		parameters.add(new BasicNameValuePair("tm", tm+""));
		
		httppost.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
		httppost.addHeader("X-Client-Id", xClientId);
		System.out.println(httppost.getHeaders("X-Client-Id")[0]);
		System.out.println(httppost.getEntity());
		HttpResponse response = httpClient.execute(httppost);
		System.out.println(response.getStatusLine());
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		HttpPost http = new HttpPost("https://www.growingio.com/insights/"+ai+"/"+statDate+".json");
	}
	
	public static String authToken(String secret, String project, String ai, Long tm) throws Exception {
		 String message = "POST\n/auth/token\nproject="+project+"&ai="+ai+"&tm="+tm;
		 Mac hmac = Mac.getInstance("HmacSHA256");
		 hmac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
		 byte[] signature = hmac.doFinal(message.getBytes("UTF-8"));
		 return Hex.encodeHexString(signature);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			request();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
