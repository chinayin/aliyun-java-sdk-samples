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
                "<your-region-id>",
                "<your-access-key-id>",
                "<your-access-key-secret>"
        );
        return new DefaultAcsClient(profile);
    }

    public static void main(String[] args) {
        // JobIds / 转码作业ID列表，逗号分隔，一次最多10个
//        String JobIds = "<your-job-ids>";
        String JobIds = "f693762e8d054496a0866cfa8ccc28e4";

        // 创建API请求并设置参数
        QueryJobListRequest request = new QueryJobListRequest();
        QueryJobListResponse response;
        // 转码作业ID列表，逗号分隔，一次最多10个
        request.setJobIds(JobIds);
        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
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
