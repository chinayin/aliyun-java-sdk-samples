package com.aliyuncs.slb.samples.listener;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 创建UDP监听
 *
 * @see https://help.aliyun.com/api/slb/CreateLoadBalancerUDPListener.html
 *
 */
public class CreateLoadBalancerUDPListener {

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
        CreateLoadBalancerUDPListenerRequest request = new CreateLoadBalancerUDPListenerRequest();
        CreateLoadBalancerUDPListenerResponse response;
//        SetLoadBalancerUDPListenerAttributeRequest request = new SetLoadBalancerUDPListenerAttributeRequest();
//        SetLoadBalancerUDPListenerAttributeResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");
        // 负载均衡实例前端使用的端口
        request.setListenerPort(1001);
        // 负载均衡实例后端使用的端口
        request.setBackendServerPort(2001);
        // 监听的带宽峰值
        request.setBandwidth(2);
        // (可选)
//        request.setVServerGroupId("");
//        request.setMasterSlaveServerGroupId("");
//        request.setScheduler("wlc");
//        request.setPersistenceTimeout(10);
//        request.setAclStatus("");
//        request.setAclId("");
//        request.setAclType("");
//        request.setHealthCheckConnectPort(80);
//        request.setHealthyThreshold(2);
//        request.setUnhealthyThreshold(2);
//        request.setHealthCheckConnectTimeout(10); //todo
//        request.setHealthCheckInterval(1);
//        request.setHealthCheckReq("");
//        request.setHealthCheckExp("");

        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
