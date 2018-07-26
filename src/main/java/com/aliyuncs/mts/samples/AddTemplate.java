package com.aliyuncs.mts.samples;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.*;

public class AddTemplate {

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
        // 创建API请求并设置参数
        AddTemplateRequest request = new AddTemplateRequest();
        AddTemplateResponse response;
        // Name  / 模板名称，最大长度128字节。
        request.setName("test-mts-template-2018");
        // Container / 输出的容器类型（文件格式）。视频支持mp4、flv、ts、m3u8，音频支持mp3、mp4等。
        JSONObject container = new JSONObject();
        container.put("Format", "mp4");
        request.setContainer(container.toJSONString());
        // Video / 视频流配置，JSON对象。例如编码格式、码率、宽、高、帧率等。
        // https://help.aliyun.com/document_detail/29226.html?spm=a2c4g.11186623.6.628.VcmQDE
        JSONObject video = new JSONObject();
        video.put("Codec", "H.264");
        video.put("Profile", "high");
        video.put("Bitrate", "500");
        video.put("Crf", "15");
        video.put("Width", "256");
        video.put("Height", "800");
        video.put("Fps", "25");
        video.put("Gop", "10");
        request.setVideo(video.toJSONString());
        // Audio / 音频参数，JSON对象
        JSONObject audio = new JSONObject();
        audio.put("Codec", "AAC");
        audio.put("Bitrate", "500");
        audio.put("Channels", "2");
        audio.put("Samplerate", "44100");
        request.setAudio(audio.toJSONString());
        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId :" + response.getRequestId());
            System.out.println(String.format("Id: %s, Name: %s", response.getTemplate().getId(), response.getTemplate().getName()));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
