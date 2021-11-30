package com.school.mongolian.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.*;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.school.mongolian.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

//服务器直传
@RestController
@RequestMapping("/qiniu")
public class QiniuController {
    //构造一个带指定Region对象的配置类
    Configuration cfg = new Configuration(Region.region1());
    //生成上传凭证，然后准备上传
    UploadManager uploadManager = new UploadManager(cfg);
    public static String accessKey = "YD4gUDzRqQt2_2h32sHgGJmNQ0wMoOJRKVnkVGo-";
    public static String secretKey = "jhQq_sX44oRF_3nuGGNvsdW0fvjLgE3aIvpzDrD_";
    public static String bucket = "yangbotj";

    String localFilePath = "D:\\javaProject\\Mongolian\\mengyuApp\\photo\\微信图片_20211129151908.png";
    //默认不指定key的情况下，以文件内容的hash值作为文件名
    String key = "chuan.mp4";

    Auth auth = Auth.create(accessKey,secretKey);
    String upToken = auth.uploadToken(bucket);

    @RequestMapping("/upload")
    public Result<String> uploadFile(){

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return Result.success("success");

    }
    @RequestMapping("/getSources")
    public Result<String> getPhoto(){
        BucketManager bucketManager = new BucketManager(auth,cfg);
        try {
            FileInfo fileInfo = bucketManager.stat(bucket, key);
            System.out.println(fileInfo.hash);
            System.out.println(fileInfo.fsize);
            System.out.println(fileInfo.mimeType);
            System.out.println(fileInfo.putTime);
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
        return Result.success("get success");
    }

    String fileName = "20211129151908.png";

    @RequestMapping("/downResource")
    public Result<String> downSources() throws UnsupportedEncodingException {


        return Result.success("");
    }



}
