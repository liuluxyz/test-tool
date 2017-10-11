package test_JRobin;

import java.io.IOException;

import org.jrobin.core.DsDef;
import org.jrobin.core.DsTypes;
import org.jrobin.core.RrdDb;
import org.jrobin.core.RrdDef;
import org.jrobin.core.RrdException;
import org.jrobin.core.Sample;
import org.jrobin.core.Util;

/**
 * liulu5
 * 2015-2-4
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-2-4 下午4:22:57 
 */
public class Test {

	private RrdDef createRrdDef(String rootPath, String rrdName, long startTime) {
		try {

			String rrdPath = rootPath + rrdName;
//			RrdDef rrdDef = new RrdDef(rrdPath, startTime - 1, 300);
			
			
			// DsTypes: GAUGE COUNTER DERIVE ABSOLUTE
//			DsDef dsDef = new DsDef("output", DsTypes.DT_COUNTER, 600, 0, Double.NaN);
//			rrdDef.addDatasource(dsDef);
//			rrdDef.addArchive("AVERAGE", 0.5, 1, 5);

			RrdDb rrdDb = new RrdDb(rrdPath);
			
			rrdDb.exportXml(rootPath + "/xx/" + rrdName + ".xml");
			
			
			Sample sample = rrdDb.createSample(System.currentTimeMillis() / 1000);
			double tmpval = Math.random();
			double tmpval2 = Math.random();
			sample.setValue("output", tmpval2 + 50);
			sample.update();
			
			rrdDb.exportXml(rootPath + rrdName + "2.xml");
			
//			return rrdDef;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 通过RrdDef创建RRD文件并初始化数据
	 */
	private void createRRDInitData(long startTime, long endTime,
			String rootPath, String rrdName, RrdDef rrdDef) {
		try {

			RrdDb rrdDb = new RrdDb(rrdDef);
			// / by this point, rrd file can be found on your disk

			// 模拟一些测试数据
			// Math.sin(2 * Math.PI * (t / 86400.0)) * baseval;
			int baseval = 50;
			for (long t = startTime; t < endTime; t += 300) {
				Sample sample = rrdDb.createSample(t);
				double tmpval = Math.random() * baseval;
				double tmpval2 = Math.random() * baseval;
				sample.setValue("input", tmpval + 50);
				sample.setValue("output", tmpval2 + 50);
				sample.update();
			}
			System.out.println("[RrdDb init data success]");
			System.out.println("[Rrd path]:" + rrdDef.getPath());

			// rrdDb.dumpXml(rootPath + rrdName + "_rrd.xml")
			rrdDb.exportXml(rootPath + rrdName + ".xml");

			// If your RRD files are updated rarely, open them only when
			// necessary and close them as soon as possible.
			rrdDb.close();

			System.out.println("[RrdDb export xml success]");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	private static void getRRDInfo(){
		String path = "C:/Users/liulu5/Desktop/jrobin/hmc/hdm182/cpu_num.rrd";
		RrdDb rrdDb = null;
		while(true){
			try {
				rrdDb = new RrdDb(path);
//				System.out.println(rrdDb.getInfo());
				System.out.println(rrdDb.getLastUpdateTime());
			} catch (IOException e) {
			} catch (RrdException e) {
			}finally{
				if(rrdDb != null && !rrdDb.isClosed()){
					try {
						rrdDb.close();
					} catch (IOException e) {
					}
				}
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
		}
	}
	
	private static void exportRRDInfo(){
		String path = "D:/project/HMC/doc/hmc-web-doc/jrobin/hmc/rrds/HDPNameNode/hdm182/load_one.rrd";
		try {
			RrdDb rrdDb = new RrdDb(path);
			rrdDb.exportXml("D:/project/HMC/doc/hmc-web-doc/jrobin/load_one.rrd.xml");
		} catch (IOException e) {
		} catch (RrdException e) {
		}finally{
		}
	}
	
	private static void updateValue(){
		String rootPath = "D:/project/HMC/doc/hmc-web-doc/jrobin/";
		String rrdName = "demo_flow.rrd";
		
		try {
			RrdDb rrdDb = new RrdDb(rootPath + rrdName);
			Sample sample = rrdDb.createSample(System.currentTimeMillis());
			sample.setValue("input", 111);
			sample.update();
			
			rrdDb.exportXml(rootPath + rrdName + ".xml");
			rrdDb.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RrdException e) {
			e.printStackTrace();
		}	
	}
	
	private static void fetchData() {
		String rootPath = "D:/project/HMC/doc/hmc-web-doc/jrobin/";
		String rrdName = "demo_flow.rrd";
		try {
			// 根据RRD文件获取RrdDb
			String rrdFullPath = rootPath + "/" + rrdName;
			RrdDb rrdDb = new RrdDb("D:/project/HMC/doc/hmc-web-doc/jrobin/demo_flow.rrd");
			System.out.println("[info:]" + rrdDb.getInfo() + "[path:]" + rrdDb.getPath());
			
			System.out.println(rrdDb.getLastDatasourceValue("input"));
			System.out.println(rrdDb.getLastDatasourceValue("output"));
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		long startTime = Util.getTimestamp(2010, 10 - 1, 1);
//		long endTime = Util.getTimestamp(2010, 11 - 1, 1);
//
//		Test test = new Test();
//		String rootPath = "C:/Users/liulu5/Desktop/jrobin/";
//		String rrdName = "a.rrd";
//		// 测试创建RrdDef
//		RrdDef rrdDef = test.createRrdDef(rootPath, rrdName, startTime);
		
//		exportRRDInfo();
		
//		updateValue();
		
		exportRRDInfo();
		
	}

}

