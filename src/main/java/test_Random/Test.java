package test_Random;

import java.util.Random;

/**
 * liulu5
 * 2014-8-26
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2014-8-26 下午6:16:58 
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int i=0; i<10; i++){
			System.out.println(new Random().nextInt(10));
		}
		
		for(int i=0; i<10; i++){
			System.out.println(new Random().nextInt());
		}
	}

}

