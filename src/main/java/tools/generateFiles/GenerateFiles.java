package tools.generateFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class GenerateFiles {

	static long fileSize = 100 * 1024 * 1024;//100M
	static int bufferSize = 1024 * 1024;
	
	public static void generateFiles(int num, String path) throws IOException{
		int i = 0;
		while(i++ < num){
			File file = new File(path + "/file1_" + i);
			if(!file.exists()){
				file.createNewFile();
				writeFile(file);
			}
		}
	}
	
	public static void writeFile(File file) throws IOException{
		FileOutputStream stream = new FileOutputStream(file);
		try{
			long remaining;
			for(remaining = fileSize; remaining > 0;){
				String content = reBuildBuffer(remaining > bufferSize ? bufferSize : remaining);
				stream.write(content.getBytes());
				remaining -= content.getBytes().length;
			}
		}finally{
			stream.close();
		}
		
//		try{
//			while(true){
//				stream.write(content.getBytes());
////				if(file.length() > (100 * 1024)){
////					stream.flush();
////					stream.close();
////					break;
////				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			stream.flush();
//			stream.close();
//		}
	}
	
	private static  String reBuildBuffer(long limitBufferSize){
		StringBuffer line = new StringBuffer("");
		while(true){
			line.append("temp");
			line.append(",");
			line.append("temp");
			line.append("\r\n");
			if(line.toString().getBytes().length >= limitBufferSize){
				break;
			}
		}
		return line.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String path = "/home/liulu/data/";
		int num = 100;
		if(args.length > 0){
			path = args[0];
			num = Integer.parseInt(args[1]);
		}
		try {
			generateFiles(num, path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
