package test_time;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;

public class Test {

	private static void testCalendar2(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(1512112026699l);
		
		System.out.println(calendar.getTime().toLocaleString());
		
//		calendar.add(Calendar.MINUTE, -803520);
		calendar.add(Calendar.MINUTE, -500000);
		System.out.println(calendar.getTime().toLocaleString());
	}
	
	
	private static void testCalendar(){
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime().toLocaleString());
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
		
		DateUtils.addDays(calendar.getTime(), -1);
		System.out.println(calendar.getTime().toLocaleString());
		
		
		calendar.setTimeInMillis(1398931037762L);
		
//		calendar.setTimeInMillis(System.currentTimeMillis()););
		System.out.println(calendar.getTime().toLocaleString());
		
		calendar.add(Calendar.DAY_OF_WEEK, 14);
		System.out.println(calendar.getTime().toLocaleString());
		
		calendar.add(Calendar.DAY_OF_MONTH, 14);
		System.out.println(calendar.getTime().toLocaleString());
		
		calendar.add(Calendar.MONTH, -1);
		System.out.println(calendar.getTime().toLocaleString());
	
		calendar.add(Calendar.MONTH, -1);
		System.out.println(calendar.getTime().toLocaleString());
		
		calendar.add(Calendar.HOUR, 13);
		System.out.println(calendar.getTime().toLocaleString());
		
		calendar.add(Calendar.HOUR_OF_DAY,13);
		System.out.println(calendar.getTime().toLocaleString());
	
		calendar.set(Calendar.HOUR_OF_DAY, -4);
		System.out.println(calendar.getTime().toLocaleString());
		
//		calendar.add(Calendar.MINUTE, 1);
//		System.out.println(calendar.getTime().toLocaleString());
//		
//		calendar.add(Calendar.SECOND, 1);
//		System.out.println(calendar.getTime().toLocaleString());
	}
	
	private static void testFormat(){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = formatter.parse("01/02/2013");
			System.out.println(date.toLocaleString());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testShow(){
		DateUtils time = new DateUtils();	
	}
	
	private static void testRandom(){
		for(int i=0; i<10; i++){
			Calendar calendar = Calendar.getInstance();
			int ran = new Random().nextInt(10);
			calendar.add(Calendar.DAY_OF_MONTH, ran);
			String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
			System.out.println(date);
		}
		
	}
	
	private static void testDate() throws ParseException{
		long d = Date.parse("Tue Apr 28 11:45:36 2015");
		
        String a =  "Sun Nov 13 21:56:41 2011";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.US);//MMM dd hh:mm:ss Z yyyy
            System.out.println(sdf.parse(a));
        
		System.out.println(d);
//		System.out.println(dd);
	}
	
	private static void showTime(){
	
//		System.out.println(new Date(1515056479261L).toLocaleString());

		SimpleDateFormat TIMEFORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(TIMEFORMATTER.format(new Date()));
		
		String a = TIMEFORMATTER.format(new Date(1515056778796L));
		System.out.println(a);
		
		TIMEFORMATTER.setTimeZone(TimeZone.getTimeZone("GMT"));
		a = TIMEFORMATTER.format(new Date(1515056778796L));
		System.out.println(a);
	}
	
	
	private static void testTimestamp(){
		Timestamp t = new Timestamp(System.currentTimeMillis());
		System.out.println(t.toLocaleString());
		
		System.out.println(System.currentTimeMillis());
		System.out.println(1422957452);
//		1285862400
		System.out.println(new Date(1423557902 * 1000L).toLocaleString());
		System.out.println(new Date(1423557893 * 1000).toLocaleString());
		
		Timestamp t2 = new Timestamp(1423638303000L);
		System.out.println("a : " + t2.toLocaleString());
		Timestamp t3 = new Timestamp(1423638715000L);
		System.out.println("a2 : " + t3.toLocaleString());
		Timestamp t4 = new Timestamp(1423638753000L);
		System.out.println("a3 : " + t4.toLocaleString());
		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(1423638303 * 1000 + 139);
		System.out.println(calendar.getTime().toLocaleString());
		
		String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(1422957452 * 1000 * 1000));
		System.out.println(date);
		
		System.out.println(System.currentTimeMillis());
	}
	
	private static void test(){
		System.out.println(System.currentTimeMillis());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis());
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		showTime();
		
//		testCalendar2();
		
//		testTimestamp();
		
//		try {
//			testDate();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		testRandom();
		
		
//		testTimestamp();
		
//		System.out.println(new SimpleDateFormat("YY-MM-dd hh:mm:ss").format(new Date()));
//		System.out.println(new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(new Date()));
//		System.out.println(new SimpleDateFormat("YYMMddhhmmss").format(new Date()));
//		System.out.println(new SimpleDateFormat("yyMMddhhmmss").format(new Date()));
//		testCalendar();
		
//		testFormat();
		
//		System.out.println(SimpleDateFormat.getDateInstance().format(Calendar.getInstance().getTime()));
		
		
//		try {
//			String a = new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("yyyymmdd").parse("20120304"));
//			System.out.println(a);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		Timestamp t = new Timestamp(0);
//		System.out.println(t.toLocaleString());
		
//		try {
//			Date d = DateFormat.getDateTimeInstance().parse("2009-03-20 11:30:01");
////			System.out.println(d.toLocaleString());
//			System.out.println(d.getTime());
//			System.out.println(d.getTimezoneOffset());
//			
//			
//			Timestamp st = new Timestamp(1237573801);
//			System.out.println(st.toString());
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
