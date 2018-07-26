# 创建访问控制策略组

本示例介绍如何使用阿里云Java SDK调用SLB的 CreateAccessControlList 接口，创建访问控制策略组。

**说明：**
您可以创建多个访问控制策略组，每个策略组可包含多个IP地址条目或IP地址段条目。访问控制策略组的限制如下
* 每个地域单账号可创建的访问控制策略组个数	50
* 单账号每次可添加的IP地址条目个数	50
* 每个访问控制策略组可包含的条目个数	300
* 每个监听可绑定的访问控制策略组个数	50

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
 * 创建访问控制策略组 https://help.aliyun.com/document_detail/70015.html
 *
 */
public class CreateAccessControlList {

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
        CreateAccessControlListRequest request = new CreateAccessControlListRequest();
        CreateAccessControlListResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 访问控制策略组名称
        request.setAclName("test-name");

        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println("AclId: " + response.getAclId());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

```