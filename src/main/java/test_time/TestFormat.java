package test_time;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestFormat {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Object d = formatter.parse("2017-05-05");
		System.out.println(d);
	}

}
