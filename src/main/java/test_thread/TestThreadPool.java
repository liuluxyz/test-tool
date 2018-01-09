package test_thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {

	
	public class MyRun implements Runnable {
		String name;
		
		public MyRun(String name){
			this.name = name;
		}
		
		public void run() {
			try{
				System.out.println("start : " + name);
				int i = 0;
				while(true){
					System.out.println("running : " + name);
					i++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(i > 5){
						break;
					}
				}
				System.out.println("end : " + name);
				throw new Exception("test exception");
			}catch(Exception e){
//				e.printStackTrace();
				System.out.println("exception");
			}
		}
	}
	
	private void doTest(){
		int CONTHREADS = 5;
		Executor executor = Executors.newFixedThreadPool(CONTHREADS);
		for(int i=0; i<10; i++){
			MyRun run = new MyRun("run" + i);
			executor.execute(run);
//			System.out.println("Executor execute : " + "run" + i);
		}

		ExecutorService executorService = (ExecutorService) executor;
		executorService.shutdown();

		while (!executorService.isTerminated()) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("executorService.isTerminated()");
	}
	
	public void testThreadPool(){
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 6, 3, 
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),  new ThreadPoolExecutor.CallerRunsPolicy()); 
		 
		for(int i=0; i<10; i++){
			MyRun task = new MyRun("thread-" + i);
			threadPool.execute(task);
		}
		
		while(true){
			
			System.out.println("getActiveCount : " + threadPool.getActiveCount());
			System.out.println("getCompletedTaskCount : " + threadPool.getCompletedTaskCount());
//			System.out.println("getCorePoolSize : " + threadPool.getCorePoolSize());
//			System.out.println("getPoolSize : " + threadPool.getPoolSize());
//			System.out.println("getTaskCount : " + threadPool.getTaskCount());
			
			if(threadPool.getActiveCount() == 0){
				System.out.println("getActiveCount == 0");
				break;
			}
			

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		threadPool.shutdown();
		
	}
	
	public void testThreadPool2(){
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(100, 100, 0L,TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>()); 
		 
		for(int i=0; i<10; i++){
			MyRun task = new MyRun("thread-" + i);
			threadPool.execute(task);
		}
		
		while(true){
			
			System.out.println("getActiveCount : " + threadPool.getActiveCount());
			System.out.println("getCompletedTaskCount : " + threadPool.getCompletedTaskCount());
//			System.out.println("getCorePoolSize : " + threadPool.getCorePoolSize());
//			System.out.println("getPoolSize : " + threadPool.getPoolSize());
//			System.out.println("getTaskCount : " + threadPool.getTaskCount());
			
			if(threadPool.getActiveCount() == 0){
				System.out.println("getActiveCount == 0");
				break;
			}
			

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		threadPool.shutdown();
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new TestThreadPool().testThreadPool2();
		
	}

}
