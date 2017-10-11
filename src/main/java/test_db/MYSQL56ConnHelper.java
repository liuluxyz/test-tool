package test_db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

/**
 * 
 * description:
 * @author liulu5 2012-9-15 下午12:10:30
 */
public class MYSQL56ConnHelper {

//	private static final Log LOG = LogFactory.getLog(MYSQL56ConnHelper.class);
	
	private static final String driver = "com.mysql.jdbc.Driver";
//	private static final String url = "jdbc:mysql://192.168.192.29/hive";
//	private static final String url = "jdbc:mysql://localhost/ctetl";
	private static final String url = "jdbc:mysql://10.17.254.14:3306/imc";
	private static final String user = "hmc";
	private static final String password = "hmc";
	
	private static MYSQL56ConnHelper mysqlConnHelper;
	
	private MYSQL56ConnHelper(){
		init();
	}
	
	public static MYSQL56ConnHelper getInstance(){
		if(mysqlConnHelper == null){
			mysqlConnHelper = new MYSQL56ConnHelper();
		}
		return mysqlConnHelper;
	}
	
	public void init(){
		try {
			Class.forName(driver);
//			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			LOG.error("mysqlConnHelper.init", e);
		} 
//		catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public Connection getConnection(){
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			LOG.error("mysqlConnHelper.getConnection", e);
		}
		return null;
	}
	
//	public Statement getStatement(){
//		if(conn == null){
//			init();
//		}
//		try {
//			return conn.createStatement();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public void close(Statement state, ResultSet result){
		try {
			if(result != null){
				result.close();
			}
			if(state != null){
				state.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = null;
			state = null;
//			LOG.error("mysqlConnHelper.close", e);
		}
	}
	
	public void close(Connection conn, Statement state, ResultSet result){
		try {
			if(result != null){
				result.close();
			}
			if(state != null){
				state.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = null;
			state = null;
			conn = null;
//			LOG.error("mysqlConnHelper.close", e);
		}
	}
	
	public void close(Connection conn){
		try {
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn = null;
//			LOG.error("mysqlConnHelper.close", e);
		}
	}
	
	public void close(Statement state){
		try {
			if(state != null){
				state.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			state = null;
//			LOG.error("mysqlConnHelper.close", e);
		}
	}

	private static int getTableRowCount(String sql){
		Connection conn = MYSQL56ConnHelper.getInstance().getConnection();
		Statement state = null;
		ResultSet set = null;
		try {
			state = conn.createStatement();
			set = state.executeQuery(sql);
			while(set.next()){
				return set.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MYSQL56ConnHelper.getInstance().close(conn, state, set);
		}
		return 0;
	}
	
	private static long executeSQL(String sql){
		Connection conn = MYSQL56ConnHelper.getInstance().getConnection();
		Statement state = null;
		ResultSet set = null;
		try {
			state = conn.createStatement();
			long a = System.currentTimeMillis();
			set = state.executeQuery(sql);
			long b = System.currentTimeMillis();
			
			int num = 0;
			while(set.next()){
				System.out.println(set.getString(1));
//				System.out.println(set.getLong(2));
//				System.out.println(set.getLong(3));
//				System.out.println(set.getLong(4));
				
				num++;
			}
			System.out.println("num : " + num);
			return b-a;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MYSQL56ConnHelper.getInstance().close(conn, state, set);
		}
		return 0;
	}
	
	private static void test(){

		String sql = "select count(1) from sch_job_log";
		String sql2 = "select count(1) from sch_var_log";
		
		String sql10 = "select l.dbid_ from sch_job j inner join sch_job_log l on j.dbid_=l.sch_job_id_ and  j.sn_='TestAfterJob' and j.is_in_use_=1 " +
				"WHERE exists(select 1 from sch_var_log v0 where v0.task_id_=l.dbid_ and v0.name_='TestPara_A' and v0.value_='AA') " +
				"AND exists(select 1 from sch_var_log v0 where v0.task_id_=l.dbid_ and v0.name_='TestPara_B' and v0.value_='BB') " +
				"AND exists(select 1 from sch_var_log v0 where v0.task_id_=l.dbid_ and v0.name_='TestPara_C' and v0.value_='CC')";
	
		String sql12 = "select l.dbid_ from sch_job j inner join sch_job_log l on j.dbid_=l.sch_job_id_ and  j.sn_='TestAfterJob' and j.is_in_use_=1 " +
				"inner join sch_var_log v0 on v0.task_id_=l.dbid_ and v0.name_='TestPara_A' and v0.value_='AA' " +
				"inner join sch_var_log v1 on v1.task_id_=l.dbid_ and v1.name_='TestPara_B' and v1.value_='BB' " +
				"inner join sch_var_log v2 on v2.task_id_=l.dbid_ and v2.name_='TestPara_C' and v2.value_='CC'";
		
		String sql13 = "select l.dbid_ from sch_job j " +
				"inner join sch_job_log l inner join sch_var_log v1 inner join sch_var_log v2 inner join sch_var_log v3 " +
				"on j.dbid_=l.sch_job_id_ " +
				"and l.dbid_=v1.task_id_ " +
				"and l.dbid_=v2.task_id_ " +
				"and l.dbid_=v3.task_id_ " +
				"and j.sn_='TestAfterJob' " +
				"and j.is_in_use_=1 " +
				"and v1.name_='TestPara_A' and v1.value_='AA' " +
				"and v2.name_='TestPara_B' and v2.value_='BB' " +
				"and v3.name_='TestPara_C' and v3.value_='CC'";
		
		while(true){
			int logNum = getTableRowCount(sql);
			int varNum = getTableRowCount(sql2);
			
			long executeExistsTime = executeSQL(sql10);
			long executeJoinTime = executeSQL(sql12);
			long executeJoin2Time = executeSQL(sql13);
			
			System.out.println("[" + logNum + ", " + varNum + "][" + executeExistsTime + "][" + executeJoinTime + "][" + executeJoin2Time + "]");
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void getColumnName(){
		String sql = "select 1 as a union select 2 union select 3 union select 4 union select 5";
		Connection conn = MYSQL56ConnHelper.getInstance().getConnection();
		Statement stat = null;
		ResultSet result = null;
		try {
			stat = conn.createStatement();
			result = stat.executeQuery(sql);
			ResultSetMetaData meta = result.getMetaData();
			while ( result.next() ) {
				for(int i=1; i<=meta.getColumnCount(); i++){
					String colLable = meta.getColumnLabel(i);
					String colName = meta.getColumnName(i);
					String colValue = result.getString(i);
					System.out.println(colLable + " - " + colName + " - " + colValue);
//					System.out.println(colName);
//					System.out.println(colValue);
				}
//				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MYSQL56ConnHelper.getInstance().close(stat, result);
			MYSQL56ConnHelper.getInstance().close(conn);
		}
	}
	
	
	private static ArrayList<String> getTables(){
		Connection conn = MYSQL56ConnHelper.getInstance().getConnection();
		ArrayList<String> al = new ArrayList<String>();
		try {
			ResultSet set = conn.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
			while(set.next()){
				String tt = set.getString("TABLE_NAME");  
				String tp = set.getString("TABLE_TYPE");  
//				System.out.println(" 表的名称 " + tt + "   表的类型 " + tp);
				al.add(tt);
			}
			return al;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MYSQL56ConnHelper.getInstance().close(conn);
		}
		return al;
	}	
	
	private static void check(){
		ArrayList<String> tables = getTables();
		for(String table : tables){
			System.out.println("checking..." + table);
			String sql = "select * from " + table;
			Connection conn = MYSQL56ConnHelper.getInstance().getConnection();
			Statement stat = null;
			ResultSet result = null;	
			try {
				stat = conn.createStatement();
				result = stat.executeQuery(sql);
				ResultSetMetaData meta = result.getMetaData();
				while ( result.next() ) {
					for(int i=1; i<=meta.getColumnCount(); i++){
						String colName = meta.getColumnName(i);
						String value = result.getString(i);
						if(value != null && value.contains("10.87")){
							System.out.println(table + " - " + colName + " - " + value);
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				MYSQL56ConnHelper.getInstance().close(stat, result);
				MYSQL56ConnHelper.getInstance().close(conn);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		getColumnName();
		
//		String sql = "select task.task_Type, sum(timestampdiff(second, start_time, finish_time)) as time_sum, " +
//				"min(timestampdiff(second, start_time, finish_time)) as time_min, max(timestampdiff(second, start_time, finish_time)) as time_max " +
//				"from hmc_Task task where task.status in ('COMPLETE', 'FAILED','KILLED') group by task.task_Type";
		
//		String sql = "SELECT * FROM HQ_METRIC_DATA_1D_1S WHERE timestamp between 1384164000000 and 1384167600000";
		
		String sql = "SELECT measurement_id, MIN(value), AVG(value), MAX(value), count(*)  FROM HQ_METRIC_DATA_1D_1S WHERE timestamp BETWEEN 1384164000000 and 1384167600000 GROUP BY measurement_id";
		
		executeSQL(sql);
		
	}

}
