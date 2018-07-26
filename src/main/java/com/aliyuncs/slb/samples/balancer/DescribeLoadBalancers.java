package com.aliyuncs.slb.samples.balancer;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 查询已创建的负载均衡实例 https://help.aliyun.com/document_detail/27582.html
 *
 */
public class DescribeLoadBalancers {

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
        DescribeLoadBalancersRequest request = new DescribeLoadBalancersRequest();
        DescribeLoadBalancersResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例的ID(可选)
//        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");
        // 负载均衡实例的名称(可选)
//        request.setLoadBalancerName("");
//        request.setAddressType("");
//        request.setNetworkType("");
//        request.setVpcId("");
//        request.setVSwitchId("");
//        request.setAddress("");
//        request.setServerIntranetAddress("");
//        request.setInternetChargeType("");
//        request.setServerId("");
//        request.setMasterZoneId("");
//        request.setSlaveZoneId("");
//        request.setTags("test");
        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            for (DescribeLoadBalancersResponse.LoadBalancer it : response.getLoadBalancers()) {
                System.out.println(String.format("LoadBalancerId: %s, Name: %s, Status: %s",
                        it.getLoadBalancerId(), it.getLoadBalancerName(), it.getLoadBalancerStatus()
                ));
                System.out.println(it.getAddressType() + "\t" + it.getAddress() + "\t" + it.getNetworkType());
                System.out.println(it.getRegionId() + "\t" + it.getRegionIdAlias());
                System.out.println(it.getVSwitchId() + "\t" + it.getVpcId());
                System.out.println(it.getCreateTime() + "\t" + it.getCreateTimeStamp());
                System.out.println(it.getMasterZoneId() + "\t" + it.getSlaveZoneId());
                //
                System.out.println(it.getEndTimeStamp());
                System.out.println(it.getInternetChargeType());
                System.out.println(it.getPayType());
                System.out.println(it.getRenewalCycUnit());
                System.out.println(it.getRenewalDuration());
                System.out.println(it.getRenewalStatus());
                System.out.println(it.getResourceGroupId());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
