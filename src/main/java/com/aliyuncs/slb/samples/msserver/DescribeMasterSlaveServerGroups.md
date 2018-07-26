# 查询已创建的主备服务器组

本示例介绍如何使用阿里云Java SDK调用SLB的 DescribeMasterSlaveServerGroups 接口，查询已创建的主备服务器组。


## 示例代码

```
package com.aliyuncs.slb.samples.msserver;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 查询已创建的主备服务器组
 *
 * @see https://help.aliyun.com/api/slb/DescribeMasterSlaveServerGroups.html
 * @see https://help.aliyun.com/document_detail/50508.html
 *
 */
public class DescribeMasterSlaveServerGroups {

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
        DescribeMasterSlaveServerGroupsRequest request = new DescribeMasterSlaveServerGroupsRequest();
        DescribeMasterSlaveServerGroupsResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");

        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("MasterSlaveBackendServersSize: %s",
                    response.getMasterSlaveServerGroups().size()
            ));
            for (DescribeMasterSlaveServerGroupsResponse.MasterSlaveServerGroup it : response.getMasterSlaveServerGroups()) {
                System.out.println(String.format("MasterSlaveServerGroupId: %s, MasterSlaveServerGroupName: %s",
                        it.getMasterSlaveServerGroupId(), it.getMasterSlaveServerGroupName()
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