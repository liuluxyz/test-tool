package tools.getFileList;

import java.io.File;

public class GetWindowsFileList {

	
	public void printFileList(String path){
		File file = new File(path);
		if(file.isFile()){
			return;
		}
		
		int num = 0;
		File[] files = file.listFiles();
		for(File child : files){
			System.out.println(child.getName());
			num++;
		}
		System.out.println("-----------------------------");
		System.out.println(num);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String path = "D:\\project\\ocdc\\branches\\release_1_7_0_cdh4\\CloudETL\\target\\AI-Cloud-ETL\\lib";
		new GetWindowsFileList().printFileList(path);
		
	}

}
