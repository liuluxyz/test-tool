package test_class;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TestInnerClass {

	public void test(){
		List<Map.Entry<Object, Object>> listMap = new ArrayList<Map.Entry<Object, Object>>();
		
		Collections.sort(listMap, new Comparator<Map.Entry<Object, Object>>() {
			public int compare(Map.Entry<Object, Object> o1,
					Map.Entry<Object, Object> o2) {
				return (Integer) o1.getKey() - (Integer) o2.getKey();
			}
		});
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new TestInnerClass().test();
		
		
	}

}
