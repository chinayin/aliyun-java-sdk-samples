package com.aliyuncs.slb.samples.vserver;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 *  创建虚拟服务器组，并添加后端服务器
 *
 *  @see https://help.aliyun.com/api/slb/CreateVServerGroup.html
 *
 */
public class CreateVServerGroup {

    /**
     * 创建DefaultAcsClient实例并初始化
     *
     * @return IAcsClient
     */
    private static IAcsClient getIAcsClient() {
        DefaultProfile profile = DefaultProfile.getProfile(
                "<your-region-id>",
                "<your-access-key-id>",
                "<your-access-key-secret>"
        );
        return new DefaultAcsClient(profile);
    }

    public static void main(String[] args) {
        // 创建API请求并设置参数
        CreateVServerGroupRequest request = new CreateVServerGroupRequest();
        CreateVServerGroupResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例的ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");
        //服务器组名称(可选)
        request.setVServerGroupName("test-vserver-name");
        // 要添加的后端服务器列表(可选)
//        request.setBackendServers();

        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("VServerGroupId: %s, BackendServersSize: %s",
                    response.getVServerGroupId(), response.getBackendServers().size()
            ));
            for (CreateVServerGroupResponse.BackendServer it : response.getBackendServers()) {
                System.out.println(String.format("ServerId: %s(%s), Port: %s, Weight: %s",
                        it.getServerId(), it.getType(), it.getPort(), it.getWeight()
                ));
            }

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
