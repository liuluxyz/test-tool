package hadoop.test_parquet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.bos.BaiduBosFileSystem;
import org.apache.parquet.column.ParquetProperties.WriterVersion;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.ExampleParquetWriter;
import org.apache.parquet.hadoop.example.GroupReadSupport;
import org.apache.parquet.hadoop.example.GroupWriteSupport;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.OriginalType;
import org.apache.parquet.schema.PrimitiveType.PrimitiveTypeName;
import org.apache.parquet.schema.Types;

public class TestWriteRead {

	static Configuration conf = new Configuration();
	static {
        conf = new Configuration();
//		try {
//			InputStream in = new FileInputStream("/home/cubead_read/liulu/tool/conf/core-site.xml");
//			conf.addResource(in);
//			in = new FileInputStream("/home/cubead_read/liulu/tool/conf/hdfs-site.xml");
//			conf.addResource(in);	
//			in = new FileInputStream("/home/cubead_read/liulu/tool/conf/mapred-site.xml");
//			conf.addResource(in);
//			in = new FileInputStream("/home/cubead_read/liulu/tool/conf/yarn-site.xml");
//			conf.addResource(in);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        System.out.println(conf.get("fs.defaultFS"));
        
//        conf.set("fs.defaultFS", "file://d:/data/");
//        conf.set("fs.defaultFS", "bos://ng391fcde-master-instance-9ujtbl4j.novalocal:8020");
        
        conf.set("fs.defaultFS", "bos://biaowang");
        conf.set("fs.bos.endpoint", "http://bj.bcebos.com");
        conf.set("fs.bos.impl", "org.apache.hadoop.fs.bos.BaiduBosFileSystem");
        conf.set("fs.bos.access.key", "c30d1f8960f844fd86115b2a24fb1562");
        conf.set("fs.bos.secret.access.key", "cc8c1460b51d42939240cc2e20e62dbb");
        
//        conf.set("dfs.nameservices", ClusterName);
//        conf.set("dfs.ha.namenodes."+ClusterName, "nn1,nn2");
//        conf.set("dfs.namenode.rpc-address."+ClusterName+".nn1", "172.16.50.24:8020");
//        conf.set("dfs.namenode.rpc-address.ng391fcde-master-instance-9ujtbl4j.novalocal", "180.76.168.163:8020");
//        conf.set("dfs.client.failover.proxy.provider."+ClusterName, 
//        		"org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
    
        System.out.println(conf.get("fs.defaultFS"));
	}
	
	public static void createFile1() throws Exception {
		MessageType FILE_SCHEMA = Types.buildMessage().required(PrimitiveTypeName.INT32).named("id")
													.required(PrimitiveTypeName.BINARY).as(OriginalType.UTF8).named("name")
													.named("test");
//		String file = "./data/test_parquet_file.parquet";
		
//		String preName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//文件名前缀
		String preName = "temp";
		
		String file = "/temp/" + preName + "_test.parquet";
		Path path = new Path(file);
//		File fileCheck = new File(file);
//		if(fileCheck.exists())
//			fileCheck.delete();
		FileSystem fs = path.getFileSystem(conf);
		
	    if (fs.exists(path)) {
	      fs.delete(path, true);
	      System.out.println("delete ...");
	    }
		
		GroupWriteSupport.setSchema(FILE_SCHEMA, conf);
	    SimpleGroupFactory f = new SimpleGroupFactory(FILE_SCHEMA);
		ParquetWriter<Group> writer = new ParquetWriter<Group>(path, new GroupWriteSupport(), CompressionCodecName.GZIP, 
				134217728, 1048576, 512, true, true, WriterVersion.PARQUET_2_0, conf);
		for (int i = 0; i < 100000; i++) {
          writer.write(
              f.newGroup()
              .append("id", i)
              .append("name", UUID.randomUUID().toString()));
        }
        writer.close();
	}
	
	public static void read() throws IOException{
		Path path = new Path("");
		GroupReadSupport readSupport = new GroupReadSupport();
        ParquetReader<Group> reader = new ParquetReader<Group>(path,readSupport);

        Group result = reader.read();
        System.out.println(result);
	}
	
	public static void createFile2() throws Exception {
		String file = "./data/test_parquet_file8.parquet";
		MessageType FILE_SCHEMA = Types.buildMessage()
				.required(PrimitiveTypeName.INT32).named("id")
		      .required(PrimitiveTypeName.BINARY).as(OriginalType.UTF8).named("name")
		      .named("test");
		Path path = new Path(file);
		
		File fileCheck = new File(file);
		if(fileCheck.exists())
			fileCheck.delete();
		
//		FileSystem fs = path.getFileSystem(conf);
//	    if (fs.exists(path)) {
//	      fs.delete(path, true);
//	    }
	    SimpleGroupFactory f = new SimpleGroupFactory(FILE_SCHEMA);
		ParquetWriter<Group> writer = ExampleParquetWriter.builder(path)
				.withConf(conf)
		        .withType(FILE_SCHEMA)
		        .build();
		for (int i = 0; i < 100; i++) {
			Group group = f.newGroup();
			group.add("id", i);
			group.add("name", UUID.randomUUID().toString());
			writer.write(group);
        }
        writer.close();
	}
	
	public static void createLocalFile() throws Exception {
		String file = "data/test_empty.parquet";
		MessageType FILE_SCHEMA = Types.buildMessage()
				.required(PrimitiveTypeName.INT32).named("id")
		      .required(PrimitiveTypeName.BINARY).as(OriginalType.UTF8).named("name")
		      .named("test");
		Path path = new Path(file);
		
		File fileCheck = new File(file);
		if(fileCheck.exists())
			fileCheck.delete();

	    SimpleGroupFactory f = new SimpleGroupFactory(FILE_SCHEMA);
		ParquetWriter<Group> writer = ExampleParquetWriter.builder(path)
				.withConf(new Configuration())
		        .withType(FILE_SCHEMA)
		        .build();
//		for (int i = 0; i < 100; i++) {
//			Group group = f.newGroup();
//			group.add("id", i);
//			group.add("name", UUID.randomUUID().toString());
//			writer.write(group);
//        }
        writer.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			createFile1();
			createLocalFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
