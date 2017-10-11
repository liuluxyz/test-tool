package test_Serializable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Test {

	public static void test(){
		org.apache.hadoop.mapred.JobID obj = new org.apache.hadoop.mapred.JobID("job_200707121733", 3);
		
		ByteArrayOutputStream byteArrOS = null;
		ObjectOutputStream objOS = null;
		try {
			byteArrOS = new ByteArrayOutputStream();
			objOS = new ObjectOutputStream(byteArrOS);
			objOS.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (byteArrOS != null)
					byteArrOS.close();
				if (objOS != null)
					objOS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		test();
		
	}

}
