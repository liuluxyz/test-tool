package test_thread;

public class MyThread implements Runnable {

	public void run() {
		synchronized (this) {
			for (int i = 1; i < 10000000; i--) {
				System.out.println(Thread.currentThread().getName()
						+ " synchronized loop " + i);
				if (i % 10000 == 0) {
					try {
						this.notifyAll();
						this.wait(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		Thread ta = new Thread(t1, "A");
		Thread tb = new Thread(t1, "B");
		Thread tc = new Thread(t1, "C");
		ta.start();
		tb.start();
		tc.start();
	}
}