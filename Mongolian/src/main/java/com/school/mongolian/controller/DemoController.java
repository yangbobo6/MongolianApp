package com.school.mongolian.controller;

import com.school.mongolian.dao.IntroduceDao;
import com.school.mongolian.dao.VideoDao;
import com.school.mongolian.log.LogHelper;
import com.school.mongolian.po.Introduce;
import com.school.mongolian.po.Video;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/test")
@Slf4j
public class DemoController {

    @Autowired
    IntroduceDao introduceDao;

    @RequestMapping("/resultSuccess")
    public Result<String> testSuccess(){
        return Result.success("hello success");
    }
    @RequestMapping("/resultError")
    public Result<String> update(){
        return Result.error(CodeMsg.UPDATE_APP);
    }

    @Autowired
    private LogHelper logHelper;

    @Autowired
    VideoDao videoDao;
    @RequestMapping("/helpLog")
    public Result<String> testLog(){
        log.info("开始打印日志");
        logHelper.helpLog();
        log.info("结束打印日志INFO");
        return Result.success("log");
    }

    @RequestMapping(value = "/getAllVideo", method = RequestMethod.GET)
    public Result<List<Video>> getAllVideo(){
        List<Video> allVideo = videoDao.getAllVideo();
        if(allVideo!=null){
            return Result.success(allVideo);
        }
        return Result.error(CodeMsg.VIDEO_ERROR);
    }

    @RequestMapping(value = "/getAllIntroduce",method = RequestMethod.GET)
    @ApiOperation("民俗")
    public Result<List<Introduce>> getAllIntroduce(){
        List<Introduce> list = introduceDao.getAllIntroduce();
        if(list.size()==0){
            return Result.error(CodeMsg.PHOTO_ERROR);
        }
        return Result.success(list);
    }

    @RequestMapping(value = "/getAllIntroduce1",method = RequestMethod.GET)
    @ApiOperation("民俗1")
    public Result<List<Introduce>> getAllIntroduce2(){
        List<Introduce> list = introduceDao.getAllIntroduce();
        if(list.size()==0){
            return Result.error(CodeMsg.PHOTO_ERROR);
        }
        return Result.success(list);
    }


}
