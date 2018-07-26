# 删除负载均衡实例

本示例介绍如何使用阿里云Java SDK调用SLB的 DeleteLoadBalancer 接口，删除负载均衡实例。

**说明：**
如果负载均衡实例上还有监听或者绑定了相应的标签，也会一并被删除。

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
 * 删除负载均衡实例 https://help.aliyun.com/document_detail/27579.html
 *
 */
public class DeleteLoadBalancer {

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
        DeleteLoadBalancerRequest request = new DeleteLoadBalancerRequest();
        DeleteLoadBalancerResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例的ID
        request.setLoadBalancerId("lb-2zevovo4zge0y26j2rbse");
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