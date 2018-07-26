package com.aliyuncs.slb.samples.msserver;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 查询指定主备服务器组的详细信息
 *
 * @see https://help.aliyun.com/api/slb/DescribeMasterSlaveServerGroupAttribute.html
 * @see https://help.aliyun.com/document_detail/50509.html
 *
 */
public class DescribeMasterSlaveServerGroupAttribute {

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
        DescribeMasterSlaveServerGroupAttributeRequest request = new DescribeMasterSlaveServerGroupAttributeRequest();
        DescribeMasterSlaveServerGroupAttributeResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例ID
        request.setMasterSlaveServerGroupId("rsp-2zehd021z8r3u");

        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("MasterSlaveServerGroupId: %s, MasterSlaveServerGroupName: %s, MasterSlaveBackendServersSize: %s",
                    response.getMasterSlaveServerGroupId(), response.getMasterSlaveServerGroupName(),
                    response.getMasterSlaveBackendServers().size()
            ));
            for (DescribeMasterSlaveServerGroupAttributeResponse.MasterSlaveBackendServer it : response.getMasterSlaveBackendServers()) {
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
