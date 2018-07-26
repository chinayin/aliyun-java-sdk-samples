package com.aliyuncs.slb.samples.vserver;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 修改虚拟服务器组的配置，并添加后端服务器 https://help.aliyun.com/document_detail/35217.html
 *
 */
public class SetVServerGroupAttribute {

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
        SetVServerGroupAttributeRequest request = new SetVServerGroupAttributeRequest();
        SetVServerGroupAttributeResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 服务器组的ID
        request.setVServerGroupId("rsp-2zef2msjj4noh");
        //服务器组名称(可选)
        request.setVServerGroupName("test-vserver-name0725");
        // 要添加的后端服务器列表(可选)
//        request.setBackendServers();

        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("VServerGroupId: %s, VServerGroupName: %s, BackendServersSize: %s",
                    response.getVServerGroupId(), response.getVServerGroupName(), response.getBackendServers().size()
            ));
            for (SetVServerGroupAttributeResponse.BackendServer it : response.getBackendServers()) {
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
