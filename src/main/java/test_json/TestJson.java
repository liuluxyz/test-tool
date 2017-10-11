package test_json;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.mapreduce.TaskType;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

//import net.sf.json.JSONObject;


public class TestJson {

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
			JSONObject keyObj = new JSONObject();
			keyObj.put("key" + i, valueObj);
//			configJson.add(keyObj);
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
	
	/**
	 * @param args
	 * 下午8:48:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		from();
		
//		testJsonAccumulate();
		
		testContains();
		
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
