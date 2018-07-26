package com.aliyuncs.mts.samples;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.*;

public class QueryMediaList {

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
        // MediaIds / 文件列表，逗号分隔，最多10个
//        String MediaIds = "<your-ids>";
        String MediaIds = "1";

        // 创建API请求并设置参数
        QueryMediaListRequest request = new QueryMediaListRequest();
        QueryMediaListResponse response;
        // MediaIds
        request.setMediaIds(MediaIds);
        // 结果是否包含播放信息，范围：true、false，默认值：false
        request.setIncludePlayList(false);
        // 结果是否包含截图信息，范围：true、false，默认值：false
        request.setIncludeSnapshotList(false);
        // 结果是否包含播放信息，范围：true、false，默认值：false
        request.setIncludeMediaInfo(false);
        // 发起请求并处理应答或异常
        try {
            response = TestClient.getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId :" + response.getRequestId());
            for (QueryMediaListResponse.Media media : response.getMediaList()) {
                System.out.println(String.format("MediaId: %s, Size: %s", media.getMediaId(), media.getSize()));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
