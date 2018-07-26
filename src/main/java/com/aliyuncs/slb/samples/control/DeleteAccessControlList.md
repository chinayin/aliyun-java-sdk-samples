# 删除访问控制策略组

本示例介绍如何使用阿里云Java SDK调用SLB的 DeleteAccessControlList 接口，删除访问控制策略组。

**说明：**
只有当要删除的访问控制策略组没有绑定任何监听时，才可以删除。

## 示例代码

```
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