package temp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestSQL {
	
	static Connection conn;
	static{
		conn = OracleConnHelper.getInstance().getConnection();
	}
	
	private static long query(String sql){
//		System.out.println(sql);
		Statement state = null;
		ResultSet result = null;
		long takeTime = 0;
		try {
			state = conn.createStatement();
			long a = System.currentTimeMillis();
			result = state.executeQuery(sql);
			long b = System.currentTimeMillis();
//			System.out.println("time : " + (b-a));
			takeTime = b-a;
			
			ResultSetMetaData metadata = result.getMetaData();//获得属性名
			for(int i = 1; i <= metadata.getColumnCount(); ++i) {//打印属性名
//				System.out.printf("%20s", metadata.getColumnLabel(i));
			}
//			System.out.println();
			while(result.next()) {//打印查询结果
				for(int i = 1; i <= metadata.getColumnCount(); ++i) {
//					System.out.printf("%20s", result.getString(i));
				}
//				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OracleConnHelper.getInstance().close(state, result);
		}
		return takeTime;
	}
	
	/**
	 * @param args
	 * 下午3:03:25
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int times = 20;
		
		String sql = "select count(*) as y0_ from SCH_JOB_LOG this_ inner join SCH_JOB schedulejo1_ on this_.SCH_JOB_ID_=schedulejo1_.DBID_ " +
				"where this_.STATE_='SUCCESS' and lower(schedulejo1_.SN_) like '%liulu%' and schedulejo1_.DBID_ not in (select this_.OBJID_ as y0_ from SYS_DIR_OBJ this_) order by this_.START_TIME_ desc";
		
		String sql2 = "select * from " +
				"(select this_.DBID_ as DBID1_35_1_, this_.SCH_JOB_ID_ as SCH2_35_1_, this_.DATAFLOW_ID_ as DATAFLOW3_35_1_, this_.COMMANDS_ as COMMANDS4_35_1_, " +
				"this_.DATA_TIME_ as DATA5_35_1_, this_.START_TIME_ as START6_35_1_, this_.END_TIME_ as END7_35_1_, this_.STATE_ as STATE8_35_1_, " +
				"this_.MESSAGE_ as MESSAGE9_35_1_, this_.BATCH_ID_ as BATCH10_35_1_, this_.FLOW_LOG_ID_ as FLOW11_35_1_, schedulejo1_.DBID_ as DBID1_29_0_, " +
				"schedulejo1_.SN_ as SN2_29_0_, schedulejo1_.DATAFLOW_NAME_ as DATAFLOW3_29_0_, schedulejo1_.JOB_NAME_ as JOB4_29_0_, " +
				"schedulejo1_.CREATE_DATE_ as CREATE5_29_0_, schedulejo1_.DATA_INTERVAL_ as DATA6_29_0_, schedulejo1_.COMMANDS_ as COMMANDS7_29_0_, " +
				"schedulejo1_.VERSION_ as VERSION8_29_0_, schedulejo1_.IS_IN_USE_ as IS9_29_0_, schedulejo1_.DESCRIPTION_ as DESCRIP10_29_0_, " +
				"schedulejo1_.IS_VALID_ as IS11_29_0_, schedulejo1_.TIMEOUT_ as TIMEOUT12_29_0_ " +
				"from SCH_JOB_LOG this_ inner join SCH_JOB schedulejo1_ on this_.SCH_JOB_ID_=schedulejo1_.DBID_ " +
				"where this_.STATE_='SUCCESS' and lower(schedulejo1_.SN_) like '%liulu%' " +
				"and schedulejo1_.DBID_ not in (select this_.OBJID_ as y0_ from SYS_DIR_OBJ this_) order by this_.START_TIME_ desc ) where rownum <= 10";
		
		String sql3 = "select schvarlogs0_.TASK_ID_ as TASK5_1_, schvarlogs0_.DBID_ as DBID1_1_, schvarlogs0_.DBID_ as DBID1_28_0_, " +
				"schvarlogs0_.NAME_ as NAME2_28_0_, schvarlogs0_.TYPE_ as TYPE3_28_0_, schvarlogs0_.VALUE_ as VALUE4_28_0_ " +
				"from SCH_VAR_LOG schvarlogs0_ where schvarlogs0_.TASK_ID_='4277e0c5-d769-4f49-99b8-a50933a0029b'";
		
		String sql4 = "SELECT * from DF_ACT_VARIBLE WHERE ROWNUM <= 10";
		
		String sql5 = "select ACT_INST_ as ACT11_1_, DBID_ as DBID1_1_, KEY_ as KEY12_1_, DBID_ as DBID1_26_0_, SCOPE_ as SCOPE3_26_0_, " +
				"DATE_VALUE_ as DATE4_26_0_, DOUBLE_VALUE_ as DOUBLE5_26_0_, LONG_VALUE_ as LONG6_26_0_, BLOB_VALUE_ as BLOB7_26_0_, " +
				"CLASSNAME_ as CLASSNAME8_26_0_, STRING_VALUE_ as STRING9_26_0_, TEXT_VALUE_ as TEXT10_26_0_, CLASS_ as CLASS2_26_0_ " +
				"from DF_ACT_VARIBLE where  'FLOW_IN'=SCOPE_ and ACT_INST_='93b021c4-3efd-4baf-899d-511c43e81c0e'";
		
		
		long total = 0;
		for(int i=0; i<times; i++){
			long time = query(sql3);
			total += time;
			System.out.println("[" + i + "] : " + time);
		}
		System.out.println(sql);
		System.out.println("take average time : " + (total / times));
	}

}
