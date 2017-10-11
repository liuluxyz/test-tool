package test_csv;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.lang.CharSet;

import com.csvreader.CsvWriter;

public class Test {

	
	private static void testWrite() {
		try{
			CsvWriter w = new CsvWriter("test.csv", ',', Charset.defaultCharset());
			String[] strs = new String[]{"\\,a", "b", "c"};
			w.writeRecord(strs);
			w.flush();
			w.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		testWrite();
	}
}
