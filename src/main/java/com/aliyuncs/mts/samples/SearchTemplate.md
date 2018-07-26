# 搜索自定义转码模板

本示例介绍如何使用阿里云Java SDK调用MTS的SearchTemplate接口查询搜索自定义转码模板。

通过管道状态搜索管道。

## 示例代码

**说明：**

```
package com.aliyuncs.mts.samples;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.*;

public class SearchTemplate {

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
        // PageNumber / 当前页号，从第1页开始，默认值是1
        long PageNumber = 1;
        // PageSize / 分页查询时设置的每页行数页面大小，默认值是10,上限100。
        long PageSize = 100;
        // State / 转码模板状态：All表示所有，Normal表示正常，Deleted表示已删除，默认是All
        String State = "All";
        // 创建API请求并设置参数
        SearchTemplateRequest request = new SearchTemplateRequest();
        SearchTemplateResponse response;
        //
        request.setPageNumber(PageNumber);
        request.setPageSize(PageSize);
        request.setState(State);
        // 发起请求并处理应答或异常
        try {
            response = getIAcsClient().getAcsResponse(request);
            System.out.println("RequestId :" + response.getRequestId());
            System.out.println(String.format("TotalCount: %s", response.getTotalCount()));
            for (SearchTemplateResponse.Template template : response.getTemplateList()) {
                System.out.println(String.format("Id: %s, Name: %s", template.getId(), template.getName()));
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

```