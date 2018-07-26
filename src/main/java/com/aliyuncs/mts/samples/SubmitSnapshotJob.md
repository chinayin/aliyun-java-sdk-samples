# 提交截图作业

本示例介绍如何使用阿里云Java SDK调用MTS的SubmitSnapshotJob接口创建一个提交截图作业。

提交截图作业接口，目前支持生成jpg格式图片。

- 同步模式：接口同步返回截图结果，截图在接口返回时就已经生成到对应的Bucket。
- 异步模式：不保证接口返回时截图已经生成，截图任务将进入后台排队，异步执行截图。只要设置了Interval和Num中的任何一个参数，就表示异步模式。
- 消息通知：提交截图作业时，只要指定了PipelineId参数，完成后将发送异步消息。

## 示例代码

**说明：**

```
package com.aliyuncs.mts.samples;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.*;

public class SubmitSnapshotJob {

    /**
     * 创建DefaultAcsClient实例并初始化
     *
     * @return IAcsClient
     */
    private static IAcsClient getIAcsClient() {
        DefaultProfile profile = DefaultProfile.getProfile(
                //                "<your-region-id>",                 // 您的可用区ID
                //                "<your-access-key-id>",             // 您的AccessKey ID
                "<your-access-key-secret>"
                "cn-beijing", "LTAIVX5Vgpw6HEGb", "SYE8ToJAKs6TCY8l9IeGC8ezR7B8pg"
        );
        return new DefaultAcsClient(profile);
    }

    public static void main(String[] args) {
        // Params
        // Oss region / 输出Bucket所在地域
        String OssLocation = "oss-cn-beijing";
        // Oss bucket / 输入Bucket
        String OssInputBucket = "test-mts2018";
        // Oss bucket / 输出Bucket
        String OssOutputBucket = "test-mts2018";
        // Oss Input Object / 输入文件名,路径
        String OssInputObject = "input.mp4";
        // Oss Output Object / 输出文件名,路径
        String OssOutputObject = "example.jpg";

        // 创建API请求并设置参数
        SubmitSnapshotJobRequest request = new SubmitSnapshotJobRequest();
        SubmitSnapshotJobResponse response;
        // Input / 作业输入，JSON对象
        request.setInput(generateSnapshotInput(OssLocation, OssInputBucket, OssInputObject).toJSONString());
        // SnapshotConfig / 截图配置，Json对象
        // https://help.aliyun.com/document_detail/29251.html?spm=a2c4g.11186623.6.685.QjplDY
        request.setSnapshotConfig(generateSnapshotConfig(OssLocation, OssOutputBucket, OssOutputObject, "5 ",
                "20", "10", "intra", "200", "200").toJSONString());
        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId :" + response.getRequestId());
            System.out.println(String.format("Id: %s, State: %s", response.getSnapshotJob().getId(),
                    response.getSnapshotJob().getState()));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject generateSnapshotInput(String ossLocation,
            String inputBucket, String inputObject) {
        JSONObject input = new JSONObject();
        input.put("Location", ossLocation);
        input.put("Bucket", inputBucket);
        input.put("Object", inputObject);
        return input;
    }

    private static JSONObject generateSnapshotConfig(String osslocation, String outputbucket,
            String outputobject, String time, String interval, String num,
            String frametype, String width, String height) {
        JSONObject snapshotConfig = new JSONObject();
        JSONObject output = generateSnapshotInput(osslocation, outputbucket, outputobject);
        snapshotConfig.put("OutputFile", output);
        snapshotConfig.put("Time", time);
        snapshotConfig.put("Interval", interval);
        snapshotConfig.put("Num", num);
        snapshotConfig.put("FrameType", frametype);
        snapshotConfig.put("Width", width);
        snapshotConfig.put("Height", height);
        return snapshotConfig;
    }

}

```