package test_db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Oracle Connection Helper
 * description:
 * @author liulu5 2012-8-11 下午2:32:14
 */
public class OracleConnHelper {

	private static final Log LOG = LogFactory.getLog(OracleConnHelper.class);
	
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
//	private static final String url = "jdbc:oracle:thin:@132.63.10.3:1521:cntbc";
//	private static final String user = "ctbw";
//	private static final String password = "bwt2012";
	
//	private static final String url = "jdbc:oracle:thin:@10.1.252.69:1521:ctetl";
//	private static final String user = "lntest";
//	private static final String password = "lntest";
	
	private static final String url = "jdbc:oracle:thin:@10.1.252.96:1521:aiox";
	private static final String user = "hmc";
	private static final String password = "hmc";
	
//	private Connection conn;
	
	private static OracleConnHelper oracleConnHelper;
	
	private OracleConnHelper(){
		init();
	}
	
	public static OracleConnHelper getInstance(){
		if(oracleConnHelper == null){
			oracleConnHelper = new OracleConnHelper();
		}
		return oracleConnHelper;
	}
	
	public void init(){
		try {
			Class.forName(driver);
//			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error("OracleConnHelper.init", e);
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
			LOG.error("OracleConnHelper.getConnection", e);
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
			LOG.error("OracleConnHelper.close", e);
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
			LOG.error("OracleConnHelper.close", e);
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
			LOG.error("OracleConnHelper.close", e);
		}
	}
//	public void executeSQL(String sql){
//		Statement statement = null;
//		try {
//			statement = conn.createStatement();
//			statement.execute(sql);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			try {
//				if(statement != null && statement.isClosed()){
//					statement.close();
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//	}
	
	private static String getSql(){
		
		try {
			File f = new File("D:/sql");
			FileReader r = new FileReader(f);
			char[] c = new char[20000];
			int i = r.read(c);
			String s = new String(c);
//			s.getBytes(charsetName)
//			System.out.println(s);
//			BufferedReader br = new BufferedReader(r);
//			String a = br.readLine();
//			while(a != null){
//				System.out.println(a);
//				break;
//			}
			return s;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
		
//		InputStreamReader s = new InputStreamReader();
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//		process.getInputStream()));
	}
	
	private static void getColumnName(){
//		String sql = "select opt_time, table_name as ggg, count(1) num from hn_ods_status where deal_status='success' and table_name in ('a') group by opt_time, table_name";
		String sql = "select sn_ as A, job_name_, version_ from sch_job";
		Connection conn = OracleConnHelper.getInstance().getConnection();
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
					System.out.println(colLable);
					System.out.println(colName);
					System.out.println(colValue);
				}
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OracleConnHelper.getInstance().close(stat, result);
			OracleConnHelper.getInstance().close(conn);
		}
	}
	
	private static void testConnect(){
		String sql = "select * from hmc_host where rownum < 5";
		Connection conn = OracleConnHelper.getInstance().getConnection();
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
					System.out.println(colLable);
					System.out.println(colName);
					System.out.println(colValue);
				}
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OracleConnHelper.getInstance().close(stat, result);
			OracleConnHelper.getInstance().close(conn);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		getColumnName();
		
		testConnect();
		
		
//		Connection conn = OracleConnHelper.getInstance().getConnection();
//		try {
//			ResultSet set = conn.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
//			while(set.next()){
////				System.out.println(set.getString(1));
//				String tt = set.getString("TABLE_NAME");  
//				String  tp = set.getString("TABLE_TYPE");  
//		            System.out.println(" 表的名称 " + tt + "   表的类型 " + tp);  
//			}
//			
//			conn.getCatalog();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
