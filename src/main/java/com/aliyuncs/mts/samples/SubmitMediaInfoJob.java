package com.aliyuncs.mts.samples;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.*;

public class SubmitMediaInfoJob {

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
        // Params
        // Oss region / 输出Bucket所在地域
        String OssLocation = "oss-cn-beijing";
        // Oss bucket / 输出Bucket
        String OssBucket = "test-mts2018";
        // Oss Input Object / 输入文件名,路径
        String OssInputObject = "input.mp4";

        // 创建API请求并设置参数
        SubmitMediaInfoJobRequest request = new SubmitMediaInfoJobRequest();
        SubmitMediaInfoJobResponse response;
        // Input / 作业输入，JSON对象
        JSONObject input = new JSONObject();
        input.put("Location", OssLocation);
        input.put("Bucket", OssBucket);
        try {
            input.put("Object", URLEncoder.encode(OssInputObject, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("input URL encode failed");
        }
        request.setInput(input.toJSONString());
        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId :" + response.getRequestId());
            System.out.println(String.format("JobId: %s, State: %s", response.getMediaInfoJob().getJobId(),
                    response.getMediaInfoJob().getState()));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
