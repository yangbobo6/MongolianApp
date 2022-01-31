package com.school.mongolian.controller;

import com.school.mongolian.dao.VideoDao;
import com.school.mongolian.po.Video;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/video")
@Api(tags = "video")
public class VideoController {

    //不知道为什么没有  前缀prefix  ？？？？  yangbovideo.lxmyt.com
    @Autowired
    VideoDao videoDao;

    private static String prefixUrl = "http://yangbovideo.lxmyt.com/";


    //POST
    @ApiOperation(value = "获取所有视频")
    @RequestMapping(value = "/getAllVideo", method = RequestMethod.POST)
    public Result<List<Video>> getAllVideo(){
        List<Video> allVideo = videoDao.getAllVideo();
        for (Video video:allVideo
             ) {
            video.setVideoUrl(prefixUrl+video.getVideoUrl());
            video.setVideoPhotoUrl(prefixUrl+video.getVideoPhotoUrl());
        }
        if(allVideo!=null){
            return Result.success(allVideo);
        }
        return Result.error(CodeMsg.VIDEO_ERROR);
    }
}
