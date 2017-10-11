package test_map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {

	public static void addDiffObject(){
		Map<Object, Object> a = new HashMap<Object, Object>();
		
		a.put(new Integer(1), new Long(0));
		a.put(new String("123"), new StringBuffer("123456"));
		
		System.out.println(a);
	}
	
	public static void testDelete(){
		Map<Integer, String> a = new HashMap<Integer, String>();
		
		a.put(1, "a");
		a.put(2, "b");
		a.put(3, "c");
		a.put(4, "d");
		a.put(5, "e");
		
		Iterator<Integer> it = a.keySet().iterator();
		while(it.hasNext()){
			
			int key = it.next();
			if(key == 3){
				it.remove();
			}
		}
		System.out.println(a);
	}
	
	
	private static void test(){
		
		String[] nodes = new String[]{
			"OCDC-DATA-014",
			"OCDC-DATA-008",
			"OCDC-DATA-002",
			"OCDC-DATA-010",
			"OCDC-DATA-012",
			"OCDC-DATA-006"
		};
		
		HashMap<String, String> nodeToBlocks = new HashMap<String, String>();
		for(String node : nodes){
			nodeToBlocks.put(node, "xxx");
		}
		
		for (Iterator<Map.Entry<String, String>> iter = nodeToBlocks.entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, String> one = iter.next();
			System.out.println(one.getKey());
		}
	}
	
	public static void testContains(){
		Map<Integer, String> a = new HashMap<Integer, String>();
		
		Integer i1 = new Integer(1);
		a.put(i1, "a");
		
		Integer i2 = new Integer(1);
		if(i1.equals(i2)){
			System.out.println("equals");
		}else{
			System.out.println("not equals");
		}
		if(i1 == i2){
			System.out.println("==");
		}else{
			System.out.println("!=");
		}
		System.out.println(a.containsKey(i1));
		System.out.println(a.containsKey(i2));
	}
	
	private static void testMuliKey(){
		Map<String, String> a = new HashMap<String, String>();
		a.put("a", "aaa");
		a.put("a", "bbb");
		System.out.println(a.size());
	}
	
	private static void testNull(){
		Map<String, String> a = new HashMap<String, String>();
		a.put("a", null);
		a.put("b", null);
		System.out.println(a.size());
		System.out.println(a);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testNull();
		
		
//		testMuliKey();
		
//		testContains();
		
//		addDiffObject();
//		test();
		
//		testDelete();
		
//		Map<String, String> a = new HashMap<String, String>();
//		
//		String name = "/etl/data_portal_bw/STransfile/837/MonthData/DAPM_PRD_PI_PO_USE.20130106.201212.00.001.001.837.DAT.gz";
//		String value = "haha";
//		a.put(name, value);
//		
//		Iterator<String> it = a.keySet().iterator();
//		while(it.hasNext()){
//			String key = it.next();
//			String v = a.get(key);
//			
//			System.out.println(key);
//			System.out.println(v);
//		}
//		
	}

}
