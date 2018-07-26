# 提交转码作业

本示例介绍如何使用阿里云Java SDK调用MTS的SubmitJobs接口创建一个提交转码作业。

提交转码作业接口，一个转码输出会生成一个转码作业，接口返回转码作业列表。作业会添加到管道中被调度执行，执行完成后需要调用“查询转码作业”接口轮询作业执行结果，也可使用异步通知机制。

使用预置模板对输入文件进行转码，须要先调用“提交模板分析作业”接口（SubmitAnalysisJob），分析作业成功完成后可以通过调用“查询模板分析作业”接口（QueryAnalysisJobList）获取该输入文件的可用预置模版列表。若提交的转码作业中指定的预置模板不在可用的预置模板列表中，则转码作业会失败。

在执行作业前，您需要获取以下信息：

-   模板ID

    调用QueryTemplateList接口查看要使用的模板 ID。

-   管道ID

    调用QueryPipelineList接口查看要使用的管道 ID。

## 示例代码

**说明：** 运行该示例代码将提交转码作业。

```
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
                                "<your-region-id>",                 // 您的可用区ID
                                "<your-access-key-id>",             // 您的AccessKey ID
                                "<your-access-key-secret>"        // 您的AccessKey Secret
        );
        return new DefaultAcsClient(profile);
    }

    public static void main(String[] args) {
        // Params
        // Oss region / 输出Bucket所在地域
        String OssLocation = "<your-oss-location>";
        // Oss bucket / 输出Bucket
        String OssBucket = "<your-oss-bucket>";
        // Oss Input Object / 输入文件名,路径
        String OssInputObject = "<your-oss-input-object>";
        // Oss Output Object / 输出文件名,路径
        String OssOutputObject = "<your-oss-output-object>";
        // Template Id / 输出模板ID
        String TemplateId = "<your-template-id>";
        // Pipeline Id / 管道ID
        String PipelineId = "<your-pipeline-id>";

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
            response = getIAcsClient().getAcsResponse(request);
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

```