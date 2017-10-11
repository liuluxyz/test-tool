package test_equals;

import java.util.ArrayList;

/**
 * liulu5
 * 2015-5-11
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-5-11 下午4:58:41 
 */
public class Test {

	public static void test(){
		TempBean beanA = new TempBean();
		beanA.setName("a");
		beanA.setAge(1);
		beanA.setType("a");
		
		TempBean beanB = new TempBean();
		beanB.setName("a");
		beanB.setAge(1);
		beanB.setType("a");
		
		System.out.println(beanA.equals(beanB));
		
		ArrayList<TempBean> al = new ArrayList<TempBean>();
		al.add(beanB);
		al.add(beanA);
		
		System.out.println(al.size());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

}

