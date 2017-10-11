package test_XMLEncoder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * liulu5
 * 2015-2-3
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2015-2-3 下午5:18:59
 */
public class XmlReader {

	public static void read() {
		InputStream is = null;
		try {
			is = new FileInputStream(new File("C:/Users/liulu5/Desktop/gmond.xml"));
//			is.
			BufferedReader reader = new BufferedReader(new FileReader(new File("C:/Users/liulu5/Desktop/gmond.xml")));
			StringBuffer aa = new StringBuffer();
			String str;
			while ((str = reader.readLine()) != null) {
                aa.append(str);
            }
            reader.close();
            
            Document doc = null;
            if(aa.toString().contains("<driver>")){
            	String b = aa.toString().replaceAll("<driver>", "&lt;driver&gt;");
            	System.out.println(b);
            	
            	 InputStream ii = new ByteArrayInputStream(b.getBytes());
            	 
            	doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(ii);
            }
            
//			String a = new String(is.toString()); 
//			System.out.println(a);
			
			
//			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			Element root = doc.getDocumentElement();
			NodeList clusters = root.getChildNodes();
			if (clusters != null) {
				for (int i = 0; i < clusters.getLength(); i++) {//轮询集群
					Node cluster = clusters.item(i);
					if (cluster.getNodeType() == Node.ELEMENT_NODE) {
						// （7）取得节点的属性值
						String clusterName = cluster.getAttributes().getNamedItem("NAME").getNodeValue();
						System.out.println(clusterName);
						
						//轮询主机
						for (Node host = cluster.getFirstChild(); host != null; host = host.getNextSibling()) {
							if (host.getNodeType() == Node.ELEMENT_NODE) {
								if (host.getNodeName().equals("HOST")) {
									String hostName = host.getAttributes().getNamedItem("NAME").getNodeValue();
									System.out.println(hostName);
								}
								
								//轮询metric
								for (Node metric = host.getFirstChild(); metric != null; metric = metric.getNextSibling()) {
									if (metric.getNodeType() == Node.ELEMENT_NODE) {
										if (metric.getNodeName().equals("METRIC")) {
											String metricName = metric.getAttributes().getNamedItem("NAME").getNodeValue();
											System.out.println(metricName);
										}
									}
								}
								
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		read();
	}

}
