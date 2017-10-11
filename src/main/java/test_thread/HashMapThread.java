package test_thread;

import java.util.HashMap;

public class HashMapThread implements Runnable {
	private static HashMap<String, String> map = new HashMap<String, String>();

	public void run() {
		String threadName = Thread.currentThread().getName();
		for (int i = 1; i < 10000000; i++) {
			if (threadName.equals("A")) {
				for (int j = 1; j < i - 1; j++) {
					System.out.println("----" + map.get("A" + j));
				}
			}

			map.put(threadName + i, threadName + i);
			System.out.println(map.get(threadName + i));
		}
	}

	public static void main(String[] args) {
		HashMapThread t1 = new HashMapThread();
		Thread ta = new Thread(t1, "A");
		Thread tb = new Thread(t1, "B");
		Thread tc = new Thread(t1, "C");
		ta.start();
		tb.start();
		tc.start();
	}
}