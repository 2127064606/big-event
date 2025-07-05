package org.example.bigevent.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
//@Data
public class AliOssConfig {

    private static String endpoint;

    private static String region;

    private static String bucketName;


    public static String getEndpoint() {
        return AliOssConfig.endpoint;
    }
    @Value("${oss-basedata-config.endpoint}")
    public  void setEndpoint(String endpoint) {
        AliOssConfig.endpoint = endpoint;
    }

    public static String getRegion() {
        return AliOssConfig.region;
    }

    @Value("${oss-basedata-config.region}")
    public  void setRegion(String region) {
        AliOssConfig.region = region;
    }

    public static String getBucketName() {
        return AliOssConfig.bucketName;
    }

    @Value("${oss-basedata-config.bucket-name}")
    public void setBucketName(String bucketName) {
        AliOssConfig.bucketName = bucketName;
    }
}
