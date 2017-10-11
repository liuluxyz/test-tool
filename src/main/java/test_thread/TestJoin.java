package test_thread;

/**
 * liulu5
 * 2016-3-22
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2016-3-22 下午4:07:19 
 */
public class TestJoin {

	static class BThread extends Thread {
	    public BThread() {
	        super("[BThread] Thread");
	    };
	    public void run() {
	        String threadName = Thread.currentThread().getName();
	        System.out.println(threadName + " start.");
	        try {
	            for (int i = 0; i < 5; i++) {
	                System.out.println(threadName + " loop at " + i);
	                Thread.sleep(1000);
	            }
	            System.out.println(threadName + " end.");
	        } catch (Exception e) {
	            System.out.println("Exception from " + threadName + ".run");
	        }
	    }
	}
	static class AThread extends Thread {
	    BThread bt;
	    public AThread(BThread bt) {
	        super("[AThread] Thread");
	        this.bt = bt;
	    }
	    public void run() {
	        String threadName = Thread.currentThread().getName();
	        System.out.println(threadName + " start.");
	        try {
	            bt.join();
	            System.out.println(threadName + " end.");
	        } catch (Exception e) {
	            System.out.println("Exception from " + threadName + ".run");
	        }
	    }
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start.");
        BThread bt = new BThread();
        AThread at = new AThread(bt);
        try {
            bt.start();
            Thread.sleep(2000);
            at.start();
            at.join();
        } catch (Exception e) {
            System.out.println("Exception from main");
        }
        System.out.println(threadName + " end!");
	}

}

