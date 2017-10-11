package test_XMLEncoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.beans.*;
import java.awt.Point;

public class SampleTest {
	
	public static void test() throws FileNotFoundException{

	    Sample sample = new Sample();
	    sample.setScores(new int[] {100, 90, 75});
	    sample.setName("Gore");
	    sample.setSeat(new Point(5, 3));
	    XMLEncoder encoder = new XMLEncoder(
	      new BufferedOutputStream(
	        new FileOutputStream("Sample.xml")));
	    long a = System.currentTimeMillis();
	    encoder.writeObject(sample);
	    long b = System.currentTimeMillis();
	    System.out.println(b-a);
	    
	    encoder.close();
	    System.out.println(sample);
	    
	    XMLDecoder decoder = new XMLDecoder(
	      new BufferedInputStream(
	        new FileInputStream("Sample.xml")));
	    Sample sample2 = (Sample)decoder.readObject();
	    decoder.close();
	    System.out.println(sample2);
	}
	
	public static void test2() throws FileNotFoundException{

		List<JavaBean> beans = new ArrayList<JavaBean>();
		JavaBean bean1 = new JavaBean();
		bean1.setName("a");
		bean1.setAge(11);
		bean1.setType("type_a");
		beans.add(bean1);
		
		JavaBean bean2 = new JavaBean();
		bean2.setName("b");
		bean2.setAge(22);
		bean2.setType("type_b");
		beans.add(bean2);
		
	    XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("bean.xml")));
	    encoder.writeObject(beans);
	    encoder.close();
	}
	
	public static void main (String args[]) throws Exception {
		
		test();
	}
}