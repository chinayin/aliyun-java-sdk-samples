package com.aliyuncs.slb.samples.vserver;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 *  查询已创建的服务器组
 *
 *  @see https://help.aliyun.com/api/slb/DescribeVServerGroupAttribute.html
 *
 */
public class DescribeVServerGroups {

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
        DescribeVServerGroupsRequest request = new DescribeVServerGroupsRequest();
        DescribeVServerGroupsResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例的ID
        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");

        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("VServerGroupsSize: %s",
                    response.getVServerGroups().size()
            ));
            for (DescribeVServerGroupsResponse.VServerGroup it : response.getVServerGroups()) {
                System.out.println(String.format("VServerGroupId: %s, VServerGroupName: %s",
                        it.getVServerGroupId(), it.getVServerGroupName()
                ));
            }

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
