package com.aliyuncs.slb.samples.rule;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 查询指定转发规则的配置详情
 *
 * @see https://help.aliyun.com/api/slb/DescribeRuleAttribute.html
 *
 */
public class DescribeRuleAttribute {

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
        DescribeRuleAttributeRequest request = new DescribeRuleAttributeRequest();
        DescribeRuleAttributeResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 转发规则ID
        request.setRuleId("rule-3ejhktkae1");

        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("RuleName: %s, LoadBalancerId: %s, ListenerPort: %s, "
                    + "Domain: %s, Url: %s, VServerGroupId: %s",
                    response.getRuleName(), response.getLoadBalancerId(), response.getListenerPort(),
                    response.getDomain(), response.getUrl(), response.getVServerGroupId()
            ));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
