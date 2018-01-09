package test_hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Hive Connection Helper
 * description:
 * @author liulu5 2012-8-11 下午2:32:31
 */
public class HiveConnHelper {
//	private static final String driver = "org.apache.hadoop.hive.jdbc.HiveDriver";
//	private static final String url = "jdbc:hive://10.1.253.177:10000/default";
//	private static final String url = "jdbc:hive://10.1.253.179:11000/default";
	
	private static final String driver = "org.apache.hive.jdbc.HiveDriver";
//	private static final String url = "jdbc:hive2://192.168.0.156:8702/default";
	private static final String url = "jdbc:hive2://180.76.148.212:10000/default;auth=noSasl";
//	private static final String url = "jdbc:hive2://10.1.253.155:10008/default?auth=ocetl";
	
	private static final String user = "hive";
	private static final String password = "";
	
	private static HiveConnHelper hiveConnHelper;
	private Connection conn;
	
	private HiveConnHelper(){
		init();
	}
	
	public static HiveConnHelper getInstance(){
		if(hiveConnHelper == null){
			hiveConnHelper = new HiveConnHelper();
		}
		return hiveConnHelper;
	}
	
	public void init(){
		try {
			Class.forName(driver);
			this.conn = getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			LOG.error("[HiveConnHelper.java -- init]:" + e.toString());
		}
	}
	
	public Connection getConnection(){
		try {
			System.out.println("start get connect...");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("end get connect...");
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Statement getStatement(){
		if(conn == null){
			init();
		}
		try {
			return conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
//			LOG.error("[HiveConnHelper.java -- getStatement]:" + e.toString());
		}
		return null;
	}
	
//	public void close(Statement state, ResultSet result){
//		try {
//			if(result != null && !result.isClosed()){
//				result.close();
//			}
//			if(state != null && !state.isClosed()){
//				state.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public void close(Statement state){
//		try {
//			if(state != null && !state.isClosed()){
//				state.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static void executeSQL(String sql){
		Connection conn = HiveConnHelper.getInstance().getConnection();
		Statement statement = null;
		try {
			statement = conn.createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(statement != null && statement.isClosed()){
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				LOG.error("[HiveConnHelper.java -- executeSQL]:" + e.toString());
			}
		}
	}
	
	public static void executeQuery(String sql){
		Connection conn = HiveConnHelper.getInstance().getConnection();
		Statement state = null;
		ResultSet set = null;
		try {
			state = conn.createStatement();
			set = state.executeQuery(sql);
			
			while(set.next()){
				String str = set.getString(1);
				System.out.println(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(set != null){
					set.close();
				}
				if(state != null && state.isClosed()){
					state.close();
				}
				if(conn != null && conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				LOG.error("[HiveConnHelper.java -- executeSQL]:" + e.toString());
			}
		}
	}
	
	private static boolean test(){
		String[] sqls = new String[]{
//				"select count(*) SUM_KPI_VALUE from test3 where month_id = '201206' and latn_id = '83201'",
//				"select 83201 REGION_ID,832 PROV_ID,201206 MONTH_NO,30015001 CHECK_ID from test3 where month_id = '201206' and latn_id = '83201'",
//				"select null INFO_VAL from test3 where month_id = '201206' and latn_id = '83201'",
//				"select NULL CHECK_RESULT from test3 where month_id = '201206' and latn_id = '83201'",
//				"select unix_timestamp() CHECK_TIME from test3 where month_id = '201206' and latn_id = '83201'"
				
//				"select count(*) SUM_KPI_VALUE from test3 where amount = '0.0'",
//				"select 83201 REGION_ID,832 PROV_ID,201206 MONTH_NO,30015001 CHECK_ID from test3 where amount = '0.0'",
//				"select null INFO_VAL from test3 where amount = '0.0'",   -- error
//				"select NULL CHECK_RESULT from test3 where amount = '0.0'", -- error
//				"select 83201 REGION_ID, '' CHECK_RESULT from test3 where amount = '0.0'",
//				"select unix_timestamp() * 1000 CHECK_TIME from test3 where amount = '0.0'"
				"show tables"
		};
		
		Statement state = HiveConnHelper.getInstance().getStatement();
		try {
			for(int i=0; i<sqls.length; i++){
				System.out.println("--------------------------------------------------------------------------------------------------");
				System.out.println("[test] sql is : " + "[" + i + "] -- [" + sqls[i] + "]");
				ResultSet result = state.executeQuery(sqls[i]);
				try{
					int num = 0;
					
//						while(result.next()){
//							num++;
//							int res1 = result.getInt(1);
//							String res2 = result.getString(2);
////							int res3 = result.getInt(3);
////							int res4 = result.getInt(4);
//							System.out.println("test -- execute hql result is: ["+res1+","+res2+"]");
//							if(num >= 10){
//								System.out.println("resule num is greater than 10, so break result.next()");
//								break;
//							}
//						}
					
//						while(result.next()){
//							num++;
//							int res = result.getInt(1);
//							System.out.println("test -- execute hql result is: [" + res + "]");
//							if(num >= 10){
//								System.out.println("resule num is greater than 10, so break result.next()");
//								break;
//							}
//						}	
					
						while(result.next()){
							num++;
//							long res = result.getLong(1);
							String res = result.getString(1);
							System.out.println("test -- execute hql result is: [" + res + "]");
//							System.out.println(new java.util.Date(res));
							if(num >= 10){
								System.out.println("resule num is greater than 10, so break result.next()");
								break;
							}
						}
					
//						while(result.next()){
//							num++;
//							String res = result.getString(1);
//							System.out.println("test -- execute hql result is: [" + res + "]");
//							if(num >= 10){
//								System.out.println("resule num is greater than 10, so break result.next()");
//								break;
//							}
//						}
						System.out.println(num);
						
				} catch (SQLException e) {
					System.out.println(e);
					if("Error retrieving next row".equals(e.getMessage())){
						System.out.println("occur exception: Error retrieving next row and ignore this exception");
						continue;
					}
					throw e;
				}
				System.out.println("--------------------------------------------------------------------------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("end...");
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(System.currentTimeMillis());
		test();
		
//		executeQuery("select count(1) from test");
		
//		Statement state = HiveConnHelper2.getInstance().getStatement();
//		try {
//			long a = System.currentTimeMillis();
//			ResultSet result = state.executeQuery("select count(1) from test3");
//			long b = System.currentTimeMillis();
//			System.out.println(b-a);
//			while(result.next()){
//				System.out.println("******");
//				System.out.println(result.getString(1));  
//				System.out.println("******");
//			}
////			ResultSet set = conn.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
////			while(set.next()){
////				String tt = set.getString("TABLE_NAME");  
////				String  tp = set.getString("TABLE_TYPE");  
////		        System.out.println(" 表的名称 " + tt + "   表的类型 " + tp);  
////			}
////			conn.getCatalog();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println();
//			System.out.println("********************************");
//			System.out.println("e.toString():" + e.toString());  
//			System.out.println("e.getErrorCode():" + e.getErrorCode());
//			System.out.println("e.getLocalizedMessage():" + e.getLocalizedMessage());
//			System.out.println("e.getMessage():" + e.getMessage());
//			System.out.println("e.getSQLState():" + e.getSQLState());
//			System.out.println("********************************");
//		}
		
	}

}
