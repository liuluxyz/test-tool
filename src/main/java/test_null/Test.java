package test_null;

/**
 * liulu5
 * 2015-10-9
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-10-9 上午10:20:14 
 */
public class Test {

	private static void test(Integer in){
		if(in > 10)
			System.out.println(">");
		else
			System.out.println("<");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test(null);
	}

}

