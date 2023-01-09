package com.mai.epidemic.commons.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tencent",ignoreInvalidFields = true,ignoreUnknownFields = true)
public class CosUtils {

    public static String appId;

    public static String secretId;

    public static String secretKey;

    public static String region;

    public static String photoBucket;

    //注入值
    @Value("${tencent.appId}")
    public void setAppId(String appId) {
        this.appId = appId;
    }
    @Value("${tencent.secretId}")
    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }
    @Value("${tencent.secretKey}")
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    @Value("${tencent.region}")
    public void setRegion(String region) {
        this.region = region;
    }
    @Value("${tencent.photoBucket}")
    public void setPhotoBucket(String photoBucket) {
        this.photoBucket = photoBucket;
    }

}
