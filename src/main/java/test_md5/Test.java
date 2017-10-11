package test_md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * liulu5
 * 2015-12-29
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-12-29 下午6:24:45
 */
public class Test {

	private static Object getMD5(String oriRowKey) throws Exception {
		if (oriRowKey == null) {
			throw new Exception("param of oriRowKey is null");
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'b', 'd', 'e', 'f' };

		byte[] btInput = oriRowKey.getBytes();
		// 获得MD5摘要算法的 MessageDigest 对象
		MessageDigest mdInst;
		try {
			mdInst = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			throw new Exception("failed init MD5 instance.", ex);
		}
		// 使用指定的字节更新摘要
		mdInst.update(btInput);
		// 获得密文
		byte[] md = mdInst.digest();
		// 把密文转换成十六进制的字符串形式
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		String result = new String(str);
		return result.substring(1, 2) + result.substring(3, 4)
				+ result.substring(5, 6);

	}
	
	private static void test(){
		String a = "1";
		byte[] aa = a.getBytes();
		
		for(byte b : aa){
			int bb = b >>> 4;
			System.out.println(bb);
		}
		
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		test();
		
		try {
			Object a = getMD5("13810683919");
			System.out.println(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
