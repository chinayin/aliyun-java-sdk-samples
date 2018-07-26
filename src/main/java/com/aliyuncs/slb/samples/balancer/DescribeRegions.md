# 查询可用地域

本示例介绍如何使用阿里云Java SDK调用SLB的 DescribeRegions 接口，查询可用地域。

**说明：**


## 示例代码

```
package com.aliyuncs.slb.samples;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.slb.model.v20140515.*;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 查询可用地域 https://help.aliyun.com/document_detail/27584.html
 *
 */
public class DescribeRegions {

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
        DescribeRegionsRequest request = new DescribeRegionsRequest();
        DescribeRegionsResponse response;
        // 设置请求参数
        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println("ResultSize: " + response.getRegions().size());
            // 获取所有region信息
            for (DescribeRegionsResponse.Region region : response.getRegions()) {
                System.out.println(String.format("RegionId: %s, LocalName: %s", region.getRegionId(), region.getLocalName()));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

```