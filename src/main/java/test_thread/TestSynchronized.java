package test_thread;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestSynchronized {
	
	private static final String lockPara = "LOCK";//用于同步锁的常量,类级
	private String name;
	public TestSynchronized(String name){
		this.name = name;
	}
	
	public boolean execute() throws Exception {
		System.out.println("enter TestSynchronized...");
		
		System.out.println(name + " : get lock : " + new Date().toLocaleString());
		synchronized(lockPara){
			Thread.sleep(1000);
			System.out.println("TestSynchronized sleep...");
		
		}//end synchronized
		System.out.println(name + " : release lock : " + new Date().toLocaleString());
		
		System.out.println("end TestSynchronized");
		return true;
	}
	
	public static class MyRun extends Thread {
		String name;
		
		public MyRun(String name){
			this.name = name;
		}

		@Override
		public void run() {
			TestSynchronized test = new TestSynchronized(name);
			try {
				test.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void  main(String[] args){

		for(int i=0; i<20; i++){
			MyRun run = new MyRun(i + "");
			run.start();
		}
		
	}
}
