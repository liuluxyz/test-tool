package test_bos;

import java.io.File;
import java.util.Date;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.AppendObjectRequest;
import com.baidubce.services.bos.model.BosObject;
import com.baidubce.services.bos.model.BosObjectSummary;
import com.baidubce.services.bos.model.ListObjectsResponse;
import com.baidubce.services.bos.model.PutObjectResponse;

public class Test {

	private String ak = "6f5b3bf04469446f91a6a885c370b66d";
	private String sk = "f5a9af94b7fb48bb9189bdbe2f624830";
	private BosClient client = null;
	
	private void initBosClient(){
		BosClientConfiguration config = new BosClientConfiguration();
	    config.setCredentials(new DefaultBceCredentials(ak, sk));
	    client = new BosClient(config);
	}
	
	private void append(){

		String fileName = "/temp/test";
		String file = "C:\\Users\\liulu\\Desktop\\danei_sogou_keyword_2017-06-28.parquet";
//		this.client.appendObject.appendObject("clm-sdf", fileName, file);
	
		
		ListObjectsResponse res = this.client.listObjects("clm-sdf", "temp");
		String filename2 = res.getContents().get(0).getKey();
		System.out.println(filename2);
		
		System.out.println(client.getObject("clm-sdf", "/temp/test"));
		System.out.println(client.getObject("clm-sdf", "temp/test"));
		
		long offset = client.getObject("clm-sdf", "/temp/test").getObjectMetadata().getAppendOffset();
		System.out.println("offset : " + offset);
		
		AppendObjectRequest re = new AppendObjectRequest("clm-sdf", filename2, new File(file));
		re.setOffset(offset);
		
		client.appendObject(re);
	}
	
	private void delete() {
//		String filePre = "/" + groupData.getClientCode() + "/" + groupData.getChannel() + "/" + groupData.getInterfaceName() + "/" + groupData.getDt();
//		String fileName = filePre + "/" + dateFormat.format(new Date()) + ".parquet";
   	
		String filePre = "/wuzhou/shenma/creative_report/2017-05-31";
//		BosObject bosObj = this.client.getObject("clm-sdf", filePre);
//		System.out.println(bosObj.getKey());
//		System.out.println(bosObj.toString());
//		System.out.println(bosObj.getObjectMetadata());
		
		ListObjectsResponse objs = this.client.listObjects("clm-sdf", "wuzhou/shenma/creative_report/2017-05-31");
		System.out.println(objs.getContents().size());
		for(BosObjectSummary obj : objs.getContents()){
//			System.out.println(obj.getKey());
			System.out.println(obj.toString());
		}
		
//		this.client.deleteObject("clm-sdf", filePre);
		
//		if(listObjs.getContents() != null && listObjs.getContents().size() > 0){
//			this.bosClient.deleteObject(this.storeBucket, filePre);    		
//		}
   	
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Test test = new Test();
		test.initBosClient();
		
		test.append();
		
	}

}
