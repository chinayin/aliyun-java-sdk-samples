package com.aliyuncs.slb.samples.balancer;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 修改公网负载均衡实例的计费方式 https://help.aliyun.com/document_detail/27578.html
 *
 */
public class ModifyLoadBalancerInternetSpec {

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
        ModifyLoadBalancerInternetSpecRequest request = new ModifyLoadBalancerInternetSpecRequest();
        ModifyLoadBalancerInternetSpecResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例的ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey");
        // 公网实例的计费方式(可选)
//        request.setInternetChargeType("paybybandwidth");
        // 按固定带宽计费方式的公网类型实例的带宽峰值(可选)
//        request.setBandwidth(1);
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
