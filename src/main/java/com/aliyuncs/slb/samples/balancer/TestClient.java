package com.aliyuncs.slb.samples.balancer;

import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import java.io.FileInputStream;
import java.util.Properties;

public class TestClient {

    /**
     * 创建DefaultAcsClient实例并初始化
     *
     * @return IAcsClient
     */
    public static IAcsClient getIAcsClient() {
        String RegionId = "";   // 您的可用区ID
        String AccessKeyId = "";   // 您的AccessKey ID
        String AccessKeySecret = "";   // 您的AccessKey Secret
        try {
            FileInputStream input = new FileInputStream("/Users/tian/NetBeansProjects/aliyun-java-sdk-samples/src/resources/ak.properties");
            Properties properties = new Properties();
            properties.load(input);
            input.close();
            RegionId = properties.getProperty("RegionId");
            AccessKeyId = properties.getProperty("AccessKeyId");
            AccessKeySecret = properties.getProperty("AccessKeySecret");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        DefaultProfile profile = DefaultProfile.getProfile(
                RegionId,       //"<your-region-id>",               // 您的可用区ID
                AccessKeyId,    //"<your-access-key-id>",           // 您的AccessKey ID
                AccessKeySecret //"<your-access-key-secret>"        // 您的AccessKey Secret
        );
        return new DefaultAcsClient(profile);
    }

}
