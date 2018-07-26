package com.aliyuncs.slb.samples.tags;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 查询已创建的标签 https://help.aliyun.com/document_detail/42873.html
 *
 */
public class DescribeTags {

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
        DescribeTagsRequest request = new DescribeTagsRequest();
        DescribeTagsResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 负载均衡实例的ID
//        request.setLoadBalancerId("lb-2zeb619c6tveo8u7b5ey4");
//        request.setPageSize(10);
//        request.setPageNumber(1);
        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println("ResultSize: " + response.getTagSets().size());
            for (DescribeTagsResponse.TagSet it : response.getTagSets()) {
                System.out.println(String.format("TagKey: %s, TagValue: %s, InstanceCount: %s",
                        it.getTagKey(),
                        it.getTagValue(),
                        it.getInstanceCount()
                ));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
