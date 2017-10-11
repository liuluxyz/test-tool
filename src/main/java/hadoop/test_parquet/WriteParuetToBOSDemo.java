package hadoop.test_parquet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.column.ParquetProperties.WriterVersion;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.GroupWriteSupport;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.OriginalType;
import org.apache.parquet.schema.Types;
import org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName;

public class WriteParuetToBOSDemo {
	private static final Log log = LogFactory.getLog(WriteParuetToBOSDemo.class);
	
	static org.apache.hadoop.conf.Configuration hadoopConf = new org.apache.hadoop.conf.Configuration();
	
	static{
		//下列三个配置项尽量使用配置文件配置，以适应未来的修改
//		String bucket = Configuration.getStringValue("bos.sdf.storage.bucket");
//		String ak = Configuration.getStringValue("bos.access.key");
//		String sk = Configuration.getStringValue("bos.secret.access.key");
	
		String bucket = "sdf";
		String ak = "c30d1f8960f844fd86115b2a24fb1562";
		String sk = "cc8c1460b51d42939240cc2e20e62dbb";
		hadoopConf.set("fs.defaultFS", "bos://" + bucket);
		hadoopConf.set("fs.bos.access.key", ak);
		hadoopConf.set("fs.bos.secret.access.key", sk);
		hadoopConf.set("fs.bos.endpoint", "http://bj.bcebos.com");
		hadoopConf.set("fs.bos.impl", "org.apache.hadoop.fs.bos.BaiduBosFileSystem");
	}
	
	private void writeParquetToBOS() throws IOException{
		log.info("start write...");

		MessageType schema = Types.buildMessage().required(PrimitiveTypeName.FLOAT).named("modifiedTime")
												 .required(PrimitiveTypeName.BINARY).named("id")
												 .required(PrimitiveTypeName.BINARY).as(OriginalType.UTF8).named("content")
												 .named("testParquetDemo");
		//org.apache.hadoop.fs.Path
		String fileName = "test_parquet_file";
		Path path = new Path("/dir1/dir2/"+fileName);
		log.info("file path is : " + path.getName());
		FileSystem fs = path.getFileSystem(hadoopConf);
	    if (fs.exists(path)) {
	      fs.delete(path, true);
	      log.warn("file is exist, delete it : " + path.getName());
	    }

		GroupWriteSupport.setSchema(schema, hadoopConf);
	    SimpleGroupFactory f = new SimpleGroupFactory(schema);
		ParquetWriter<Group> writer = new ParquetWriter<Group>(
				path, 
				new GroupWriteSupport(), 
				CompressionCodecName.GZIP, 
				134217728, 1048576, 512, true, true, WriterVersion.PARQUET_2_0, hadoopConf);
		int i = 0;
//		while(i++ < 100)
//			Group group = f.newGroup().append("modifiedTime", System.currentTimeMillis())
//			                          .append("id", "id"+i)
//			                          .append("content", "content"+i);			
//			writer.write(group);
//        }
//        writer.close();
//        log.info("end write...");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		writeParquetToBOS();
	}

}
