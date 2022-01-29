package com.school.mongolian.controller;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.*;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.school.mongolian.dao.IntroduceDao;
import com.school.mongolian.dto.QiNiuDto;
import com.school.mongolian.po.Introduce;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


//r3bne66hw.hb-bkt.clouddn.com
//服务器直传
@RestController
@RequestMapping("/folkway")
@Api(tags = "民俗")
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

    //上传文件
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
    //获取文件信息
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

//    @RequestMapping("/getPhoto1")
//    public Result<QiNiuDto> getPhotos(){
//        QiNiuDto qiNiuDto = new QiNiuDto();
//        qiNiuDto.setName("PhotoUrl");
//        List<String> url = new ArrayList<>();
//        url.add("http://r3bne66hw.hb-bkt.clouddn.com/20211129151908.png");
//        url.add("http://r3bne66hw.hb-bkt.clouddn.com/20211129151909.png");
//        url.add("http://r3bne66hw.hb-bkt.clouddn.com/20211129151930.png");
//        qiNiuDto.setQiNiuUrl(url);
//        return Result.success(qiNiuDto);
//    }

    @Autowired
    IntroduceDao introduceDao;
    public static final String prefixUrl = "http://yangbotj.lxmyt.com/";

    @RequestMapping("/getPhoto/{id}")
    public Result<Introduce> getPhoto(@PathVariable int id){
        Introduce intro = introduceDao.getById(id);
        if(intro!=null){
            return Result.success(intro);
        }
        return Result.error(CodeMsg.PHOTO_ERROR);
    }

    //http://r3bne66hw.hb-bkt.clouddn.com/
    @RequestMapping(value = "/getAllIntroduce",method = RequestMethod.GET)
    @ApiOperation("民俗")
    public Result<List<Introduce>> getAllIntroduce(@RequestParam("collect")int collect){
        List<Introduce> list = introduceDao.getAllIntroduce();

        if(collect==1){
            List<Introduce> listCollect = new ArrayList<>();
            for (Introduce introduce:list
            ) {
                if(introduce.getCollect()==1){
                    introduce.setMainUrl(prefixUrl+introduce.getMainUrl());
                    introduce.setFirstUrl(prefixUrl+introduce.getFirstUrl());
                    introduce.setSecondUrl(prefixUrl+introduce.getSecondUrl());
                    listCollect.add(introduce);
                }
            }
            return Result.success(listCollect);
        }

        for (Introduce intro:list
             ) {
            intro.setFirstUrl(prefixUrl+intro.getFirstUrl());
        }
        for (Introduce intro:list
        ) {
            intro.setMainUrl(prefixUrl+intro.getMainUrl());
        }
        for (Introduce intro:list
        ) {
            intro.setSecondUrl(prefixUrl+intro.getSecondUrl());
        }
        if(list.size()==0){
            return Result.error(CodeMsg.PHOTO_ERROR);
        }
        return Result.success(list);
    }

    //收藏
    @RequestMapping("/getIntroCollect")
    public Result<List<Introduce>> getIntroCollect(){
        List<Introduce> allIntroduce = introduceDao.getAllIntroduce();
        List<Introduce> list = new ArrayList<>();
        for (Introduce introduce:allIntroduce
             ) {
            if(introduce.getCollect()==1){
                introduce.setMainUrl(prefixUrl+introduce.getMainUrl());
                introduce.setFirstUrl(prefixUrl+introduce.getFirstUrl());
                introduce.setSecondUrl(prefixUrl+introduce.getSecondUrl());
                list.add(introduce);
            }
        }
        return Result.success(list);

    }


}

