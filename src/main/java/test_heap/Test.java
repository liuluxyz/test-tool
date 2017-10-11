package test_heap;

import java.util.ArrayList;

/**
 * liulu5
 * 2014-3-13
 */
public class Test {

	ArrayList<TestBean> al = new ArrayList<TestBean>();
	
	public void add(int num){
		System.out.println("start add : " + num);
		for(int i=0; i<10000; i++){
			TestBean bean = new TestBean(num, i+1);
			al.add(bean);
		}
		System.out.println("end add : " + num);
	}
	
	public void reduce(int num){
		System.out.println("start reduce : " + num);
		for(int i=0; i<5000; i++){
			al.remove(i);
		}
		System.out.println("end reduce : " + num);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Test test = new Test();
			int num = 1;
			while(true){
				test.add(num);
				Thread.sleep(3000);
//				test.reduce(num);
				Thread.sleep(3000);
				num++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

