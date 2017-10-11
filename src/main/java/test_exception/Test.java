package test_exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;

public class Test {

	public void ex() {
		B b = new BI();
		try{
		b.doWork(new A(){

			public void execute() throws SQLException {
				System.out.println("aa");
				String a = "a";
				try{
					Integer.parseInt(a);	
				}catch(Exception e){
					throw new SQLException(e);
				}
			}
		});
		}catch(Exception ee){
			System.out.println("ee");
		}
		System.out.println("bb");
	}
	
//	public void exe(){
//		Session session = HibernateUtils.getSessionFactory().openSession(); 
//	    session.doWork( new Work() {
//			
//			public void execute(Connection connection) throws SQLException {
//				String sql = "a";
////				PreparedStatement sta = connection.prepareStatement(sql);
////				int code = sta.executeUpdate(sql);
////				log.info("execute sql[" + sql + "] return code=" + code);
////				connection.commit();
////				sta.close();
//			
//				System.out.println("work...");
//				
//			}
//		});
//
//	    System.out.println("exe...");
//	    
//	}
	
	public static void testPrint(){
		try{
			int a = Integer.parseInt("a");	
		}catch(Exception e){
			System.out.println("e : " + e);
			System.out.println("-----------------------------------");
			System.out.println("str : " + e.toString());
			System.out.println("-----------------------------------");
			System.out.println("msg : " + e.getMessage());
			System.out.println("-----------------------------------");
			e.printStackTrace();
		}
	}
	
	public static void test(){
		
		if(1 != 2)
			throw new RuntimeException("XXX");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		new Test().ex();
		testPrint();
		
	}

}
