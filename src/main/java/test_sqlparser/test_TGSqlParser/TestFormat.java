package test_sqlparser.test_TGSqlParser;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.pp.para.GFmtOpt;
import gudusoft.gsqlparser.pp.para.GFmtOptFactory;
import gudusoft.gsqlparser.pp.stmtformattor.FormattorFactory;

/**
 * liulu5
 * 2015-3-23
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-3-23 下午4:45:10 
 */
public class TestFormat {

	private static void format() {
		TGSqlParser sqlparser = new TGSqlParser(EDbVendor.dbvoracle);

		sqlparser.sqltext = "select col1, col2,sum(col3) from table1, table2 where col4 > col5 and col6= 1000";

		int ret = sqlparser.parse();
		if (ret == 0) {
			GFmtOpt option = GFmtOptFactory.newInstance();
			String result = FormattorFactory.pp(sqlparser, option);
			System.out.println(result);
		} else {
			System.out.println(sqlparser.getErrormessage());
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		format();
	}

}

