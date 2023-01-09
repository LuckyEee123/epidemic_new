package com.mai.epidemic.commons.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tencent",ignoreInvalidFields = true,ignoreUnknownFields = true)
public class CosUtils {

    private static String appId;

    private static String secretId;

    private static String secretKey;

    private static String region;

    private static String photoBucket;

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

    public static String getAppId() {
        return appId;
    }

    public static String getSecretId() {
        return secretId;
    }

    public static String getSecretKey() {
        return secretKey;
    }

    public static String getRegion() {
        return region;
    }

    public static String getPhotoBucket() {
        return photoBucket;
    }
}
