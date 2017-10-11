package test_string;

import java.util.regex.Matcher;

public class test {

	public static void test(){
		Exception e = new Exception("test");
		System.out.println(e.getSuppressed());
		e.printStackTrace();
	}
	
	public static void testReplaceAll(){
		String a = "a$b$c/{d}";
		String b = a.replaceAll("/", "\\$");
		System.out.println(b);
		
		String c = a.replaceAll("\\$", "/");
		System.out.println(c);
		
		String split = "$";
		System.out.println("$".equals(split));
		
		String d = a.replaceAll("\\{", "\\\\{");
		System.out.println(d);
	}
	
	public static void testReplace(){
		String a = "a,b,c,{d}";
		String b = a.replace(",", "\\,");
		System.out.println(b);
	}
	
	private static void test(String str){
		str = str + "test";
		
		String a = "abcdef";
		System.out.println(a.substring(a.indexOf("abc")));
	}

	private static void test(int x){
		x = x + 1;
	}
	
	private static void testCompareTo(){
		String a = "2017-09-22";
		String b = "2017-09-21";
		System.out.println(a.compareTo(b));
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		testReplace();
		
//		int x = 1;
//		test(x);
//		System.out.println(x);
		
//		test();
		
//		String exportPath = "export PATH=JAVA_HOME/bin:$HADOOP_HOME/bin:$ZOOKEEPER_HOME/bin:$HIVE_HOME/bin:$HOME/bin:$PATH";
//		System.out.println(exportPath.indexOf("'$'"));
//		System.out.println(exportPath.indexOf("'$'"));
//		exportPath = exportPath.replaceAll("\\'$'", "\\\\'$'");
//		System.out.println(exportPath);
//		String newExportPath = exportPath.replaceAll("\\'$'", "\\\\'$'");
//		System.out.println(newExportPath);
		
//		System.out.println(exportPath.contains("="));
//		System.out.println(exportPath.indexOf("="));
//		String temp = "$JAVA_HOME/bin";
//		System.out.println(temp.indexOf("$"));
//		System.out.println(temp.indexOf("'$'"));
//		System.out.println(temp.indexOf("\\'$'"));
//		System.out.println(temp.indexOf('$'));
//		temp = temp.replace('$', '$');
//		System.out.println(temp);
//		temp = Matcher.quoteReplacement(temp);
//		System.out.println(temp);
//		String newExportPath = exportPath.replaceFirst("=", "=" + temp + ":");
//		String newExportPath = exportPath.replaceFirst("=", "=\\$JAVA_HOME/bin:");
//		System.out.println(newExportPath);
//		
//		System.out.println(exportPath.contains("$JAVA_HOME/bin:"));
		
//		String a = "我";
//		System.out.println(a.length());
		
//		int i=0;
//		while(i++ < 3){
//			String dataFileName = "asdfasdfasd.txt.gz";
//			String checkFilePath = "sdfasdfsdf.check";
//			if(checkFilePath.endsWith(".verf")){//经分接口的check文件以verf结尾
//				if(!dataFileName.endsWith(".txt.gz")){
//					System.out.println("no use");
//					continue;
//				}
//			}
//			System.out.println("use");
//		}
		
//		StringBuilder cause = (new StringBuilder())
//				.append("\u9519\u8BEF\u4FE1\u606F\uFF1A")
//				.append("\u9519\u8BEF\u65E5\u5FD7\uFF1A");
		
//		byte[] a = "3".getBytes();
//		System.out.println(a.length);
//		System.out.println(a[0]);
		
//		StringBuffer a = new StringBuffer("abcdef");
//		
//		String b = a.delete(a.length() -1, a.length()).toString();
//		System.out.println(a);
//		System.out.println(b);
		
//		String a = "abc.def";
//		String b = a.replaceAll("c" + "\\.", "");
//		System.out.println(b);
				
//		String a = "a/b/c/d";
//		System.out.println(a.substring(a.lastIndexOf("/")));
//		System.out.println(a.substring(0, a.lastIndexOf("/")));
	}

}
