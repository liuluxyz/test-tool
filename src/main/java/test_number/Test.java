package test_number;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Test {

	private static void testDouble(){
		String t = "2.0090102E9";
		BigDecimal b = new BigDecimal(t);
		System.out.println(b.toPlainString());
		
		double d = 2.0090102E9;
		DecimalFormat df = new DecimalFormat("#.#"); 
		System.out.println("d = " + df.format(d)); 
		
		String t2 = "3.3333333333E-03";
		BigDecimal b2 = new BigDecimal(t2);
		System.out.println(b2.toPlainString());
		
		double d2 = 3.3333333333E-03;
		DecimalFormat df2 = new DecimalFormat("#.#"); 
		System.out.println("d = " + df2.format(d2)); 
		
	}
	
	private static void testDouble2(){
		String num1 = "6.6638888889e-02";
		String num2 = "6.5416666667e-02";
		String num3 = "5.3750000000e-02";
		String summary = "1.8580555556e-01";
		
		System.out.println(Double.parseDouble(num1) + " = " + Double.parseDouble(num2) + " = " + Double.parseDouble(num3));
		System.out.println(Double.parseDouble(summary));
		
		
		double sum = Double.parseDouble(num1) + Double.parseDouble(num2) + Double.parseDouble(num3);
		System.out.println(sum);
		System.out.println(Double.NaN);
		System.out.println(Double.parseDouble("NaN"));
		
		if(sum == Double.parseDouble(summary)){
			System.out.println("==");
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		testDouble();
		
		
//		double d = Double.parseDouble("6.583389");
//		float f = Float.parseFloat("6.583389");
//		
//		System.out.println(d);
//		System.out.println(f);
		
		
		
//		long bytesWrittenThreshold =  10L * 1024L * 1024L * 1024L;
////		long bytesWrittenThreshold = 1024 * 1024;
//		System.out.println(bytesWrittenThreshold);
//		long a = 1073741824l * 1024;
//		System.out.println(a);
//		System.out.println(bytesWrittenThreshold * 10);
		
		
//		long a = Long.parseLong("2.3800000000e-01");
//		double a = Double.parseDouble("2.3800000000e-01");
//		System.out.println(a);
		
	}

}
