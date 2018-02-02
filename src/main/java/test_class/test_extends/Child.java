package test_class.test_extends;

import java.util.Arrays;

public class Child extends Parent {

	String[] inters = new String[]{"a", "b"};
	
	public void testChild(){
		System.out.println("child print : " + Arrays.toString(inters));
		this.test();
	}
	
	public static void main(String[] args) {
		Child c = new Child();
		c.testChild();
	}
}
