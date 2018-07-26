package com.aliyuncs.mts.samples;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.*;

public class QueryFpShotJobList {

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
        // JobIds / 截图作业Id列表，最多一次查10个。
//        String JobIds = "<your-ids>";
        String JobIds = "f693762e8d054496a0866cfa8ccc28e4";

        // 创建API请求并设置参数
        QueryFpShotJobListRequest request = new QueryFpShotJobListRequest();
        QueryFpShotJobListResponse response;
        // JobIds
        request.setJobIds(JobIds);
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId :" + response.getRequestId());
            for (QueryFpShotJobListResponse.FpShotJob job : response.getFpShotJobList()) {
                System.out.println(String.format("Id: %s, State: %s", job.getId(), job.getState()));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
