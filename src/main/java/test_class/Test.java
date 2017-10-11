package test_class;

import java.lang.reflect.InvocationTargetException;

public class Test {

	public static void test() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, SecurityException, InvocationTargetException, NoSuchMethodException{
		
		String cName = Parse.class.getName();
		System.out.println(cName);
		
		String value = "input value";
		Object objWithPara = Class.forName(cName).getConstructor(String.class).newInstance(value);
		System.out.println(objWithPara);
		
		Object objWithoutPara = Class.forName(cName).newInstance();
		System.out.println(objWithoutPara);
		if(objWithoutPara instanceof Parse){
//			System.out.println((Parse)obj.);
		}
			
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String a = new String("aa");
		if(a instanceof String){
			System.out.println("a");
		}
		
		a = null;
		if(a instanceof String){
			System.out.println("aaaa");
		}
		
		System.out.println(a.getClass().getName());
		
//		try {
//			test();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
