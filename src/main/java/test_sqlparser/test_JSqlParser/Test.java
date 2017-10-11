package test_sqlparser.test_JSqlParser;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.util.TablesNamesFinder;

/**
 * liulu5
 * 2015-4-10
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-4-10 下午2:02:07 
 */
public class Test {

	private static void test(){
		
		try{
			CCJSqlParserManager pm = new CCJSqlParserManager();
			String sql = "SELECT * FROM MY_TABLE1, MY_TABLE2, (SELECT * FROM MY_TABLE3) LEFT OUTER JOIN MY_TABLE4 "+
			" WHERE ID = (SELECT MAX(ID) FROM MY_TABLE5) AND ID2 IN (SELECT * FROM MY_TABLE6) and id3=5" ;
			net.sf.jsqlparser.statement.Statement statement = pm.parse(new StringReader(sql));
			/* 
			now you should use a class that implements StatementVisitor to decide what to do
			based on the kind of the statement, that is SELECT or INSERT etc. but here we are only
			interested in SELECTS
			*/
			if (statement instanceof Select) {
				Select selectStatement = (Select) statement;
				TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
				List tableList = tablesNamesFinder.getTableList(selectStatement);
				for (Iterator iter = tableList.iterator(); iter.hasNext();) {
					System.out.println(iter.next());
				}
				
				SelectBody body = selectStatement.getSelectBody();
				System.out.println(body);
				
				List al = selectStatement.getWithItemsList();
				System.out.println(body);
				System.out.println(al);
			}
		}catch(Exception e){
			e.printStackTrace();
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

