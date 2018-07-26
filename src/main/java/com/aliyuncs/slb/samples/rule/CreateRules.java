package com.aliyuncs.slb.samples.rule;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 为指定的HTTP/HTTPS监听添加转发规则
 *
 * @see https://help.aliyun.com/api/slb/CreateRules.html
 *
 */
public class CreateRules {

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
        CreateRulesRequest request = new CreateRulesRequest();
        CreateRulesResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");
        // 负载均衡实例前端使用的监听端口
        request.setListenerPort(8081);
        // 要添加的转发规则
        JSONArray array = new JSONArray();
        JSONObject rule = new JSONObject();
        rule.put("RuleName", "test-rule");
        rule.put("VServerGroupId", "rsp-2zeh3ah0agnaz");
//        rule.put("Domain", "");
//        rule.put("Url", "");
        array.add(rule);
        request.setRuleList(array.toJSONString());

        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("RulesSize: %s",
                    response.getRules().size()
            ));
            for (CreateRulesResponse.Rule it : response.getRules()) {
                System.out.println(String.format("RuleId: %s, RuleName: %s",
                        it.getRuleId(), it.getRuleName()
                ));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
