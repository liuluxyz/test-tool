package test_StringHolder;

import org.omg.CORBA.StringHolder;

public class Test {

	
	
	
	public static void test(StringHolder msg){
		msg.value = "test";
	}
	
	public static void main(String[] args) {
		StringHolder msg = new StringHolder();
		msg.value = "main";
		System.out.println(msg.value);
		test(msg);
		System.out.println(msg.value);
		
	}
}
