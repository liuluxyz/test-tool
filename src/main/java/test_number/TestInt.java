package test_number;

import java.math.BigInteger;

public class TestInt {

	private static void testBigInteger(){
		BigInteger bi = new BigInteger("0");
		System.out.println(bi.longValue());
		
		bi.add(new BigInteger("10"));
		System.out.println(bi.toString());
	}
	
	public static void main(String[] args) {
		
	}
}
