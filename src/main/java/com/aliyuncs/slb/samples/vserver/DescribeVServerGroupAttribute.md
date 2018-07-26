# 查询服务器组的详细信息

本示例介绍如何使用阿里云Java SDK调用SLB的 DescribeVServerGroupAttribute 接口，查询服务器组的详细信息。


## 示例代码

```
package com.aliyuncs.slb.samples.vserver;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 *  查询服务器组的详细信息
 *
 *  @see https://help.aliyun.com/api/slb/DescribeVServerGroupAttribute.html
 */
public class DescribeVServerGroupAttribute {

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
        DescribeVServerGroupAttributeRequest request = new DescribeVServerGroupAttributeRequest();
        DescribeVServerGroupAttributeResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 服务器组的ID
        request.setVServerGroupId("rsp-2zef2msjj4noh");

        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("VServerGroupId: %s,VServerGroupName: %s, BackendServersSize: %s",
                    response.getVServerGroupId(), response.getVServerGroupName(), response.getBackendServers().size()
            ));
            for (DescribeVServerGroupAttributeResponse.BackendServer it : response.getBackendServers()) {
                System.out.println(String.format("ServerId: %s(%s), Port: %s, Weight: %s",
                        it.getServerId(), it.getType(), it.getPort(), it.getWeight()
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