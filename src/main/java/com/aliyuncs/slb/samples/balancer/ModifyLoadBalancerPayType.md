# 将后付费实例转换为预付费实例

本示例介绍如何使用阿里云Java SDK调用SLB的 ModifyLoadBalancerPayType 接口，将后付费实例转换为预付费实例。

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
 * 将后付费实例转换为预付费实例 https://help.aliyun.com/document_detail/59589.html
 *
 */
public class ModifyLoadBalancerPayType {

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
        ModifyLoadBalancerPayTypeRequest request = new ModifyLoadBalancerPayTypeRequest();
        ModifyLoadBalancerPayTypeResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例的ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");
        // 负载均衡实例的计费方式
        request.setPayType("PrePay");
        // 计费周期
        request.setPricingCycle("month");
        // 计费时长
        request.setDuration(1);
        // 是否自动付费(可选)
        request.setAutoPay(false);
        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

```