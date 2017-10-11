package test_return;

/**
 * liulu5
 * 2015-6-9
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-6-9 上午10:25:28 
 */
public class Test<V> {

	V get(){
		return (V) "";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Test t = new Test<String>();
		Object a = t.get();
		
		System.out.println(a instanceof String);
	}

}

