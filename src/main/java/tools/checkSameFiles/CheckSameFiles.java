package tools.checkSameFiles;

import java.io.File;

/**
 * 对比输入的路径，查找下面相同文件名的文件
 * @author liulu5
 *
 */
public class CheckSameFiles {

	public void check(String[] paths){
		if(paths == null || paths.length == 0){
			System.out.println("input check path!");
			return;
		}
		
		for(int i=0; i<paths.length-1; i++){
			for(int j=i+1; j<paths.length; j++){
				File file1 = new File(paths[i]);
				if(file1.isFile()){
					System.out.println(paths[i] + " is not directory!");
					return;
				}
				File file2 = new File(paths[j]);
				if(file2.isFile()){
					System.out.println(paths[j] + " is not directory!");
					return;
				}
				checkSameFileBetween2Dirs(file1, file2);
				System.out.println();
			}
		}
	}
	
	public void checkSameFileBetween2Dirs(File file1, File file2){
		System.out.println("checking : " + file1.getAbsolutePath() + " & " + file2.getAbsolutePath());
		String[] list1 = file1.list();
		String[] list2 = file2.list();
		for(int i=0; i<list1.length; i++){
			for(int j=0; j<list2.length; j++){
				if(list1[i].equals(list2[j])){
					System.out.println("[" + file1.getAbsolutePath() + "\\" + list1[i] + "] equals [" + file2.getAbsolutePath() + "\\" + list2[j] + "]");
				}
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new CheckSameFiles().check(args);
//		File file = new File("D:\\personal\\movie");
//		String[] list = file.list();
//		for(String str : list){
//			System.out.println(str);
//		}
	}

}
