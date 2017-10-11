package test_sqlparser.test_TGSqlParser;

import gudusoft.gsqlparser.EDbVendor;
import gudusoft.gsqlparser.TCustomSqlStatement;
import gudusoft.gsqlparser.TGSqlParser;
import gudusoft.gsqlparser.nodes.TResultColumn;
import gudusoft.gsqlparser.stmt.TSelectSqlStatement;

/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-3-23 下午4:45:43 
 */
public class TestChange {

	protected static void analyzeSelectStmt(TSelectSqlStatement pStmt) {
		System.out.println("\nSelect statement:");
		if (pStmt.isCombinedQuery()) {
			String setstr = "";
			switch (pStmt.getSetOperator()) {
			case 1:
				setstr = "union";
				break;
			case 2:
				setstr = "union all";
				break;
			case 3:
				setstr = "intersect";
				break;
			case 4:
				setstr = "intersect all";
				break;
			case 5:
				setstr = "minus";
				break;
			case 6:
				setstr = "minus all";
				break;
			case 7:
				setstr = "except";
				break;
			case 8:
				setstr = "except all";
				break;
			}
			System.out.printf("set type: %s\n", setstr);
			System.out.println("left select:");
			analyzeSelectStmt(pStmt.getLeftStmt());
			System.out.println("right select:");
			analyzeSelectStmt(pStmt.getRightStmt());
			if (pStmt.getOrderbyClause() != null) {
				System.out.printf("order by clause %s\n", pStmt.getOrderbyClause().toString());
			}
		} else {
			// select list

			for (int i = 0; i < pStmt.getResultColumnList().size(); i++) {
				TResultColumn resultColumn = pStmt.getResultColumnList().getResultColumn(i);
				System.out.printf("\tColumn: %s, Alias: %s\n", resultColumn.getExpr().toString(),
						(resultColumn.getAliasClause() == null) ? "" : resultColumn.getAliasClause().toString());
			}
			
			for (int i = 0; i < pStmt.getResultColumnList().size(); i++) {
				TResultColumn resultColumn = pStmt.getResultColumnList().getResultColumn(i);
				if(resultColumn.getExpr().toString().contains("DATE")){
					resultColumn.getExpr().setString("change test1");
				}
				
				if(resultColumn.getExpr().toString().contains("SUBSTR")){
					resultColumn.getExpr().setString("change test2");	
				}
			}	
		}
	}

	protected static void analyzeStmt(TCustomSqlStatement stmt) {
		switch (stmt.sqlstatementtype) {
		case sstselect:
			analyzeSelectStmt((TSelectSqlStatement) stmt);
			break;
		case sstupdate:
			break;
		case sstcreatetable:
			break;
		case sstaltertable:
			break;
		case sstcreateview:
			break;
		default:
			System.out.println(stmt.sqlstatementtype.toString());
		}
	}

	private static void test() {
		TGSqlParser sqlparser = new TGSqlParser(EDbVendor.dbvteradata);
		sqlparser.sqltext = "SELECT e.last_name AS name,\n"
				+ " CAST('20140930' AS DATE FORMAT 'YYYYMMDD')(FORMAT 'YYYY-MM-DD')  AS d,\n"
				+ " SUBSTR(T1.int_on,3,5) as e,\n"
				+ " e.commission_pct comm,\n"
				+ " e.salary * 12 \"Annual Salary\"\n"
				+ "FROM scott.employees AS e\n" + "WHERE e.salary > 1000\n"
				+ "ORDER BY\n" + " e.first_name,\n" + " e.last_name;";
		int ret = sqlparser.parse();
		if (ret == 0) {
			for (int i = 0; i < sqlparser.sqlstatements.size(); i++) {
				analyzeStmt(sqlparser.sqlstatements.get(i));
				System.out.println("");
				
				System.out.println(sqlparser.sqlstatements.get(i).toString());
			}
		} else {
			System.out.println(sqlparser.getErrormessage());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test();
	}
}

