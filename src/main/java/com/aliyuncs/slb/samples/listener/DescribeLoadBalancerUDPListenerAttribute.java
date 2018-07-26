package com.aliyuncs.slb.samples.listener;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 查询UDP监听配置
 *
 * @see https://help.aliyun.com/api/slb/DescribeLoadBalancerUDPListenerAttribute.html
 *
 */
public class DescribeLoadBalancerUDPListenerAttribute {

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
        DescribeLoadBalancerUDPListenerAttributeRequest request = new DescribeLoadBalancerUDPListenerAttributeRequest();
        DescribeLoadBalancerUDPListenerAttributeResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");
        // 负载均衡实例前端使用的端口
        request.setListenerPort(1001);

        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format(""
                    + "ListenerPort: %s, BackendServerPort: %s" + "\n"
                    + "Bandwidth: %s, Status: %s" + "\n"
                    + "Scheduler: %s, AclStatus: %s" + "\n"
                    + "AclType: %s, AclId: %s" + "\n"
                    + "HealthCheck: %s,  HealthyThreshold: %s" + "\n"
                    + "UnhealthyThreshold: %s, HealthCheckTimeout: %s" + "\n"
                    + "HealthCheckInterval: %s, VServerGroupId: %s" + "\n",
                    response.getListenerPort(), response.getBackendServerPort(),
                    response.getBandwidth(), response.getStatus(),
                    response.getScheduler(), response.getAclStatus(),
                    response.getAclType(), response.getAclId(),
                    response.getHealthCheck(), response.getHealthyThreshold(),
                    response.getUnhealthyThreshold(), response.getHealthCheckConnectTimeout(),
                    response.getHealthCheckInterval(), response.getVServerGroupId()
            ));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
