# 查询已创建的访问控制策略组

本示例介绍如何使用阿里云Java SDK调用SLB的 DescribeAccessControlLists 接口，查询已创建的访问控制策略组。


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
 * 查询已创建的访问控制策略组 https://help.aliyun.com/document_detail/70052.html
 *
 */
public class DescribeAccessControlLists {

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
        DescribeAccessControlListsRequest request = new DescribeAccessControlListsRequest();
        DescribeAccessControlListsResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 访问控制策略组名称(可选)
//        request.setAclName("");

        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            for (DescribeAccessControlListsResponse.Acl it : response.getAcls()) {
                System.out.println(String.format("AclEntryIP: %s, Comment: %s, AddressIPVersion: %s",
                        it.getAclId(), it.getAclName(), it.getAddressIPVersion()
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