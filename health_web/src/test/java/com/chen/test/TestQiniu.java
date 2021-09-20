package com.chen.test;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

public class TestQiniu {

    //上传本地文件
    @Test
    public void uploadFile(){
        //构造一个带指定zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //.生成上传凭证，然后准备上传
        String accessKey = "j8RqWHjTfQkkCHFRMC5czvMqdEWheEyaW6V3NN2u";
        String secretKey = "EdsSdttU2JeY177qTnO2A7HHwW6WqZLYbyrqrxQg";
        String bucket = "chenxiaoqing";
        String localFilePath = "E:/photo/tu/2050072.jpg";
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);

        } catch (QiniuException e) {
            Response r = e.response;
            System.err.println(r.toString());
            e.printStackTrace();
        }
    }


    @Test
    public void deleteFile(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        String accessKey = "j8RqWHjTfQkkCHFRMC5czvMqdEWheEyaW6V3NN2u";
        String secretKey = "EdsSdttU2JeY177qTnO2A7HHwW6WqZLYbyrqrxQg";
        String bucket = "chenxiaoqing";
        String key = "FqjD5RQdWtjGT1wlK-oALzcPLVoP";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
