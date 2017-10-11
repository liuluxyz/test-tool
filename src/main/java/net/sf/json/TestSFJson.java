package net.sf.json;

import java.util.Map;

/**
 * liulu5
 * 2015-2-12
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-2-12 下午3:41:36 
 */
public class TestSFJson {

	private static void test() throws JSONException{
		
		JSONObject b = new JSONObject();
		b.accumulate("a", "a");
		b.accumulate("b", "b");
		b.accumulate("c", "c");
		System.out.println(b);
		
//		JSONObject c = JSONObject.fromObject(b.toString());
		
		System.out.println((Map)b);
	}
	
	private static void test(String str){
//		JSONObject c = JSONObject.fromObject(str);
		
//		JSONArray a = JSONArray.fromObject(str);
//		System.out.println(a.size());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			test();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

