# 查询HTTP监听配置

本示例介绍如何使用阿里云Java SDK调用SLB的 DescribeLoadBalancerHTTPListenerAttribute 接口，查询HTTP监听配置。


## 示例代码

```
package com.aliyuncs.slb.samples.listener;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 查询HTTP监听配置
 *
 * @see https://help.aliyun.com/api/slb/DescribeLoadBalancerHTTPListenerAttribute.html
 *
 */
public class DescribeLoadBalancerHTTPListenerAttribute {

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
        DescribeLoadBalancerHTTPListenerAttributeRequest request = new DescribeLoadBalancerHTTPListenerAttributeRequest();
        DescribeLoadBalancerHTTPListenerAttributeResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");
        // 负载均衡实例前端使用的端口
        request.setListenerPort(2001);

        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format(""
                    + "ListenerPort: %s, BackendServerPort: %s" + "\n"
                    + "Bandwidth: %s, Status: %s" + "\n"
                    + "XForwardedFor: %s, XForwardedFor_SLBIP: %s" + "\n"
                    + "XForwardedFor_SLBID: %s, XForwardedFor_proto: %s" + "\n"
                    + "Scheduler: %s, StickySession: %s" + "\n"
                    + "StickySessionType: %s, CookieTimeout: %s" + "\n"
                    + "Cookie: %s, AclStatus: %s" + "\n"
                    + "AclType: %s, AclId: %s" + "\n"
                    + "HealthCheck: %s, HealthCheckDomain: %s" + "\n"
                    + "HealthCheckURI: %s, HealthyThreshold: %s" + "\n"
                    + "UnhealthyThreshold: %s, HealthCheckTimeout: %s" + "\n"
                    + "HealthCheckInterval: %s, HealthCheckHttpCode: %s" + "\n"
                    + "HealthCheckConnectPort: %s, VServerGroupId: %s" + "\n"
                    + "Gzip: %s",
                    response.getListenerPort(), response.getBackendServerPort(),
                    response.getBandwidth(), response.getStatus(),
                    response.getXForwardedFor(), response.getXForwardedFor_SLBIP(),
                    response.getXForwardedFor_SLBID(), response.getXForwardedFor_proto(),
                    response.getScheduler(), response.getStickySession(),
                    response.getStickySessionType(), response.getCookieTimeout(),
                    response.getCookie(), response.getAclStatus(),
                    response.getAclType(), response.getAclId(),
                    response.getHealthCheck(), response.getHealthCheckDomain(),
                    response.getHealthCheckURI(), response.getHealthyThreshold(),
                    response.getUnhealthyThreshold(), response.getHealthCheckTimeout(),
                    response.getHealthCheckInterval(), response.getHealthCheckHttpCode(),
                    response.getHealthCheckConnectPort(), response.getVServerGroupId(),
                    response.getGzip()
            ));

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

```