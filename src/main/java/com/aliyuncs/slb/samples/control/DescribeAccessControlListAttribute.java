package com.aliyuncs.slb.samples.control;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 查询访问控制策略组的配置 https://help.aliyun.com/document_detail/70051.html
 *
 */
public class DescribeAccessControlListAttribute {

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
        DescribeAccessControlListAttributeRequest request = new DescribeAccessControlListAttributeRequest();
        DescribeAccessControlListAttributeResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 访问控制策略组ID
        request.setAclId("acl-2ze8xixlu30svjtse3tlj");
        // 访问控制策略组中的路由条目的说明信息(可选)
//        request.setAclEntryComment("abc");

        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("AclId: %s, AclName: %s",
                    response.getAclId(), response.getAclName()
            ));
            for (DescribeAccessControlListAttributeResponse.AclEntry it : response.getAclEntrys()) {
                System.out.println(String.format("AclEntryIP: %s, Comment: %s",
                        it.getAclEntryIP(), it.getAclEntryComment()
                ));
            }
            for (DescribeAccessControlListAttributeResponse.RelatedListener it : response.getRelatedListeners()) {
                System.out.println(String.format("LoadBalancerId: %s, AclType: %s, Protocol: %s, ListenerPort: %s",
                        it.getLoadBalancerId(), it.getAclType(), it.getProtocol(), it.getListenerPort()
                ));
            }

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
