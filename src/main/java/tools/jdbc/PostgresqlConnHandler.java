package tools.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 代码未完成
 * @Description: TODO
 * @author liulu
 * @date 2017年12月19日 下午7:35:57
 */
public class PostgresqlConnHandler extends ConnHandler {

	private static void test(){
		try{
            Class.forName("org.postgresql.Driver").newInstance();  
            String connectUrl ="jdbc:postgresql://180.76.167.47:5432/liulu";  
            Connection conn = DriverManager.getConnection(connectUrl, "postgres", "postgres");  
            Statement st = conn.createStatement();  
//            String sql = " SELECT * from testsqoop;";  
            String sql = " SELECT 1";
            ResultSet rs = st.executeQuery(sql);  
            while(rs.next()){  
                System.out.println(rs.getString(4));  
            }  
            rs.close();  
            st.close();  
            conn.close();  
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

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

}

