# 提交媒体信息作业

本示例介绍如何使用阿里云Java SDK调用MTS的SubmitMediaInfoJob接口创建一个提交媒体信息作业。

提交媒体信息作业接口，媒体处理服务会对输入文件进行媒体信息分析，同步返回输入文件的媒体信息；可通过“查询媒体信息作业”接口得到媒体信息分析结果。

## 示例代码

**说明：**

```
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
                                "<your-region-id>",                 // 您的可用区ID
                                "<your-access-key-id>",             // 您的AccessKey ID
                                "<your-access-key-secret>"        // 您的AccessKey Secret
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
            response = getIAcsClient().getAcsResponse(request);
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

```