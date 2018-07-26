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
            response = TestClient.getIAcsClient().getAcsResponse(request);
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
