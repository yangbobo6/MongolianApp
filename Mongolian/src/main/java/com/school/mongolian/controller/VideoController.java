package com.school.mongolian.controller;

import com.school.mongolian.dao.VideoDao;
import com.school.mongolian.po.Video;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {

    //不知道为什么没有  前缀prefix  ？？？？  yangbovideo.lxmyt.com
    @Autowired
    VideoDao videoDao;

    @RequestMapping(value = "/getAllVideo", method = RequestMethod.POST)
    public Result<List<Video>> getAllVideo(){
        List<Video> allVideo = videoDao.getAllVideo();
        if(allVideo!=null){
            return Result.success(allVideo);
        }
        return Result.error(CodeMsg.VIDEO_ERROR);
    }
}
