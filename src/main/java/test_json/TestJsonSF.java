package test_json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.hadoop.mapreduce.TaskType;
import org.omg.CORBA.IntHolder;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class TestJsonSF {

	public static void fromEnum(){
		
		EnumJson json = new EnumJson();
		json.setName("a");
		json.setType(TaskType.MAP);
		
//		JSONObject obj = JSONObject.fromObject(json);
//		System.out.println(obj.toString());
//		System.out.println(TaskType.MAP.toString());
//		
//		JSONObject a = new JSONObject();
//		a.put("name", "a");
//		a.put("type", "MAP");
//		EnumJson bean = (EnumJson) JSONObject.toBean(a, EnumJson.class);
//		System.out.println(bean.getName() + " - " + bean.getType());
		
	}
	
	
	public static void from() throws JSONException{
		
		JSONObject res = new JSONObject();
		res.put("res1", 11);
		res.put("res2", 22);
		
		Map<String, String> config = new HashMap<String, String>();
		config.put("con1", "conf1");
		config.put("con2", "conf2");
//		JSONObject configJson = JSONObject.fromObject(config);
		
		JSONObject std = new JSONObject();
		std.put("name", "a");
		std.put("age", 20);
//		std.put("address", "abc");
		std.put("alias", new String[]{"a1", "a2", "a3"});
		std.put("id", "aaa");
//		std.put("res", res);
//		std.put("config", configJson);
		
//		Student bean = (Student) JSONObject.toBean(std, Student.class);
//		System.out.println(bean.getId() + " - " + bean.getName() + " - " + bean.getAge() + " - " + bean.getAddress() + " - " + Arrays.toString(bean.getAlias()));
//		
//		JSONObject json = JSONObject.fromObject(bean);
//		System.out.println(json.toString());
	        
	}
	
	private static void testJsonArray() throws JSONException{
		JSONArray configJson = new JSONArray();
		for(int i=0; i<4; i++){
			JSONObject valueObj = new JSONObject();
			valueObj.put("value", "v"+i);
			valueObj.put("desc", "de");
			configJson.add(valueObj);
		}
		System.out.println(configJson.toString());
	}
	
	private static void testJsonAccumulate() throws JSONException{
		JSONObject configJson = new JSONObject();
		for(int i=0; i<4; i++){
			JSONObject valueObj = new JSONObject();
			valueObj.put("value", "v"+i);
			valueObj.put("desc", "de");
			
//			configJson.put("key" + i, valueObj);
			configJson.accumulate("key" + i, valueObj);
		}
		System.out.println(configJson.toString());
	}
	
	private static void testContains(){
		
		JSONObject js = new JSONObject();
//		System.out.println(js.containsKey("id"));
//		System.out.println(js.getString("id"));
	}
	
	private static void testNull(){
		String a = "{\"time\": \"2017-12-18T17:59:02+08:00\",\"uri\": \"/pv/trace.do\",\"args\": \"ca_tenant=136191&ptype=N&session_id=OtBWireMnXEoG9d85081&user_id=W2YoFb7k4IPi2yG87639&title=%E5%90%89%E6%9E%97%E7%9C%81%E5%85%AC%E5%8A%A1%E5%91%98%E8%80%83%E8%AF%95%E7%BD%91_%E5%90%89%E6%9E%97%E7%9C%81%E4%BA%BA%E4%BA%8B%E8%80%83%E8%AF%95%E7%BD%91-%E5%90%89%E6%9E%97%E5%8D%8E%E5%9B%BE%E6%95%99%E8%82%B2%E7%BD%91&pv_id=AUCS1i2uVIR7NTi17314&pf=web&refer=&ca_cv=&ca_kid=&utm_source=&hash_url=&ca_source=&sw=&join_key=AUCS1i2uVIR7NTi17314&ck=0&seq=70331144\",\"host\": \"bpv.geekca.cubead.com\",\"ua\": \"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 UBrowser/6.2.3831.407 Safari/537.36\",\"referer\": \"http://jl.huatu.com/\",\"cookie\": \"\",\"x_real_ip\": \"\",\"x_forwarded_for\": \"\",\"ip\": \"122.194.84.211\",\"uuid\": \"\",\"set_cookie\": \"UUID=wKgALVo3kWYCe2BlToEkAg==; expires=Thu, 31-Dec-37 23:55:55 GMT; domain=cubead.com; path=/\"}";
		JSONObject js = JSONObject.fromObject(a);
		String ip = js.getString("x_real_ip");
		
		System.out.println("aaa");
		System.out.println(ip == null);
		System.out.println("".equals(ip));
	}
	
	private static void testFileCubeiodata(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String fileName = "C:\\Users\\Administrator\\Desktop\\download\\access.log-2017122006";
		File file = new File(fileName);
		BufferedReader reader = null;
		Map<String, Cubeio> urls = new HashMap<String, Cubeio>();
		try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 0;
            while ((tempString = reader.readLine()) != null) {
            	line++;
            	JSONObject json = JSONObject.fromObject(tempString);
            	if(!json.containsKey("uri"))
            		continue;
            	
            	if(!urls.containsKey(json.getString("uri"))){
            		Cubeio cubeio = new Cubeio(json.getString("uri"));
            		urls.put(json.getString("uri"), cubeio);
            	}else{
            		urls.get(json.getString("uri")).getNum().value++;
            	}
            	
            	String time = json.getString("time");
            	Calendar calendar = DatatypeConverter.parseDateTime(time);
            	
            	String argsStr = json.getString("args");
            	JSONObject args = new JSONObject();
            	String[] temp = argsStr.split("&");
            	for(String t : temp){
            		String[] values = t.split("=");
            		if(values.length == 2)
            			args.put(values[0], values[1]);
            	}
            	if(args.containsKey("event_time")){
            		urls.get(json.getString("uri")).getEventTimeNum().value++;
            		
            		long eventTime = args.getLong("event_time");
//            		System.out.println((calendar.getTimeInMillis() - eventTime)/1000);
            		
            		System.out.println(format.format(calendar.getTime()));
            		System.out.println(format.format(new Date(eventTime)));
            		System.out.println("-----------");
            	}
            }
            
            System.out.println(urls);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}
	
	private static class Cubeio{
		String uri;
		IntHolder num;
		IntHolder eventTimeNum;
		public Cubeio(String uri){
			this.uri = uri;
			num = new IntHolder(1);
			eventTimeNum = new IntHolder(0);
		}
		public String getUri() {
			return uri;
		}
		public void setUri(String uri) {
			this.uri = uri;
		}
		public IntHolder getNum() {
			return num;
		}
		public void setNum(IntHolder num) {
			this.num = num;
		}
		
		public IntHolder getEventTimeNum() {
			return eventTimeNum;
		}
		public void setEventTimeNum(IntHolder eventTimeNum) {
			this.eventTimeNum = eventTimeNum;
		}
		@Override
		public String toString() {
			return "{url=" + uri + ",num=" + num.value + ",hasEventTime=" + eventTimeNum.value + "}";
		}
	}
	
	/**
	 * @param args
	 * 下午8:48:00
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub

//		from();
		
//		testJsonAccumulate();
		
//		testJsonArray();
		
//		testNull();
		
		testFileCubeiodata();
		
//		String a = "{A:[0,1,2,3,4,5,6,7,8,9]}";
//		try {
//			JSONObject circleDetail = new JSONObject();
//			for(java.util.Iterator<String> it = circleDetail.keys(); it.hasNext();){
//				String key = it.next();
//				JSONArray arrs = circleDetail.getJSONArray(key);
//				for(int i=0; i<arrs.length(); i++){
//					System.out.println(key + " : " + arrs.getString(i));
//				}
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		String[] aa = new String[]{"a", "b", "c"};
//		JSONObject obj = new JSONObject("xxx", aa);
//		System.out.println(obj.toString());
		
//		String a = "{A:[0,1,2,3,4,5,6,7,8,9],B:[11,12,13,14,15,16,17,18,19]}";
////		String a = "{}";
//		JSONObject obj = null;
//		try {
//			obj = new JSONObject(a);
//			System.out.println(obj.get("A"));
//			System.out.println(obj.get("B"));
//			System.out.println(obj.get("B").getClass().toString());
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(obj.toString());
		
		
		
//		ArrayList al = new ArrayList();
//		for(int i=0; i<10; i++){
//			al.add(i);
//		}
//		JSONObject json = new JSONObject();
//		try {
//			json.put("XX", al);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(json.toString());
//		
//		ArrayList al2 = new ArrayList();
//		for(int i=11; i<20; i++){
//			al2.add(i);
//		}
//		JSONObject obj = new JSONObject();
//		try {
//			obj.put("A", al);
//			obj.put("B", al2);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(obj.toString());
	}

}
