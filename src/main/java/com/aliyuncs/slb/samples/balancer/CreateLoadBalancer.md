# 创建负载均衡实例

本示例介绍如何使用阿里云Java SDK调用SLB的CreateLoadBalancer接口 创建负载均衡实例。

**说明：**

调用该接口创建实例时，请注意：

实例创建后，会产生费用。关于负载均衡的计费说明，参见计费说明。
如果不指定实例规格（LoadBalancerSpec），则创建性能共享型实例。建议在创建负载均衡实例时，通过规格参数（LoadBalancerSpec）指定实例的规格。

## 示例代码

```
package com.aliyuncs.slb.samples.balancer;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 创建负载均衡实例 https://help.aliyun.com/document_detail/27577.html
 *
 */
public class CreateLoadBalancer {

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
        CreateLoadBalancerRequest request = new CreateLoadBalancerRequest();
        CreateLoadBalancerResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 实例的主可用区ID(可选)
        request.setMasterZoneId("cn-beijing-a");
        // 实例的备可用区ID(可选)
        request.setSlaveZoneId("cn-beijing-b");
        // 负载均衡实例的规格(可选)
        request.setLoadBalancerSpec("slb.s1.small");
        // 负载均衡实例的名称(可选)
        request.setLoadBalancerName("test-slb");
        // 负载均衡实例的网络类型(可选)
        request.setAddressType("internet");
        // 专有网络实例的所属交换机ID(可选)
//        request.setVSwitchId("");
        // 实例的计费类型(可选)
        request.setPayType("PayOnDemand");
        // 预付费公网实例的计费周期(可选)
//        request.setPricingCycle("");
        // 预付费公网实例的购买时长(可选)
//        request.setDuration(1);
        // 是否是自动支付预付费公网实例的账单(可选)
//        request.setAutoPay(false);
        // 公网实例的计费方式(可选)
//        request.setInternetChargeType("paybybandwidth");
        // 按固定带宽计费方式的公网类型实例的带宽峰值(可选)
//        request.setBandwidth(1);
        // 企业资源组ID
//        request.setResourceGroupId("");
        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("LoadBalancerId: %s, Address: (%s) %s",
                    response.getLoadBalancerId(), response.getAddressIPVersion(), response.getAddress()
            ));
            System.out.println(response);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

```