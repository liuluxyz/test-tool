package test_switch;

/**
 * liulu5
 * 2015-3-11
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-3-11 上午10:53:08 
 */
public class Test {

	private static void test(){
		int num = 10;
		
		switch(num){
		case 10:
			System.out.println("a");
		case 20:
			System.out.println("b");
			break;
		case 30:
			System.out.println("c");
		default:
			System.out.println("d");
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		test();
	}

}

