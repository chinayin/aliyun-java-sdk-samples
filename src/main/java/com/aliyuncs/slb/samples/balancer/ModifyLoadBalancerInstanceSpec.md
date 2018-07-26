# 修改负载均衡的实例规格

本示例介绍如何使用阿里云Java SDK调用SLB的 ModifyLoadBalancerInstanceSpec 接口，修改负载均衡的实例规格。

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
 * 修改负载均衡的实例规格 https://help.aliyun.com/document_detail/53360.html
 *
 */
public class ModifyLoadBalancerInstanceSpec {

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
        ModifyLoadBalancerInstanceSpecRequest request = new ModifyLoadBalancerInstanceSpecRequest();
        ModifyLoadBalancerInstanceSpecResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例的ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");
        // 负载均衡实例的规格
        request.setLoadBalancerSpec("slb.s2.small");
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