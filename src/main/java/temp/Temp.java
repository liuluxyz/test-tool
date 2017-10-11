package temp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Temp {
	  
	private static void test(){
		String dateStr = "20120630";
		try {//解析账单日期所在月份的最后一天
			Date date = new SimpleDateFormat("yyyyMMdd").parse(dateStr);
			System.out.println(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			cal.set(Calendar.DAY_OF_MONTH, value);
		} catch (ParseException e) {
			System.out.println("ParseException");
			e.printStackTrace();
		}
		System.out.println("end...");
	}
	
	private static void t() throws Exception{
		try{
			System.out.println("AAA");
			Integer.parseInt("a");
		}finally{
			System.out.println("BBB");
		}
	}
	
	private static void t2(){
		String[] ch = new String[]{"+", "-", "*", "/"};
		
		int[] aa = new int[]{50, 10, 25,8 };
		int a = aa[0];
		int b = aa[1];
		int c = aa[2];
		int d = aa[3];
		
		
		for(String c1 : ch){
			int res1 = 0;
			if("+".equals(c1))
				res1 = a + b;
			else if("-".equals(c1))
				res1 = a - b;
			else if("*".equals(c1))
				res1 = a * b;
			else if("/".equals(c1))
				res1 = a/b;
			
			for(String c2 : ch){
				int res2 = 0;
				if("+".equals(c2))
					res2 = res1 + c;
				else if("-".equals(c2))
					res2 = res1 - c;
				else if("*".equals(c2))
					res2 = res1 * c;
				else if("/".equals(c2))
					res2 = res1/c;
				
				for(String c3 : ch){
					int res3 = 0;
					if("+".equals(c3))
						res3 = res2 + d;
					else if("-".equals(c3))
						res3 = res2 - d;
					else if("*".equals(c3))
						res3 = res2 * d;
					else if("/".equals(c3))
						res3 = res2/d;

					if(res3 == 517){
						System.out.println(c1 + " " + c2 + " " + c3);
						return;
					}
				}
			}
		}
	}

	
	/**
	 * @param args
	 * 下午4:59:25
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		t2();
		
//		String[] strs = new String[]{"a", "b"};
//		StringBuffer stateStr = new StringBuffer("");
//		for(String state : strs){
//			stateStr.append("'" + state + "'").append(",");
//		}
//		System.out.println(stateStr);
//		stateStr = stateStr.replace(stateStr.length()-1, stateStr.length(), "");
//		System.out.println(stateStr);
		
//		try{
//			t();
//			System.out.println("RRRRRRRRRRr");
//		}catch(Exception e){
//			e.printStackTrace();
//			System.out.println("EEEEEEEEEEEEEEEEEEee");
//		}
		
		
//		String a = "[12]select * from bb";
//		String b = a.substring(a.indexOf("]")+1);
//		System.out.println(a);
//		System.out.println(b);
		
//		System.out.println(new java.util.Date().toLocaleString());
//		test();
		
//		StringBuffer sql = new StringBuffer("select * from aa unit select UNION,");
////		String sqlStr = sql.substring(0, sql.lastIndexOf("UNION"));
////		System.out.println(sqlStr);
//		
//		sql.replace(sql.length()-1, sql.length(), ")");
//		System.out.println(sql);
	}

}
