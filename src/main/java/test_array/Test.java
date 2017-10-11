package test_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public class Test {

	private static void testEqual(){
		
		TestObj a = new TestObj("a", 1);
		
		List<TestObj> list = new ArrayList<TestObj>();
		list.add(a);
		
		System.out.println(list.indexOf(new TestObj("a", 1)));
		System.out.println(list.indexOf(new TestObj("b", 1)));
		System.out.println(list.indexOf(new TestObj("a", 2)));
	}
	
	
	private static void test(){
//		int[] a = new int[]{1, 2, 3, 4, 5};
//		String b = Arrays.toString(a);
//		System.out.println(b);
		
		String[] a = new String[]{"a", "b", "c", "d", "e"};
		List b = Arrays.asList(a);
		boolean c = Arrays.asList(a).contains("c");
		System.out.println(b);
		System.out.println(c);
		
//		int[] a = new int[]{1, 2, 3, 4, 5};
//		int[] b = new int[]{6, 7, 8, 9, 10};
//		int[] c = ArrayUtils.addAll(a, b);
		
//		String a = StringUtils.join(new String[]{"a", "b", "c", "d", "e"}, ",");
//		System.out.println(a);
		
//		int[] a = new int[]{1, 2, 3, 4, 5};
//		ArrayUtils.reverse(a);
//		System.out.println(Arrays.toString(a));
//		int[] b = ArrayUtils.removeElement(a, 3);
//		System.out.println(Arrays.toString(a));
//		System.out.println(Arrays.toString(b));
		
	}
	
	private static void testArrayList(){
		List<String> list = new ArrayList<String>();
		
		for(String str : list){
			System.out.println(str.trim());
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		test();
		
		
//		String[] a = null;
//		for(String aa : a){
//			System.out.println(aa);
//		}
//		testArrayList();
		
//		ArrayList<String> values = new ArrayList<String>();
//		for(int i=0; i<5; i++){
//			values.add("a_" + i);
//		}
//		
//		for(String v : values){
//			System.out.println(v);
//		}
//		System.out.println("--------------");
//		values.add(2, "xxxx");
//		for(String v : values){
//			System.out.println(v);
//		}
	}

}
