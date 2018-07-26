package com.aliyuncs.slb.samples.balancer;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 查询指定负载均衡实例的详细信息 https://help.aliyun.com/document_detail/27583.html
 *
 */
public class DescribeLoadBalancerAttribute {

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
        DescribeLoadBalancerAttributeRequest request = new DescribeLoadBalancerAttributeRequest();
        DescribeLoadBalancerAttributeResponse response;
        // 设置请求参数(可选)
//        request.setRegionId("cn-beijing");
        // 负载均衡实例的ID
        request.setLoadBalancerId("lb-2zewn7h1tp3cro3ln2qqw");
        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println(String.format("LoadBalancerId: %s, Name: %s, Status: %s",
                    response.getLoadBalancerId(), response.getLoadBalancerName(), response.getLoadBalancerStatus()
            ));
            System.out.println(response.getAddressType() + "\t" + response.getAddress() + "\t" + response.getNetworkType());
            System.out.println(response.getRegionId() + "\t" + response.getRegionIdAlias());
            System.out.println(response.getVSwitchId() + "\t" + response.getVpcId());
            System.out.println(response.getCreateTime() + "\t" + response.getCreateTimeStamp());
            System.out.println(response.getMasterZoneId() + "\t" + response.getSlaveZoneId());
            System.out.println(response.getBandwidth());
            System.out.println(response.getListenerPorts());
            for (DescribeLoadBalancerAttributeResponse.ListenerPortAndProtocol it : response.getListenerPortsAndProtocol()) {
                System.out.println(it.getListenerProtocol() + "\t" + it.getListenerPort() + "\t" + it.getForwardPort());
            }
            for (DescribeLoadBalancerAttributeResponse.BackendServer it : response.getBackendServers()) {
                System.out.println(it.getServerId() + "\t" + it.getServerIp() + "\t" + it.getType() + "\t" + it.getWeight());
            }
            //
            System.out.println(response.getEndTimeStamp());
            System.out.println(response.getInternetChargeType());
            System.out.println(response.getPayType());
            System.out.println(response.getResourceGroupId());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
