package test_zipfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * liulu5
 * 2014-5-29
 */
public class Test {

	
	 /** 
     * 依次读取压缩包中各文件内容 
     * @param file 
     */  
    public static void display(String file){
        try {
            ZipFile zip = new ZipFile(file);//由指定的File对象打开供阅读的ZIP文件
            Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zip.entries();//获取zip文件中的各条目（子文件）
            while(entries.hasMoreElements()){//依次访问各条目  
                ZipEntry ze = (ZipEntry) entries.nextElement();
//                System.out.println(ze.getCompressedSize());
//                System.out.println(ze.getMethod());
                System.out.println(ze.isDirectory());
                System.out.println(ze.getName());

                
//                break;
//                BufferedReader br = new BufferedReader(new InputStreamReader(zip.getInputStream(ze)));  
//                System.out.println("\n"+ze.getName()+":");
//                String line ;  
//                while((line = br.readLine()) != null){  
//                    System.out.println(line);  
//                }  
//                br.close();  
            }  
              
        } catch (ZipException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		display("D:\\project\\cdh4\\cdh5.0.0\\parquet-format-1.0.0.zip");
	}

}

