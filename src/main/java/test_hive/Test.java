package test_hive;

public class Test {

	
	static class HiveThread extends Thread{
		String hql;
		public HiveThread(String hql){
			this.hql = hql;
		}
		
		public void run(){
			String name = Thread.currentThread().getName();
//			System.out.println(str + " : " + name);
//			if(flag == true){
//				Test2.synMethod();	
//			}else{
//				Test2.noSynMethod();
//			}
		}
	}
	
	/**
	 * @param args
	 * 下午8:10:26
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String hql = "select count(1) from emp";
		
//		MyThread t1 = new MyThread("t1", true);
//		MyThread t2 = new MyThread("t2", true);
//		MyThread t3 = new MyThread("t3", true);
//		t1.start();
//		try {
//			Thread.sleep(700);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		t2.start();
//		t3.start();
	}

}
