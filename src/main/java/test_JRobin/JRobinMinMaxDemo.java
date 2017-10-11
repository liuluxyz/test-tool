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
import org.jrobin.graph.RrdGraphInfo;

/**
 * liulu5
 * 2015-2-5
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-2-5 下午5:18:12
 */
public class JRobinMinMaxDemo {

	public static void test() throws RrdException, IOException {
		long start = Util.getTime(), end = start + 300 * 300;
		String rrdFile = "minmax.rrd";
		String rrdFile2 = "minmax2.rrd";
		String pngFile = "minmax.png";
		// create
		RrdDef rrdDef = new RrdDef(rrdFile, start - 1, 300);
		rrdDef.addDatasource("a", "GAUGE", 600, Double.NaN, Double.NaN);
		rrdDef.addArchive("AVERAGE", 0.5, 1, 300);
		rrdDef.addArchive("MIN", 0.5, 12, 300);
		rrdDef.addArchive("MAX", 0.5, 12, 300);
		RrdDb rrdDb = new RrdDb(rrdDef);
		// update
		for (long t = start; t < end; t += 300) {
			Sample sample = rrdDb.createSample(t);
			sample.setValue("a", Math.sin(t / 3000.0) * 50 + 50);
			sample.update();
		}
		rrdDb.exportXml("minmax.rrd.xml");
		// graph
		RrdGraphDef gDef = new RrdGraphDef();
		gDef.setTimeSpan(start, start + 86400);
		gDef.setImageFormat("png");
		gDef.setTitle("RRDTool's MINMAX.pl demo");
//		gDef.setTimeAxisLabel("time");
		gDef.setFilename("b.png");
		
		gDef.datasource("a", rrdFile, "a", "AVERAGE");
		gDef.datasource("b", rrdFile, "a", "MIN");
		gDef.datasource("c", rrdFile, "a", "MAX");
		gDef.datasource("c", rrdFile2, "a", "TOTAL");
//		gDef.datasource("c", rrdFile, "a", "MAX");
//		gDef.area("a", Color.decode("0xb6e4"), "real");
//		gDef.line("b", Color.decode("0x22e9"), "min");
		gDef.line("c", Color.decode("0xee22"), "max");
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
