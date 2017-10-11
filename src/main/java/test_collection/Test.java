package test_collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Test {

	static class MyThread extends Thread{
		Set<String> actSet;
		String name;
		
		MyThread(String name, Set<String> actSet){
			this.actSet = actSet;
			this.name = name;
		}
		
		@Override
		public void run() {
			synchronized(actSet){
//			Collections.synchronizedSet(actSet){
				int i =0;
				while(i < 10){
					System.out.println(name + " add " + i);
					actSet.add(name + "-" + (i));
					i++;
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	static class MyThreadIterator extends Thread{
		Set<String> actSet;
		String name;
		
		MyThreadIterator(String name, Set<String> actSet){
			this.actSet = actSet;
			this.name = name;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			Object[] o = actSet.toArray();
//			for(Object a : o){
//				System.out.println("Iterator: " + a);
//			}
//			Iterator<String> it = actSet.iterator();
//			while(it.hasNext()){
//				System.out.println("Iterator: " + it.next());
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
		
			int i = 0;
			while(i < 10){
				actSet.add("Iterator" + i);
				System.out.println("add Iterator: " + i);
				i++;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Set<String> actSet = (new TreeSet<String>());
//		actSet.add("a");
//		actSet.add("b");
//		actSet.add("c");
		
		MyThread t1 = new MyThread("t1", actSet);
		MyThread t2 = new MyThread("t2", actSet);
		MyThread t3 = new MyThread("t3", actSet);
		t1.start();
//		t2.start();
//		t3.start();
		
		MyThreadIterator i1 = new MyThreadIterator("i1", actSet);
		MyThreadIterator i2 = new MyThreadIterator("i2", actSet);
		MyThreadIterator i3 = new MyThreadIterator("i3", actSet);
		i1.start();
//		i2.start();
//		i3.start();
		
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Iterator<String> it = actSet.iterator();
//		while(it.hasNext()){
//			System.out.println("Iterator: " + it.next());
//		}
		
		List a = new ArrayList();
		String[] aaa = (String[]) a.toArray(new String[0]);
		
		
	}

}
