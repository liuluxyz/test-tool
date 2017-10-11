package tools.updatemaven;


import java.io.File;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

/**
 * 将本地maven库中的jar和pom文件上传至服务器的maven库
 * @author liulu5
 */
public class TransferFileFromLocalToRemote {

	static int directoryCount = 0;
	static int fileCount = 0;
	
	static int handleDirectoryCount = 0;
	static int handleFileCount = 0;
	
	static SSHExec ssh = null;
	static{
		ConnBean cb = new ConnBean("10.1.252.69", "cloudetl", "cloudetl"); 
//		ssh = SSHExec.getInstance(cb); 
		ssh.connect();
	}
	
	private static boolean checkFileExistInLocal(String filePathInLocal){
		File file = new File(filePathInLocal);
		return file.exists();
	}
	
	public static boolean checkFileExistInRemote(String filePathInRemote){
		 try {
			 CustomTask task = new ExecCommand("ls " + filePathInRemote);
			 Result res = ssh.exec(task);
			 if (res.isSuccess) {
//				 System.out.println("Return code: " + res.rc); 
//				 System.out.println("sysout: " + res.sysout); 
				 return true;
			 } 
			 else {
//				 System.out.println("Return code: " + res.rc); 
//				 System.out.println("error message: " + res.error_msg);
				 return false;
			 }
		 }
		 catch (TaskExecFailException e) {
			 e.printStackTrace();
			 return false;
		 } 
		 catch (Exception e) {
			 e.printStackTrace(); 
			 return false;
		 } 
	}
	
	public static boolean transferLocalFileToRemote(String filePathInLocal, String remotePath){
		 try {
			ssh.uploadSingleDataToServer(filePathInLocal, remotePath);
			System.out.println("transfer Local File To Remote : [" + filePathInLocal + "][" + remotePath + "]");
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		 return true;
	}
	
	public static boolean mkdirDirectoryInRemote(String dirPathInRemote){
		 try {
			 CustomTask task = new ExecCommand("mkdir " + dirPathInRemote);
			 Result res = ssh.exec(task);
			 if (res.isSuccess) {
				 System.out.println("mkdir success : " + dirPathInRemote); 
				 return true;
			 } 
			 else {
				 System.out.println("mkdir fail : " + dirPathInRemote); 
				 return false;
			 }
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
	}
	
	public static void transferAllFiles(){
		System.out.println("start..." + new java.util.Date().toLocaleString());
		String[] paths = new String[]{
				"C:/Users/liulu5/.m2/repository"
		};
		initCount(paths);
		
		for(String path : paths){
			File file = new File(path);
			transforPomFile(file);	
		}
		System.out.println("end..." + new java.util.Date().toLocaleString());
	}
	
	public static void initCount(String[] paths){
		for(String path : paths){
			File file = new File(path);
			initCount(file);	
		}
	}
	
	public static void initCount(File file){
		if(file.isDirectory()){
			directoryCount++;
			File[] childFiles = file.listFiles();
			for(File childFile: childFiles){
				initCount(childFile);
			}
		}else{
			fileCount++;
		}
	}
	
	public static void transforPomFile(File file){
		String path = file.getAbsolutePath();
		path = path.replace("\\", "/");
		if(path.contains("repository/com/ailk") || path.contains(".cache/m2e")){
			return;
		}
		
		if(file.isDirectory()){
			handleDirectoryCount++;
			System.out.println("***handleDirectoryCount: [" + handleDirectoryCount + "][" + directoryCount + "][" + new java.util.Date().toLocaleString() + "]");
			 
			String filePathInRemote = path.replaceAll("C:/Users/liulu5/.m2/repository", "/home/cloudetl/.m2/repository") + "/";
			boolean fileExistInRemote = checkFileExistInRemote(filePathInRemote);
			if(fileExistInRemote == false){
				mkdirDirectoryInRemote(filePathInRemote);
			}
			
			File[] childFiles = file.listFiles();
			for(File childFile: childFiles){
				transforPomFile(childFile);
			}
		}
		else{
			handleFileCount++;
			System.out.println("***handleFileCount: [" + handleFileCount + "][" + fileCount + "][" + new java.util.Date().toLocaleString() + "]");
			
			if(path.endsWith(".pom") || path.endsWith(".jar")){//��pom��jar�ļ��ϴ�
				String filePathInRemote = path.replaceAll("C:/Users/liulu5/.m2/repository", "/home/cloudetl/.m2/repository");
				boolean fileExistInRemote = checkFileExistInRemote(filePathInRemote);
				if(fileExistInRemote == false){
					String remotePath = filePathInRemote.substring(0, filePathInRemote.lastIndexOf("/")) + "/";
					transferLocalFileToRemote(path, remotePath);
				}
			}
		}
	}
	
	public static void main(String[] args){
//		if(args.length == 0){
//			return;
//		}
		
		transferAllFiles();		
	}
}
