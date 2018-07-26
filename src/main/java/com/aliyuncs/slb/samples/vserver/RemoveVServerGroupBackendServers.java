package com.aliyuncs.slb.samples.vserver;

import com.alibaba.fastjson.JSONArray;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 为指定的后端服务器组添加后端服务器 https://help.aliyun.com/document_detail/35215.html
 *
 */
public class RemoveVServerGroupBackendServers {

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
        RemoveVServerGroupBackendServersRequest request = new RemoveVServerGroupBackendServersRequest();
        RemoveVServerGroupBackendServersResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 服务器组的ID
        request.setVServerGroupId("rsp-2zef2msjj4noh");
        // 要添加的后端服务器列表
        JSONArray servers = new JSONArray();
        RemoveVServerGroupBackendServersResponse.BackendServer server = new RemoveVServerGroupBackendServersResponse.BackendServer();
        server.setServerId("i-2ze24rjpfgj9azofwk9d");
        server.setPort(8080);
        servers.add(server);
        request.setBackendServers(servers.toJSONString());

        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("VServerGroupId: %s, BackendServersSize: %s",
                    response.getVServerGroupId(), response.getBackendServers().size()
            ));
            for (RemoveVServerGroupBackendServersResponse.BackendServer it : response.getBackendServers()) {
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
