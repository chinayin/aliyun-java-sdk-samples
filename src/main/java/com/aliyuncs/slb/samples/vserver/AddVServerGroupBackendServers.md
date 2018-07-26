# 为指定的后端服务器组添加后端服务器

本示例介绍如何使用阿里云Java SDK调用SLB的 AddVServerGroupBackendServers 接口，为指定的后端服务器组添加后端服务器。


## 示例代码

```
package com.aliyuncs.slb.samples.vserver;

import com.alibaba.fastjson.JSONArray;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 *  为指定的后端服务器组添加后端服务器
 *
 *  @see https://help.aliyun.com/api/slb/AddVServerGroupBackendServers.html
 *
 */
public class AddVServerGroupBackendServers {

    /**
     * 创建DefaultAcsClient实例并初始化
     *
     * @return IAcsClient
     */
    private static IAcsClient getIAcsClient() {
        DefaultProfile profile = DefaultProfile.getProfile( //                "<your-region-id>",               // 您的可用区ID
                "<your-access-key-id>",
                "<your-access-key-secret>"
                );
        return new DefaultAcsClient(profile);
    }

    public static void main(String[] args) {
        // 创建API请求并设置参数
        AddVServerGroupBackendServersRequest request = new AddVServerGroupBackendServersRequest();
        AddVServerGroupBackendServersResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 服务器组的ID
        request.setVServerGroupId("rsp-2zef2msjj4noh");
        // 要添加的后端服务器列表(可选)
        JSONArray servers = new JSONArray();
        AddVServerGroupBackendServersResponse.BackendServer server = new AddVServerGroupBackendServersResponse.BackendServer();
        server.setServerId("i-2ze24rjpfgj9azofwk9d");
        server.setPort(8080);
        server.setWeight(80);
        servers.add(server);
        request.setBackendServers(servers.toJSONString());

        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("VServerGroupId: %s, BackendServersSize: %s",
                    response.getVServerGroupId(), response.getBackendServers().size()
            ));
            for (AddVServerGroupBackendServersResponse.BackendServer it : response.getBackendServers()) {
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