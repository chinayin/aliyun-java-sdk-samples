# 查询指定地域的可用区信息

本示例介绍如何使用阿里云Java SDK调用SLB的 DescribeZones 接口，查询指定地域的可用区信息。

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
 * 查询指定地域的可用区信息 https://help.aliyun.com/document_detail/27585.html
 *
 */
public class DescribeZones {

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
        DescribeZonesRequest request = new DescribeZonesRequest();
        DescribeZonesResponse response;
        // 设置请求参数
        request.setRegionId("cn-beijing");
        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId: " + response.getRequestId());
            System.out.println("ResultSize: " + response.getZones().size());
            // 获取所有可用区信息
            for (DescribeZonesResponse.Zone zone : response.getZones()) {
                System.out.println(String.format("ZoneId: %s, LocalName: %s, SlaveZones.size: %d",
                        zone.getZoneId(),
                        zone.getLocalName(),
                        zone.getSlaveZones().size()));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

```