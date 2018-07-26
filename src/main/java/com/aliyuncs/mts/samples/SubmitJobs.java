package com.aliyuncs.mts.samples;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.*;

public class SubmitJobs {

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
        // Oss Output Object / 输出文件名,路径
        String OssOutputObject = "output.mp4";
        // Template Id / 输出模板ID
        String TemplateId = "0074cd0290214818b767872f160216c3";
        // Pipeline Id / 管道ID
        String PipelineId = "2d9e8b9733a94ebfae95a889804e10f7";

        // Oss region / 输出Bucket所在地域
//        String OssLocation = "<your-oss-location>";
//        // Oss bucket / 输出Bucket
//        String OssBucket = "<your-oss-bucket>";
//        // Oss Input Object / 输入文件名,路径
//        String OssInputObject = "<your-oss-input-object>";
//        // Oss Output Object / 输出文件名,路径
//        String OssOutputObject = "<your-oss-output-object>";
//        // Template Id / 输出模板ID
//        String TemplateId = "<your-template-id>";
//        // Pipeline Id / 管道ID
//        String PipelineId = "<your-pipeline-id>";
        // 创建API请求并设置参数
        SubmitJobsRequest request = new SubmitJobsRequest();
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
        // Output / Outputs由Output列表构成，JSON数组，大小上限为30
        String outputOSSObject;
        try {
            outputOSSObject = URLEncoder.encode(OssOutputObject, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("output URL encode failed");
        }
        JSONObject output = new JSONObject();
        output.put("OutputObject", outputOSSObject);
        // Ouput->Container / 输出的容器类型（文件格式）。视频支持mp4、flv、ts、m3u8，音频支持mp3、mp4等。
        JSONObject container = new JSONObject();
        container.put("Format", "mp4");
        output.put("Container", container.toJSONString());
        // Ouput->Video / 输出的视频参数。例如编码格式、码率、宽、高、帧率等。
        // https://help.aliyun.com/document_detail/29226.html?spm=a2c4g.11186623.6.628.VcmQDE
        JSONObject video = new JSONObject();
        video.put("Codec", "H.264");
        video.put("Bitrate", "1500");
        video.put("Width", "1280");
        video.put("Fps", "25");
        output.put("Video", video.toJSONString());
        // Ouput->Audio / 输出的音频参数
        JSONObject audio = new JSONObject();
        audio.put("Codec", "AAC");
        audio.put("Bitrate", "128");
        audio.put("Channels", "2");
        audio.put("Samplerate", "44100");
        output.put("Audio", audio.toJSONString());
        // Ouput->TemplateId / 输出的模板参数
        output.put("TemplateId", TemplateId);
        JSONArray outputs = new JSONArray();
        outputs.add(output);
        request.setOutputs(outputs.toJSONString());
        request.setOutputBucket(OssBucket);
        request.setOutputLocation(OssLocation);
        // PipelineId / 管道ID，管道的定义详见术语表；若需要异步通知，须保证此管道绑定了可用的消息主题。
        request.setPipelineId(PipelineId);
        // 发起请求并处理应答或异常
        SubmitJobsResponse response;
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId :" + response.getRequestId());
            if (response.getJobResultList().get(0).getSuccess()) {
                System.out.println("JobId :" + response.getJobResultList().get(0).getJob().getJobId());
            } else {
                System.out.println("SubmitJobs Failed Code :" + response.getJobResultList().get(0).getCode()
                        + " Message:" + response.getJobResultList().get(0).getMessage());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
