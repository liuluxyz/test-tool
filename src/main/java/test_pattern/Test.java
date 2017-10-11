package test_pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void test(){
		String reg = "a-b*";
		Pattern pattern = Pattern.compile(reg);
		
		String name = "2012-a-b-0226.xml";
		Matcher matcher = pattern.matcher(name);
		if(matcher.find()){
			System.out.println("find");
		}else{
			System.out.println("no find");
		}
	}
	
	private static void testSpace(){
		String reg = "\\s2012*";
		Pattern pattern = Pattern.compile(reg);
		
		String name = "  2012-a-b-0226.xml";
		Matcher matcher = pattern.matcher(name);
		if(matcher.find()){
			System.out.println("find");
		}else{
			System.out.println("no find");
		}
	}
	
	private static void test6(){
//		String reg = "cast.'(\\d+)'\\sas\\sdate\\sformat\\s'(.)'.format\\s'(.)'.";
		String reg = "cast.'(\\d+)'\\sas\\sdate\\sformat\\s'(.+)'.\\(format\\s'(.+)'\\)";
		Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		
		String name = "CAST('20140930' AS DATE FORMAT 'YYYYMMDD')(FORMAT 'YYYY-MM-DD')";
		Matcher matcher = pattern.matcher(name);
		if(matcher.find()){
			System.out.println("find");
		}else{
			System.out.println("no find");
		}
		
		System.out.println(matcher.groupCount());
		System.out.println(matcher.group(0));
		System.out.println(matcher.group(1));
		System.out.println(matcher.group(2));
		System.out.println(matcher.group(3));
	}
	
	private static void test7(){
		String reg = ".+";
		Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		
		String name = "abcd";
		Matcher matcher = pattern.matcher(name);
		if(matcher.find()){
			System.out.println("find");
		}else{
			System.out.println("no find");
		}
	}
	
	public static void test4(){
//		String reg = "/(?=^.{3,254}$)(^([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])(\\.([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9]))*(\\.[a-zA-Z]{1,62})$)/";
		
		String reg = "/^(([a-zA-Z]|[a-zA-Z][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z]|[A-Za-z][A-Za-z0-9\\-]*[A-Za-z0-9])$/";
	    
		
		Pattern pattern = Pattern.compile(reg);
		
		String name = "centos1-a.a-a";
		Matcher matcher = pattern.matcher(name);
		if(matcher.find()){
			System.out.println("find");
		}else{
			System.out.println("no find");
		}
		
		String name2 = "centos1.a";
		boolean a = name2.matches(reg);
		System.out.println(a);
	}
	
	public static void test2(){
		String reg = "a-b-*2*.*";
		
		String name = "a-b-0226.xml";
		boolean a = name.matches(reg);
		System.out.println(a);
	}
	
	public static void test3(){
		String reg = "[*]_20140711";
		reg = reg.replace("reg:", "");
		System.out.println(reg);
		Pattern p = Pattern.compile(reg.replace("reg:", ""));
		
		Matcher m = p.matcher("programinfo_20140711");
		System.out.println(m.matches());
	}
	
	private static void test5(){
		//通过compile（）方法创建Pattern实例  
        Pattern pattern=Pattern.compile("java");  
        //通过match（）创建Matcher实例  
        Matcher matcher=pattern.matcher("java Java java JAVA jAva");  
        while (matcher.find())//查找符合pattern的字符串  
        {
            System.out.println("The result is here :" +   
                    matcher.group() + "\n" + "It starts from "  
                    + matcher.start() + " to " + matcher.end() + ".\n");  
        }
	}
	
	public static void test8(){
		String reg = "101_*.*((\\.CSV)|(\\.CHK))";
		
		String name = "101_201512111055_b6_00.CSV";
		boolean a = name.matches(reg);
		System.out.println(a);
	}
	
	private static void testReplace(){
		//通过compile（）方法创建Pattern实例  
        Pattern pattern = Pattern.compile("java",Pattern.CASE_INSENSITIVE);  
        //通过match（）创建Matcher实例  
        Matcher matcher=pattern.matcher("java Java java JAVA jAva avafdsafdsaf");  
        StringBuffer buffer=new StringBuffer();  
        int i=0;  
        while (matcher.find())  
        {  
            i++;  
            if(i%2==0)  
            {   //偶数项替换为JAVA  
                matcher.appendReplacement(buffer, "JAVA");  
            }  
            else {  //基数项替换为java  
                matcher.appendReplacement(buffer, "java");  
            }  
        }  
        matcher.appendTail(buffer);//将剩余的不匹配部分也追加到buffer中  
        System.out.println(buffer);  
	}
	
	private static void testIP(String ip){
		Pattern p= Pattern.compile("([0-9]|[0-9]\\d|1\\d{2}|2[0-1]\\d|25[0-5])(\\.(\\d|[0-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");  
	    Matcher m = p.matcher(ip);
		  boolean match = m.matches();
		  if(!match)   
		  {   
		      System.out.println("no");
		  }
		  else{
			  System.out.println("yes");
		  }
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test6();
		
		test8();
		
//		testIP("10.10.23");
	}

}
