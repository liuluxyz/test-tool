package test_ssh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

/**
 * liulu5
 * 2015-2-4
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-2-4 上午11:57:42 
 */
public class GetRRDFile {

	private static void get(String[] rrdfiles){

		SSHExec ssh = null;
		 try {
			 ConnBean cb = new ConnBean("10.1.253.182", "liulu", "liulu"); 
			 ssh = new SSHExec(cb);
			 ssh.connect();
			 
			 for(String rrdfile : rrdfiles){
				 System.out.println("start get file : " + rrdfile);
				 CustomTask cmd1 = new ExecCommand("rrdtool info /var/lib/ganglia/rrds/HDPNameNode/hdm182/" + rrdfile);
				 Result res = ssh.exec(cmd1);
				 if (!res.isSuccess) {
					 System.out.println("Return code: " + res.rc); 
					 System.out.println("error message: " + res.error_msg);
				 }
				 writeFile(rrdfile + ".info", res.sysout);
				 
//				 CustomTask cmd2 = new ExecCommand("rrdtool dump /var/lib/ganglia/rrds/HDPNameNode/hdm182/" + rrdfile);
//				 Result res2 = ssh.exec(cmd2);
//				 if (!res2.isSuccess) {
//					 System.out.println("Return code: " + res2.rc); 
//					 System.out.println("error message: " + res2.error_msg);
//				 }
//				 writeFile(rrdfile + ".xml", res2.sysout);
				 
				 System.out.println("end get file : " + rrdfile);
			 }
		 }
		 catch (TaskExecFailException e) { 
			 System.out.println(e.getMessage()); 
			 e.printStackTrace();
		 } 
		 catch (Exception e) { 
			 System.out.println(e.getMessage()); 
			 e.printStackTrace(); 
		 } 
		 finally { 	
			 ssh.disconnect(); 	
		 }
	}
	
	private static void generateXML(String[] rrdfiles){

		SSHExec ssh = null;
		 try {
			 ConnBean cb = new ConnBean("10.1.253.182", "liulu", "liulu"); 
			 ssh = new SSHExec(cb);
			 ssh.connect();
			 
			 for(String rrdfile : rrdfiles){
				 System.out.println("start generate file : " + rrdfile);
				 String cmd = "rrdtool dump /var/lib/ganglia/rrds/HDPNameNode/hdm182/" + rrdfile 
						 + " > " + "/var/lib/ganglia/rrds/HDPNameNode/hdm182/temp/" + rrdfile + ".xml";
				 CustomTask cmd1 = new ExecCommand(cmd);
				 Result res = ssh.exec(cmd1);
				 if (!res.isSuccess) {
					 System.out.println("Return code: " + res.rc); 
					 System.out.println("error message: " + res.error_msg);
				 }				 
				 System.out.println("end generate file : " + rrdfile);
			 }
		 }
		 catch (TaskExecFailException e) { 
			 System.out.println(e.getMessage()); 
			 e.printStackTrace();
		 } 
		 catch (Exception e) { 
			 System.out.println(e.getMessage()); 
			 e.printStackTrace(); 
		 } 
		 finally { 	
			 ssh.disconnect(); 	
		 }
	}
	
	private static void writeFile(String filename, String out) throws IOException{
		File localFile = new File("C:/Users/liulu5/Desktop/jrobin/test/" + filename);  
        
        OutputStream is = new FileOutputStream(localFile);
        is.write(out.getBytes());
        is.close();  
	}
	
	private static void checkRRDFile(String[] rrdfiles){
		StringBuffer str = new StringBuffer();
		
		for(String rrdFile : rrdfiles){
//			str.append(rrdFile).append("\n");
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader("C:/Users/liulu5/Desktop/jrobin/test/" + rrdFile + ".info"));
	            String tempString = null;
	            while ((tempString = reader.readLine()) != null) {
	            	if(tempString.contains("cf")){
	            		str.append(tempString).append("\n");
	            	}
	            }
	            reader.close();
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
		System.out.println(str);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		checkRRDFile(args);
		
		generateXML(args);
	}

}

