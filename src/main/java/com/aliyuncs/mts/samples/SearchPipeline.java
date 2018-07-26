package com.aliyuncs.mts.samples;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.*;

public class SearchPipeline {

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
        // PageNumber / 当前页号，从第1页开始，默认值是1
        long PageNumber = 1;
        // PageSize / 分页查询时设置的每页行数页面大小，默认值是10,上限100。
        long PageSize = 100;
        // State / 管道状态：All、Active、Paused、Deleted，默认是All
        String State = "All";
        // 创建API请求并设置参数
        SearchPipelineRequest request = new SearchPipelineRequest();
        SearchPipelineResponse response;
        //
        request.setPageNumber(PageNumber);
        request.setPageSize(PageSize);
        request.setState(State);
        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId :" + response.getRequestId());
            System.out.println(String.format("TotalCount: %s", response.getTotalCount()));
            for (SearchPipelineResponse.Pipeline pipe : response.getPipelineList()) {
                System.out.println(String.format("Id: %s, Name: %s", pipe.getId(), pipe.getName()));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
