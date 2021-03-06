package test_JRobin;

import org.jrobin.core.FetchData;
import org.jrobin.core.FetchRequest;
import org.jrobin.core.RrdDb;
import org.jrobin.core.Util;

/**
 * liulu5
 * 2015-2-2
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-2-2 上午11:14:45 
 */
public class TestFetchData {

	/**
	 * 除根据RrdDef以外获取RrdDb的其他方法
	 */
	private void getRrdDbMethod(String rootPath, String rrdName) {
		try {

			// 根据RRD文件获取RrdDb
			String rrdFullPath = rootPath + "/" + rrdName;
			RrdDb rrdDb = new RrdDb(rrdFullPath);
			System.out.println("[info:]" + rrdDb.getInfo() + "[path:]" + rrdDb.getPath());
			rrdDb.close();

			// 根据XML文件获取RrdDb
//			rrdDb = new RrdDb(rootPath + "copy.rrd", rootPath + "demo_flow.rrd.xml");
//			System.out.println("[info:]" + rrdDb.getInfo() + "[path:]" + rrdDb.getPath());
//			rrdDb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
     *  
     */
	private void fetchRrdData(String rootPath, String rrdName) {
		try {
			// open the file
			RrdDb rrd = new RrdDb(rootPath + rrdName);

			// create fetch request using the database reference
			FetchRequest request = rrd.createFetchRequest("AVERAGE",
					Util.getTimestamp(2010, 10 - 1, 1),
					Util.getTimestamp(2010, 10 - 1, 2));

			System.out.println("[requet dump:]" + request.dump());

			// filter the datasources you really need
			// String[] filterDataSource = { "input", "output" };
			// request.setFilter(filterDataSource);

			// if you want only the "input" datasource use:
			// request.setFilter("input");

			// execute the request
			FetchData fetchData = request.fetchData();
			int columnCount = fetchData.getColumnCount();
			int rowCount = fetchData.getRowCount();
			long[] timestamps = fetchData.getTimestamps();
			System.out.println("[data column count:]" + columnCount);
			System.out.println("[data row count:]" + rowCount);

			// System.out.println("[fetch data dump:]" + fetchData.dump());
			// 循环获取数据
			double[][] values = fetchData.getValues();
			StringBuffer buffer = new StringBuffer("");
			for (int row = 0; row < rowCount; row++) {
				buffer.append(timestamps[row]);
				buffer.append(":  ");
				for (int dsIndex = 0; dsIndex < columnCount; dsIndex++) {
					buffer.append(Util.formatDouble(values[dsIndex][row]));
					buffer.append("  ");
				}
				buffer.append("\n");
			}
			System.out.println("[fetch data display :]\n" + buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TestFetchData test = new TestFetchData();
		String rootPath = "C:/Users/liulu5/Desktop/jrobin/";
		String rrdName = "demo_flowa.rrd";
		
		test.getRrdDbMethod(rootPath, rrdName);
	}

}

