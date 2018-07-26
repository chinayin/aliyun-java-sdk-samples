package com.aliyuncs.slb.samples.server;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 添加后端服务器 https://help.aliyun.com/document_detail/27632.html
 *
 */
public class AddBackendServers {

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
        AddBackendServersRequest request = new AddBackendServersRequest();
        AddBackendServersResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例的ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");
        // 要添加的后端服务器列表
        request.setBackendServers("");

        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("LoadBalancerId: %s, BackendServersSize: %s",
                    response.getLoadBalancerId(), response.getBackendServers().size()
            ));
            for (AddBackendServersResponse.BackendServer it : response.getBackendServers()) {
                System.out.println(String.format("ServerId: %s, Weight: %s",
                        it.getServerId(), it.getWeight()
                ));
            }

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
