package com.school.mongolian.controller;

import com.school.mongolian.dao.DailySenDao;
import com.school.mongolian.dao.IntroduceDao;
import com.school.mongolian.po.DailySen;
import com.school.mongolian.po.Introduce;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import com.school.mongolian.service.DailySenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dailySen/")
public class DailySenController {

    public static final String prefixUrl = "http://r3sr4g6ht.hb-bkt.clouddn.com";

    @Autowired
    DailySenDao dailySenDao;
    @Autowired
    DailySenService dailySenService;

    @Autowired
    IntroduceDao introduceDao;

    @RequestMapping("/getByAllDailySen")
    public Result<List<DailySen>> getAllDailySen(){
        List<DailySen> allDailySen = dailySenDao.getAllDailySen();
        if(allDailySen==null){
            return Result.error(CodeMsg.DAILY_SEN_List_ERROR);
        }
        return Result.success(allDailySen);
    }

    //获取 每日一句  谚语
    @RequestMapping("/getDailySen")
    public Result<DailySen> getByIdDailySen(){
        DailySen dailySen = dailySenService.getDailySen();
        dailySen.setPhotoUrl(prefixUrl+dailySen.getPhotoUrl());
        if(dailySen==null){
            return Result.error(CodeMsg.DAILY_SEN_ERROR);
        }
        return Result.success(dailySen);
    }


    @RequestMapping("/getPhoto1/{id}")
    public Result<Introduce> getPhoto(@PathVariable int id){
        Introduce intro = introduceDao.getById(id);
        if(intro!=null){
            return Result.success(intro);
        }
        return Result.error(CodeMsg.PHOTO_ERROR);
    }

    @RequestMapping("/getAllIntroduce")
    public Result<List<Introduce>> getAllIntroduce(){
        List<Introduce> list = introduceDao.getAllIntroduce();
        if(list.size()==0){
            return Result.error(CodeMsg.PHOTO_ERROR);
        }
        return Result.success(list);
    }


}
