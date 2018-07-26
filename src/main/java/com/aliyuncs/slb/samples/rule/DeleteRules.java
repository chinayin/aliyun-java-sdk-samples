package com.aliyuncs.slb.samples.rule;

import com.alibaba.fastjson.JSONArray;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 删除转发规则
 *
 * @see https://help.aliyun.com/api/slb/DeleteRules.html
 *
 */
public class DeleteRules {

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
        DeleteRulesRequest request = new DeleteRulesRequest();
        DeleteRulesResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 要删除的转发规则列表
        JSONArray array = new JSONArray();
        array.add("rule-3ejhktkae1");
        request.setRuleIds(array.toJSONString());

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
