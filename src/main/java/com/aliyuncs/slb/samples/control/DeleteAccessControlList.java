package com.aliyuncs.slb.samples.control;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 删除访问控制策略组 https://help.aliyun.com/document_detail/70056.html
 *
 */
public class DeleteAccessControlList {

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
        DeleteAccessControlListRequest request = new DeleteAccessControlListRequest();
        DeleteAccessControlListResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 访问控制策略组ID
        request.setAclId("acl-2ze8xixlu30svjtse3tlj");

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
