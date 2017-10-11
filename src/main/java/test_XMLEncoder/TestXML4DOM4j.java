package test_XMLEncoder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * liulu5
 * 2014-9-4
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2014-9-4 下午6:36:29 
 */
public class TestXML4DOM4j {

	public static void test() throws Exception{
		DocumentFactory dbf = DOMDocumentFactory.getInstance();
		  Document doc = dbf.createDocument();
		  Element root = doc.addElement("DataSources");
		  for (int i = 0; i < 10; i++) {
		   Element child = root.addElement("DataSource");
		   child.addAttribute("dbName", "");
		   child.addElement("Driver");
		   child.addElement("URL");
		   child.addElement("UserName");
		   child.addElement("PassWord");
		   child.addElement("maxIdle");
		   child.addElement("maxActive");
		   child.addElement("maxWait");
		  }
		  
		  OutputFormat format = OutputFormat.createPrettyPrint();
		  format.setEncoding("GBK");
		  
		  OutputStream os = new FileOutputStream("dataSource_dom4j.xml");
		  XMLWriter xmlout = new XMLWriter(os, format);
		  xmlout.write(doc);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

