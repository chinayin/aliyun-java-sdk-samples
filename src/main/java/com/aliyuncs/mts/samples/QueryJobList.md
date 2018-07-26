# 查询转码作业

本示例介绍如何使用阿里云Java SDK调用MTS的QueryJobList接口查询转码作业。

通过转码作业ID，批量查询转码作业，返回默认按CreationTime降序排列。

在执行前，您需要获取以下信息：

-   JobIds

    转码作业ID列表，逗号分隔，一次最多10个。


## 示例代码

**说明：** 运行该示例代码将查询转码作业，返回默认按CreationTime降序排列。

```
package com.aliyuncs.mts.samples;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.*;

public class QueryJobList {

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
        // JobIds / 转码作业ID列表，逗号分隔，一次最多10个
        String JobIds = "<your-job-ids>";

        // 创建API请求并设置参数
        QueryJobListRequest request = new QueryJobListRequest();
        QueryJobListResponse response;
        // 转码作业ID列表，逗号分隔，一次最多10个
        request.setJobIds(JobIds);
        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId :" + response.getRequestId());
            for (QueryJobListResponse.Job job : response.getJobList()) {
                System.out.println(String.format("JobId: %s, PipelineId: %s", job.getJobId(), job.getPipelineId()));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

```