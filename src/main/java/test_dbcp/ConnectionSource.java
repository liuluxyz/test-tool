package test_dbcp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class ConnectionSource {
	
	private static BasicDataSource dataSource = null;

	public ConnectionSource() {
	}

	public static void init() {

		if (dataSource != null) {
			try {
				dataSource.close();
			} catch (Exception e) {
				//
			}
			dataSource = null;
		}

		try {
			Properties p = new Properties();
			p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
			p.setProperty("url", "jdbc:mysql://localhost/ctetl");
			p.setProperty("password", "root");
			p.setProperty("username", "root");
			
			p.setProperty("initialSize", "10");
			p.setProperty("maxActive", "10");
			p.setProperty("minIdle", "10");
			p.setProperty("maxIdle", "30");
			p.setProperty("maxWait", "1000");
			p.setProperty("removeAbandoned", "false");
			p.setProperty("removeAbandonedTimeout", "30");
			p.setProperty("validationQuery", "select 1");
			p.setProperty("validationQueryTimeout", "1");
			p.setProperty("testWhileIdle", "false");
			p.setProperty("timeBetweenEvictionRunsMillis", "30000");
			p.setProperty("numTestsPerEvictionRun", "10");
			p.setProperty("minEvictableIdleTimeMillis", "1800000");
			p.setProperty("testOnBorrow", "false");
			p.setProperty("testOnReturn", "false");
//			p.setProperty("logAbandoned", "true");

			dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);

		} catch (Exception e) {
			//
		}
	}

	public static synchronized Connection getConnection() throws SQLException {
		if (dataSource == null) {
			init();
		}
		Connection conn = null;
		if (dataSource != null) {
			conn = dataSource.getConnection();
			int idle = dataSource.getNumIdle();
			int active = dataSource.getNumActive();
			System.out.println("active : " + active);
			System.out.println("idle : " + idle);
		}
		return conn;
	}
}
