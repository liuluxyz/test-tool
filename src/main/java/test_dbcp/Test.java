package test_dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * liulu5
 * 2013-12-3
 */
public class Test {

	public static void testConn(){
		Connection conn = null;
		Statement state = null;
		ResultSet set = null;
		try {
			conn = ConnectionSource.getConnection();
			state = conn.createStatement();
			set = state.executeQuery("select count(1) from dbs");
			while(set.next()){
				int num = set.getInt(1);
				System.out.println("get count : " + num);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(set != null){
					set.close();	
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
			}	
		}
	}
	
	public static void testThread(){
		new Thread(){
			public void run(){
				int num = 0;
				while(true){
					System.out.println("test while : " + (num++));
					testConn();
					System.out.println("-----------------------------------------");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testThread();
		
	}

}

