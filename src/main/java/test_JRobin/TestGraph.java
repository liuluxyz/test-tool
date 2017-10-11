package test_JRobin;

import java.awt.Color;
import java.io.IOException;

import org.jrobin.core.RrdDb;
import org.jrobin.core.RrdDef;
import org.jrobin.core.RrdException;
import org.jrobin.core.Sample;
import org.jrobin.core.Util;
import org.jrobin.graph.RrdGraph;
import org.jrobin.graph.RrdGraphDef;

/**
 * liulu5
 * 2015-2-5
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-2-5 下午6:18:41 
 */
public class TestGraph {

	public static void test() throws RrdException, IOException {
		String pngFile = "cpu.png";
		String rrdFile = "C:/Users/liulu5/Desktop/jrobin/hmc/hdm182/cpu_user.rrd";
		RrdDb rrd = new RrdDb(rrdFile);
		long end = rrd.getLastUpdateTime();
		long start = end - 300 * 300;
		
		// graph
		RrdGraphDef gDef = new RrdGraphDef();
		gDef.setTimeSpan(start, start + 86400);
		gDef.setImageFormat("png");
		gDef.setTitle("RRDTool's cpu_user demo");
		gDef.setFilename("C:/Users/liulu5/Desktop/jrobin/hmc/cpu.png");
		gDef.datasource("sum", rrdFile, "sum", "AVERAGE");
		gDef.area("sum", Color.decode("0xb6e4"), "real");
//		gDef.line("b", Color.decode("0x22e9"), "min");
//		gDef.line("c", Color.decode("0xee22"), "max");
		RrdGraph graph = new RrdGraph(gDef);
//		graph.saveAsPNG(pngFile, 450, 0);
//		graph.getRrdGraphInfo().dump();
//		RrdGraph rrdGraph = new RrdGraph(gDef);
//		RrdGraphInfo info = rrdGraph.getRrdGraphInfo();
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			test();
		} catch (RrdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

