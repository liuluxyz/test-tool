package test_bmr;

import java.util.ArrayList;
import java.util.UUID;

import com.baidubce.BceServiceException;
import com.baidubce.services.bmr.BmrClient;
import com.baidubce.services.bmr.model.InstanceGroup;
import com.baidubce.services.bmr.model.ListInstanceGroupsRequest;
import com.baidubce.services.bmr.model.ListInstanceGroupsResponse;

public class Test {

	public void listInstanceGroups(BmrClient client, String clusterId) {
	    try {
	        // 方法 1. 罗列指定集群ID相关的实例组
	        ListInstanceGroupsResponse response1 = client.listInstanceGroups(clusterId);
	        // 方法 2. 自定义ListInstanceGroupsRequest对象的查询请求 
	        ListInstanceGroupsResponse response2 = client.listInstanceGroups(new ListInstanceGroupsRequest().withClusterId(clusterId));
	        for (InstanceGroup instanceGroup: response2.getInstanceGroups()) {
	            System.out.println(instanceGroup.getId() + "," +  instanceGroup.getType()
	                    + "," + instanceGroup.getInstanceType() + "," + instanceGroup.getRequestedInstanceCount());
	        }
	    } catch (BceServiceException e){
	        System.out.println("List instance group failed: " + e.getErrorMessage());
	    }
	}
	
//	public void modifyInstanceGroups(BmrClient bmrClient, String clusterId, String coreGroupId, String taskGroupId) {
//	    // 可省略无须修改的实例组
//	    List<ModifyInstanceGroupConfig> instanceGroups = new ArrayList<ModifyInstanceGroupConfig>();
//	    // core group
//	    ModifyInstanceGroupConfig coreGroup = new ModifyInstanceGroupConfig();
//	    coreGroup.setId(coreGroupId);
//	    coreGroup.setInstanceCount(4);
//	    instanceGroups.add(coreGroup);
//	    // task group
//	    ModifyInstanceGroupConfig taskGroup = new ModifyInstanceGroupConfig();
//	    taskGroup.setId(taskGroupId);
//	    taskGroup.setInstanceCount(2);
//	    instanceGroups.add(taskGroup);
//
//	    String clientToken = UUID.randomUUID().toString();
//	    try {
//	        bmrClient.modifyInstanceGroups(
//	                new ModifyInstanceGroupRequest()
//	                        .withClusterId(clusterId)
//	                        .withInstanceGroups(instanceGroups)
//	                        .withClientToken(clientToken)
//	        );
//	    } catch (BceServiceException e) {
//	        System.out.println("Modify instance groups failed: " + e.getErrorMessage());
//	    }
//	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
