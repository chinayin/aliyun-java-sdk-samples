# 查询媒体-使用媒体ID

本示例介绍如何使用阿里云Java SDK调用MTS的QueryMediaList接口查询媒体-使用媒体ID。

查询媒体列表。

在执行前，您需要获取以下信息：

-   MediaIds

    文件列表，逗号分隔，最多10个。


## 示例代码

**说明：** 运行该示例代码将查询媒体-使用媒体ID。

```
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
                                "<your-region-id>",                 // 您的可用区ID
                                "<your-access-key-id>",             // 您的AccessKey ID
                                "<your-access-key-secret>"        // 您的AccessKey Secret
        );
        return new DefaultAcsClient(profile);
    }

    public static void main(String[] args) {
        // MediaIds / 文件列表，逗号分隔，最多10个
        String MediaIds = "<your-ids>";

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
            response = getIAcsClient().getAcsResponse(request);
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

```