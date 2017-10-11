package test_sqlparser.test_ZQL;

import java.io.ByteArrayInputStream;

import org.gibello.zql.ParseException;
import org.gibello.zql.ZqlParser;

/**
 * liulu5
 * 2015-3-26
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-3-26 下午5:48:00 
 */
public class Test {

	private static void test() throws ParseException{
		
		String sql = "select col1 from table1";;
		ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sql.getBytes());
		
		ZqlParser zql = new ZqlParser();
		zql.initParser(tInputStringStream);
		System.out.println(zql.readExpression());
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			test();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

