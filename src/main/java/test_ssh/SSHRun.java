package test_ssh;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

public class SSHRun {

	public static boolean runWithShell(){
        
		SSHExec ssh = null;
		 try {
			 ConnBean cb = new ConnBean("10.1.252.69", "ocdc", "123456"); 
//			 ssh = SSHExec.getInstance(cb);
			 ssh = new SSHExec(cb);
			 ssh.connect();
			 
			 String[] cmds = new String[]{
					 "su - oracle",
					 "oracle",
					 "sqlplus /nolog",
					 "conn / as sysdba",
					 "alter user lntest account unlock"
			 };
			 CustomTask task = new ExecCommand(cmds);
			 Result res = ssh.exec(task);
			 if (res.isSuccess) {
				 System.out.println("Return code: " + res.rc); 
				 System.out.println("sysout: " + res.sysout); 
				 return true;
			 } 
			 else {
				 System.out.println("Return code: " + res.rc); 
				 System.out.println("error message: " + res.error_msg);
				 return false;
			 }
		 }
		 catch (TaskExecFailException e) { 
			 System.out.println(e.getMessage()); 
			 e.printStackTrace();
			 return false;
		 } 
		 catch (Exception e) { 
			 System.out.println(e.getMessage()); 
			 e.printStackTrace(); 
			 return false;
		 } 
		 finally { 
			 ssh.disconnect(); 	
		 }
	}
	
	public static boolean uploadSingleDataToServer(String fileName){
		SSHExec ssh = null;
		 try {
			 ConnBean cb = new ConnBean("192.168.38.86", "onest", "onest"); 
			 ssh = new SSHExec(cb);
			 ssh.connect();
			 
			 CustomTask cmd1 = new ExecCommand("chmod 755 /home/onest/htatp/"+fileName);
			 Result res = ssh.exec(cmd1);
			 if (!res.isSuccess) {
				 System.out.println("Return code: " + res.rc); 
				 System.out.println("error message: " + res.error_msg);
//				 return false;
			 }
			 
			 ssh.uploadSingleDataToServer(fileName, "/home/onest/htatp");
	 
			 CustomTask cmd3 = new ExecCommand("chmod 755 /home/onest/htatp/"+fileName);
			 res = ssh.exec(cmd3);
			 if (!res.isSuccess) {
				 System.out.println("Return code: " + res.rc); 
				 System.out.println("error message: " + res.error_msg);
				 return false;
			 }
			 return true;
		 }
		 catch (TaskExecFailException e) { 
			 System.out.println(e.getMessage()); 
			 e.printStackTrace();
			 return false;
		 } 
		 catch (Exception e) { 
			 System.out.println(e.getMessage()); 
			 e.printStackTrace(); 
			 return false;
		 } 
		 finally { 
			 ssh.disconnect(); 	
		 }
	}
	
	private static void test(){
		try {
			Process p = Runtime.getRuntime().exec("ll");
			InputStream in = p.getInputStream();
			
			byte[] b = new byte[4096];
			while(in.read(b)>-1){
				System.out.print(new String(b));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args){
		runWithShell();
		
		/**
		ConnBean cb = new ConnBean("192.168.38.86", "onest", "onest"); 
		SSHExec ssh = SSHExec.getInstance(cb); 
		ssh.connect();
		
		CustomTask cmd = new ExecCommand("ps -ef");
		Result res = null;
		try {
			res = ssh.exec(cmd);
		} catch (TaskExecFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res.isSuccess: " + res.isSuccess);
		System.out.println("res.sysout: " + res.sysout);
		System.out.println("Return code: " + res.rc);
		System.out.println("error message: " + res.error_msg);
		ssh.disconnect();
		
		*/
	}
}
