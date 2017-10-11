package temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Oracle Connection Helper
 * description:
 * @author liulu5 2012-8-11 下午2:32:14
 */
public class OracleConnHelper {

	private static final Log LOG = LogFactory.getLog(OracleConnHelper.class);
	
	private static final String driver = "oracle.jdbc.OracleDriver";
//	private static final String url = "jdbc:oracle:thin:@10.1.252.69:1521:ctetl";
	
	private static final String url = "jdbc:oracle:thin:@192.168.200.100:1521";
	
	private static final String user = "lvguan";
	private static final String password = "lvguan";
	
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			boolean a = OracleConnHelper.getInstance().getConnection().getAutoCommit();
			System.out.println(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
//		 
//		String sql = "delete FROM TL_CHECKDATA_LIST WHERE LATN_ID = 83201 and month_no = 201206 and check_id in (30008001,30008003)";
//		Statement stat = OracleConnHelper.getInstance().getStatement();
//		try {
////			ResultSet set = stat.executeQuery(sql);
////			while(set.next()){
////				System.out.println(set.getString(1));
////			}
//
//			int res = stat.executeUpdate(sql);
//			System.out.println(res);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			OracleConnHelper.getInstance().close(stat);
//		}
		
		
		Connection conn = OracleConnHelper.getInstance().getConnection();
		try {
			ResultSet set = conn.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
			while(set.next()){
//				System.out.println(set.getString(1));
				String tt = set.getString("TABLE_NAME");  
				String  tp = set.getString("TABLE_TYPE");  
		            System.out.println(" 表的名称 " + tt + "   表的类型 " + tp);  
			}
			
			conn.getCatalog();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
