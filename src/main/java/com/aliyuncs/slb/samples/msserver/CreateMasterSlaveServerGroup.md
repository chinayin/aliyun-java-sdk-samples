# 创建主备服务器组

本示例介绍如何使用阿里云Java SDK调用SLB的 CreateMasterSlaveServerGroup 接口，创建主备服务器组。


## 示例代码

```
package com.aliyuncs.slb.samples.msserver;

import com.alibaba.fastjson.JSONArray;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 创建主备服务器组
 *
 * @see https://help.aliyun.com/api/slb/CreateMasterSlaveServerGroup.html
 * @see https://help.aliyun.com/document_detail/50506.html
 *
 */
public class CreateMasterSlaveServerGroup {

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
        CreateMasterSlaveServerGroupRequest request = new CreateMasterSlaveServerGroupRequest();
        CreateMasterSlaveServerGroupResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");
        // 主备服务器组名称(可选)
        request.setMasterSlaveServerGroupName("lbs-master-slave2");
        // 主备服务器组列表(可选)
        JSONArray servers = new JSONArray();
        // M
        CreateMasterSlaveServerGroupResponse.MasterSlaveBackendServer server
                = new CreateMasterSlaveServerGroupResponse.MasterSlaveBackendServer();
        server.setServerId("i-2ze24rjpfgj9azofwk9d");
        server.setPort(8080);
        server.setWeight(80);
        server.setServerType("Master");
        servers.add(server);
        request.setMasterSlaveBackendServers(servers.toJSONString());
        // S
        server = new CreateMasterSlaveServerGroupResponse.MasterSlaveBackendServer();
        server.setServerId("i-2zecaq06nvmq0pb99feh");
        server.setPort(8080);
        server.setWeight(80);
        server.setServerType("Slave");
        servers.add(server);
        request.setMasterSlaveBackendServers(servers.toJSONString());

        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("MasterSlaveServerGroupId: %s, MasterSlaveBackendServersSize: %s",
                    response.getMasterSlaveServerGroupId(), response.getMasterSlaveBackendServers().size()
            ));
            for (CreateMasterSlaveServerGroupResponse.MasterSlaveBackendServer it : response.getMasterSlaveBackendServers()) {
                System.out.println(String.format("ServerId: %s(%s), Port: %s, Weight: %s(%s)",
                        it.getServerId(), it.getType(), it.getPort(), it.getWeight(), it.getServerType()
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