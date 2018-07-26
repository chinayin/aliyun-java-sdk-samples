# 查询指定监听已配置的转发规则

本示例介绍如何使用阿里云Java SDK调用SLB的 DescribeRules 接口，查询指定监听已配置的转发规则。


## 示例代码

```
package com.aliyuncs.slb.samples.rule;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 查询指定监听已配置的转发规则
 *
 * @see https://help.aliyun.com/api/slb/DescribeRules.html
 *
 */
public class DescribeRules {

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
        DescribeRulesRequest request = new DescribeRulesRequest();
        DescribeRulesResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");
        // 负载均衡实例前端使用的监听端口
        request.setListenerPort(8081);

        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("RulesSize: %s",
                    response.getRules().size()
            ));
            for (DescribeRulesResponse.Rule it : response.getRules()) {
                System.out.println(String.format("RuleId: %s, RuleName: %s, "
                        + "Domain: %s, Url: %s, VServerGroupId: %s",
                        it.getRuleId(), it.getRuleName(),
                        it.getDomain(),it.getUrl(),it.getVServerGroupId()
                ));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

```