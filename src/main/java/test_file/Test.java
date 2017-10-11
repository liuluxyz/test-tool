package test_file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class Test {

	private static void testPathName() throws IOException{
		File file = new File("D:\\project\\cdh4\\cdh5.0.0\\hadoop-2.3.0-cdh5.0.0.tar.gz");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getCanonicalPath());
		System.out.println(file.getName());
		System.out.println(file.getPath());
	}
	
	private static void writeFile() throws IOException{
		File localFile = new File("test");  
        
        OutputStream is = new FileOutputStream(localFile);
        is.write("aaaaa".getBytes());
        is.write("\r\n".getBytes());
        is.write("bbb".getBytes());
        is.close();  
		
	}
	
	private static void testCreate() throws IOException{
		File file = new File("C:/Users/liulu5/Desktop/jrobin/a/b/c");
		boolean a = file.mkdirs();
		System.out.println(a);
		System.out.println(file.exists());
	}
	
	private static void testLength() throws IOException{
		File file = new File("D:/download/snapshot/99/8d5xdgqoaqkhq6tm");
		System.out.println(file.length());
		
		FileInputStream input = new FileInputStream(file);
		System.out.println(input.available());
	}
	
	private static void testRead(String filePath){
//		String fileName = "C:/Users/liulu5/Desktop/testdata";
		
		String fileName = "C:/Users/liulu5/Desktop/A_000SCA_F-SCAEnhance1.0-1002-000000-2015031815-0000.AVL";
		if(filePath != null)
			fileName = filePath;

		File file = new File(fileName);
		BufferedReader reader = null;
		try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            int colNum = 0;
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
//                System.out.println("line " + line++);
                line++;
                
                String[] cols = tempString.split("\t");
                if(colNum == 0){
                	colNum = cols.length;
                	continue;
                }
                
                if(colNum != cols.length)
                	System.out.println("col num is diff : " + colNum + " - " + cols.length);
            }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		System.out.println(File.separator);
//		System.out.println(File.pathSeparator);
		
		try {
//			testPathName();
			testLength();
			
//		if(args != null && args.length > 0)
//			testRead(args[0]);
//		else
//			testRead(null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
