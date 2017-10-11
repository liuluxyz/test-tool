package test_fileInJar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestFileInJar {

	
	public void readFile(String[] paths) throws IOException{

		System.out.println(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
		System.out.println(this.getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
//		System.out.println( System.getProperty("java.class.path"));
		
		
		String[] fileNames = new String[]{
				"src/main/resource/test.txt",
				"/src/main/resource/test.txt",
				"/main/resource/test.txt",
				"main/resource/test.txt",
				"test.txt"
				};
//		File file = new File(fileName);
//		InputStream in = this.getClass().getResourceAsStream("/UI/image/background.txt");
		for(String name : paths){
			java.net.URL fileURL = this.getClass().getResource(name);
			System.out.println(fileURL == null);
			if(fileURL != null){
				System.out.println(fileURL.toString());
				System.out.println(fileURL.getFile());
				System.out.println(fileURL.getContent());
			}
			
			BufferedReader reader = null;
			try {
				InputStream is=this.getClass().getResourceAsStream(name);
				this.getClass().getClassLoader().getResource(name);


	            reader = new BufferedReader(new InputStreamReader(is));
	            String tempString = null;
	            System.out.println("print file content...");
	            while ((tempString = reader.readLine()) != null) {
	            	System.out.println(tempString);
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
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		try {
//			new TestFileInJar().readFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
