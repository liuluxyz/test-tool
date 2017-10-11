package test_exception;

import java.sql.SQLException;

import org.hibernate.HibernateException;

public class BI implements B{

	public void doWork(A a) throws HibernateException{
		try {
			a.execute();
		} catch (SQLException e) {
			throw new HibernateException(e);
		}
	}
	
	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
