package test_thread;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 测试场景：对一个list进行锁，一个线程轮询，另一个线程删除
 * @Description: TODO
 * @author liulu
 * @date 2017年11月20日 下午8:35:59
 */
public class TestSynchronized2 {
	
	private final List<String> runningCleanFlows =
			Collections.synchronizedList(new ArrayList<String>());
	
	public class RemoveThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(3000);
				synchronized(runningCleanFlows){
					runningCleanFlows.remove("abc");
				}
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void test(){
		runningCleanFlows.add("abc");
		for(int i=0; i<5; i++){
			runningCleanFlows.add("i+" + i);
		}
		System.out.println("init size : " + runningCleanFlows.size());
		
		new RemoveThread().start();
		
		synchronized(runningCleanFlows){
			for(String str : runningCleanFlows){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(str);
			}
			System.out.println("current 1 size : " + runningCleanFlows.size());
		}
		System.out.println("current 2 size : " + runningCleanFlows.size());
	}
	
	public static void  main(String[] args){
		new TestSynchronized2().test();
	}
}
