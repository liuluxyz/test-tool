package test_XMLEncoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
//import com.sun.xml.internal.stream.XMLOutputFactoryImpl;
//import com.sun.xml.internal.txw2.output.XMLWriter;
import org.dom4j.io.XMLWriter;

/**
 * liulu5
 * 2014-9-4
 */
/**
 * @Description: TODO
 * @author liulu5
 * @date 2014-9-4 下午5:34:40 
 */
public class TestXml {

	public static void test() throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument(); 

		//下面是建立XML文档内容的过程，
		//先建立根元素"学生花名册" 
		Element root = doc.createElement("学生花名册"); 
		//根元素添加上文档 
		doc.appendChild(root); 
		//建立"学生"元素，添加到根元素 
		Element student = doc.createElement("学生"); 
		student.setAttribute("性别", "男"); 
		root.appendChild(student); 
		//建立"姓名"元素，添加到学生下面，下同 
		Element name = doc.createElement("姓名"); 
		student.appendChild(name); 
		Text tName = doc.createTextNode("刘某"); 
		name.appendChild(tName);
		Element age = doc.createElement("年龄"); 
		student.appendChild(age); 
		Text tAge = doc.createTextNode(String.valueOf(22)); 
		age.appendChild(tAge);
		
//		FileOutputStream out = new FileOutputStream("student.xml");
//		out.write(doc.getTextContent().getBytes());
//		out.close();

//		TransformerFactory tFactory = TransformerFactory.newInstance();
//		Transformer transformer = tFactory.newTransformer();
//		transformer.setOutputProperty(OutputKeys.INDENT, "yes");// 设置自动换行
//		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");  
//		
//		DOMSource source = new DOMSource(doc);
//		StreamResult result = new StreamResult(new java.io.File("student.xml"));
//		transformer.transform(source, result); 

		
		OutputStream os = new FileOutputStream("student.xml");
		  XMLWriter xmlout = new XMLWriter(os);
		  xmlout.write(doc);
		  
//		OutputFormat format = new OutputFormat(doc, "", true);
//        format.setEncoding("UTF-8");
//        //xml输出器
//        StringWriter out = new StringWriter();
////        XMLWriter xmlWriter = new XMLWriter(out);
//        XMLWriter xmlWriter = new XMLWriter(out, format);
//        try {
//            xmlWriter.write(doc);
//            xmlWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//       return out.toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

