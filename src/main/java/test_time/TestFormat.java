package test_time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestFormat {

	public static void test() throws ParseException{
		
		SimpleDateFormat DATEFORMATTER = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat STARTTIMEFORMATTER = new SimpleDateFormat("yyyy-MM-dd 01:00:00");
		SimpleDateFormat ENDTIMEFORMATTER = new SimpleDateFormat("yyyy-MM-dd 23:55:40");

		String dateString = "2017-10-10";
		
		Date parse = DATEFORMATTER.parse(dateString);
		System.out.println(parse);
		
//		String format = STARTTIMEFORMATTER.format(parse);
		String format1 = ENDTIMEFORMATTER.format(parse);
		System.out.println(format1);
		System.out.println(SimpleDateFormat.getDateTimeInstance().parse(format1));
		System.out.println(ENDTIMEFORMATTER.parse(format1));
//		System.out.println("start"+STARTTIMEFORMATTER.parse(format));
//		System.out.println("ends"+ENDTIMEFORMATTER.parse("format1").toLocaleString());
	}
	
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		test();
		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Object d = formatter.parse("2017-05-05");
//		System.out.println(d);
	}

}
