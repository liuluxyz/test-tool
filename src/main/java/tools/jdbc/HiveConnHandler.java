package tools.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 代码未完成
 * @Description: TODO
 * @author liulu
 * @date 2017年12月19日 下午7:35:32
 */
public class HiveConnHandler extends ConnHandler{
	
	private static final Log log = LogFactory.getLog(HiveConnHandler.class);
	
//	private static final String hiveserverDefaultIP = "localhost";
//	private static final String driver = "org.apache.hadoop.hive.jdbc.HiveDriver";
//	private static final String url = "jdbc:hive://192.168.11.70:10000/default";
	
	private static final String driver = "org.apache.hive.jdbc.HiveDriver";
	private static final String url = "jdbc:hive2://180.76.148.212:10000/default;auth=noSasl";
	private static final String user = "hive";
	private static final String password = "";
	
	private static HiveConnHandler hiveConnHelper;
	
	private HiveConnHandler(){
		init();
	}
	
	public static HiveConnHandler getInstance(){
		if(hiveConnHelper == null){
			hiveConnHelper = new HiveConnHandler();
		}
		return hiveConnHelper;
	}
	
	public void init(){
		try {
			Class.forName(driver);
//			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			LOG.error("[HiveConnHelper.java -- init]:" + e.toString());
//		}
	}
	
	public Connection getConnection(){
		log.info("start getConnection...");
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("[HiveConnHelper.java -- getConnection]", e);
		}
		return null;
	}
	
	public Connection getConnection(String hiveserverIp, int port){
		log.info("start getConnection...");
		log.info("hive server ip is : " + hiveserverIp);
		try {
			String url = "jdbc:hive2://" + hiveserverIp + ":" + port + "/default";
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			LOG.error("[HiveConnHelper.java -- getConnection]", e);
		}
		return null;
	}
	
	public Connection getConnection(String hiveserverIp){
		log.info("start getConnection...");
		log.info("hive server ip is : " + hiveserverIp);
		try {
			String url = "jdbc:hive2://" + hiveserverIp + ":10000/default";
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("[HiveConnHelper.java -- getConnection]", e);
		}
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
