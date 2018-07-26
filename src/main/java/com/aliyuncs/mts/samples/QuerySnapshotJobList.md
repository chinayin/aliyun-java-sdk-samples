# 查询截图作业

本示例介绍如何使用阿里云Java SDK调用MTS的QuerySnapshotJobList接口查询截图作业。

查询媒体列表。

在执行前，您需要获取以下信息：

-   SnapshotJobIds

    截图作业Id列表，最多一次查10个。


## 示例代码

**说明：** 运行该示例代码将查询查询截图作业。

```
package com.aliyuncs.mts.samples;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.*;

public class QuerySnapshotJobList {

    /**
     * 创建DefaultAcsClient实例并初始化
     *
     * @return IAcsClient
     */
    private static IAcsClient getIAcsClient() {
        DefaultProfile profile = DefaultProfile.getProfile(
                                "<your-region-id>",                 // 您的可用区ID
                                "<your-access-key-id>",             // 您的AccessKey ID
                                "<your-access-key-secret>"        // 您的AccessKey Secret
        );
        return new DefaultAcsClient(profile);
    }

    public static void main(String[] args) {
        // SnapshotJobIds / 截图作业Id列表，最多一次查10个。
        String SnapshotJobIds = "<your-ids>";

        // 创建API请求并设置参数
        QuerySnapshotJobListRequest request = new QuerySnapshotJobListRequest();
        QuerySnapshotJobListResponse response;
        // SnapshotJobIds
        request.setSnapshotJobIds(SnapshotJobIds);
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId :" + response.getRequestId());
            for (QuerySnapshotJobListResponse.SnapshotJob job : response.getSnapshotJobList()) {
                System.out.println(String.format("Id: %s, State: %s", job.getId(), job.getState()));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

```