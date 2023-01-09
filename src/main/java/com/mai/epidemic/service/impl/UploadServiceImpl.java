package com.mai.epidemic.service.impl;

import com.mai.epidemic.commons.Result;
import com.mai.epidemic.commons.utils.CosUtils;
import com.mai.epidemic.service.UploadService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class UploadServiceImpl implements UploadService {


    public COSClient getCosClient() {
        COSCredentials cred = new BasicCOSCredentials(CosUtils.getSecretId(), CosUtils.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(CosUtils.getRegion()));
        return new COSClient(cred, clientConfig);
    }

    @Override
    public Result uploadImages(MultipartFile img) throws IOException {
        //上传至存储桶的名字
        String KEY = "/epidemicMS/images/" + img.getOriginalFilename();
        String bucket = CosUtils.getPhotoBucket();

        //获得文件名
        String fileName = img.getOriginalFilename();
        //将图片的具体信息传入ObjectMetadate类
        ObjectMetadata meta = new ObjectMetadata();
        //必须设置该属性
        meta.setContentLength(img.getSize());
        //设置字符编码格式
        meta.setContentEncoding("UTF-8");
        //获得文件后缀名并根据传入的图片格式设置ContentType
        if (".png".equals(fileName.lastIndexOf("."))) {
            meta.setContentType("image/png");
        } else if (".jpg".equals(fileName.lastIndexOf("."))) {
            meta.setContentType("image/jpeg");
        }
        //SDK构造方法,具体参造[SDKAPI](https://help.aliyun.com/document_detail/32008.htm?spm=a2c4g.11186623.2.3.65ac605fhxBPgG)
        PutObjectRequest request = new PutObjectRequest(bucket, KEY, img.getInputStream(), meta);
        request.setStorageClass(StorageClass.Standard);
        COSClient client = getCosClient();
        try {
            client.putObject(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 关闭客户端
        client.shutdown();
        //拼接获得存储桶中可访问的地址
        return Result.success("https://" + bucket + ".cos." + CosUtils.getRegion() + ".myqcloud.com" + KEY);
    }
}
