package com.nisiwa.project3.service;

import com.aliyun.oss.OSSClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author: nisiwa
 * @date: 2019-04-11 17:08
 */
@Service
public class UploadPhoto2OOS {
    public String uploadPhoto(MultipartFile file) throws IOException {
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAIvOfFBk2mW0rT";
        String accessKeySecret = "Es1WmpiGJQOhurfPsKnHPbQwR3MfST";

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        String bukectname="wangdao12";

        InputStream inputStream = file.getInputStream();

        String key = UUID.randomUUID().toString().replaceAll("-", "")+file.getOriginalFilename();
        ossClient.putObject(bukectname, key, inputStream);

        ossClient.shutdown();

        return "https://"+bukectname +".oss-cn-hangzhou.aliyuncs.com/" + key;
    }
}
