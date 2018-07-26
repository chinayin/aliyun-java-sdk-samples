package com.aliyuncs.slb.samples.server;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 查询后端服务器的健康状态 https://help.aliyun.com/document_detail/27635.html
 *
 */
public class DescribeHealthStatus {

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
        DescribeHealthStatusRequest request = new DescribeHealthStatusRequest();
        DescribeHealthStatusResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例的ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");
        // 负载均衡实例前端使用的端口
//        request.setListenerPort(80);

        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            for (DescribeHealthStatusResponse.BackendServer it : response.getBackendServers()) {
                System.out.println(String.format("ServerId: %s, Weight: %s",
                        it.getServerId(), it.getServerHealthStatus()
                ));
            }

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
