# 更改转发规则的目标服务器组

本示例介绍如何使用阿里云Java SDK调用SLB的 SetRule 接口，更改转发规则的目标服务器组。


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
 * 更改转发规则的目标服务器组
 *
 * @see https://help.aliyun.com/api/slb/SetRule.html
 *
 */
public class SetRule {

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
        SetRuleRequest request = new SetRuleRequest();
        SetRuleResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 转发规则ID
        request.setRuleId("");
        // 转发规则的目标服务器组ID
        request.setVServerGroupId("rsp-2zeh3ah0agnaz");

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