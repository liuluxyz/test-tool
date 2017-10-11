package tools.updatemaven.test_download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

/**
 * when execute maven to compile project, eclipse can't download some dependent jar or other file, this tool is used to
 * download the files manually and put them into local maven repository.
 * the input parameters for main function are the download log from maven compiling like below:
 * 
 * 输入参数如下：
 * Downloading: http://people.apache.org/~garyh/mvn/org/slf4j/slf4j-jdk14/1.5.6/slf4j-jdk14-1.5.6.jar
 * Downloading: http://10.1.253.223:8081/nexus/content/repositories/plugins/org/slf4j/slf4j-jdk14/1.5.6/slf4j-jdk14-1.5.6.jar
 * Downloading: https://repository.cloudera.com/content/repositories/cdh-releases-rcs/org/slf4j/slf4j-jdk14/1.5.6/slf4j-jdk14-1.5.6.jar
 * Downloading: http://repo1.maven.org/maven2/org/slf4j/slf4j-jdk14/1.5.6/slf4j-jdk14-1.5.6.jar
 * 
 * 直接下载输入的参数中对应的url
 * @author liulu5
 *
 */
public class DownFile {

	private static int BUFFER_SIZE = 1024; // 缓冲区大小

	private final ArrayList<String> fileNameSuccess = new ArrayList<String>();
	private final ArrayList<String> fileNameFail = new ArrayList<String>();
	
	static String[] MavenRepository = new String[]{
		"http://10.1.253.223:8081/nexus/content/repositories/snapshots",
		"http://10.1.253.223:8081/nexus/content/repositories/releases",
		"http://10.1.253.223:8081/nexus/content/repositories/others",
		"http://10.1.253.223:8081/nexus/content/repositories/plugins",
		
		"http://maven.net.cn/content/groups/public",
		"https://maven.atlassian.com/content/groups/public/",
		"http://repo1.maven.org/maven2",
		"http://repo2.maven.org/maven2",
		"http://download.java.net/maven/2",
		"http://www.datanucleus.org/downloads/maven2",
		"https://www.escidoc.org/artifactory/maven.jahia.org",
		"http://people.apache.org/~stack/m2/repository",
		"http://people.apache.org/~garyh/mvn",
		
		"http://repository.codehaus.org",
		"http://repository.jboss.org/maven2",
		"http://repository.jboss.org/nexus/content/groups/public",
		"http://repository.jboss.org/nexus/content/groups/public-jboss",
		
		"https://repository.apache.org/content/repositories/snapshots",
		"https://repository.apache.org/content/repositories/releases",

		"https://repository.cloudera.com/artifactory/libs-snapshot-local",
		"https://repository.cloudera.com/artifactory/cloudera-repos",
		"https://repository.cloudera.com/artifactory/public",
		
		"https://repository.cloudera.com/content/groups/cdh-releases-rcs",
		"https://repository.cloudera.com/content/groups/cdh-build",
		"https://repository.cloudera.com/content/repositories/third-party",
		"https://repository.cloudera.com/content/repositories/cdh-releases-rcs"
	};
	
	public boolean saveToFile(String destUrl, String targetPutUrl) {

		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		byte[] buf = new byte[BUFFER_SIZE];

		try {
			url = new URL(destUrl);
		} catch (MalformedURLException e) {
//			e.printStackTrace();
			System.out.println(destUrl + "资源URL语法错误，请检查字符串是否正确！");
			return false;
		}

		try {
			httpUrl = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("打开到 " + destUrl + "所引用的远程对象的连接失败");
			return false;
		}

		Properties prop = System.getProperties();
		prop.setProperty("http.proxyHost", "proxy.asiainfo.com");
		prop.setProperty("http.proxyPort", "8080");
		prop.setProperty("http.proxyUser", "liulu5");
		prop.setProperty("http.proxyPassword", "2wsx@WSX");
		
		try {
			httpUrl.connect();
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("打开到此 " + destUrl + " 引用的资源的通信链接失败");
			return false;
		}

		try {
			bis = new BufferedInputStream(httpUrl.getInputStream());
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("取得连接的Input流失败");
			return false;
		}
		File file = new File(targetPutUrl);
		BufferedOutputStream fileOut = null;
		try {
			fileOut = new BufferedOutputStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			System.out.println(file + "在本地保存文件失败");
			e.printStackTrace();
			return false;
		}

		try {
			while (true) {
				int bytesIn = bis.read(buf, 0, 1024);
				if (bytesIn == -1) {
					break;
				} else {
					fileOut.write(buf, 0, bytesIn);
				}
			}
			fileOut.flush();
			fileOut.close();
		} catch (Exception ee) {
//			ee.printStackTrace();
			System.out.println(file + "保存文件过程失败");
			return false;
		}
		System.out.println(destUrl + " 下载完毕");
		return true;
	}
	
	private String parseMavenRepository(String url){
		for(String rep : MavenRepository){
			if(url.startsWith(rep)){
				return rep;
			}
		}
		return "";
	}
	
	private boolean mkdirs(String url){
		File file = new File(url);
		if(file.exists()){
			return true;
		}else{
			return file.mkdirs();
		}
	}
	
	public static void main(String[] args) throws IOException {
		DownFile d = new DownFile();
		int num = 0;
		for(String url : args){
			System.out.println("downloading : " + (++num) + " in " + args.length);
			if("Downloading:".equals(url) || "Downloading".equals(url.trim())){
				continue;
			}
			String fileName = url.substring(url.lastIndexOf("/"));
			if(d.fileNameSuccess.contains(fileName)){
				continue;
			}
			
			String repository = d.parseMavenRepository(url);
			if(repository == null || "".equals(repository)){
				System.err.println("missing repository : " + url);
				continue;
			}
			String targetPutUrl = url.replaceAll(repository, "D:/maven/repository");
			d.mkdirs(targetPutUrl.substring(0, targetPutUrl.lastIndexOf("/")));
			
			boolean res = d.saveToFile(url, targetPutUrl);
			if(res == true){
				d.fileNameSuccess.add(fileName);
			}else{
				d.fileNameFail.add(fileName);
			}
		}
		System.out.println("download statistic: total input:[" + args.length + "], success:[" + d.fileNameSuccess.size() + "], failed:[" + d.fileNameFail.size() + "]");
	}
}
