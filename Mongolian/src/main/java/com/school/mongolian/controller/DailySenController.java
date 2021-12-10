package com.school.mongolian.controller;

import com.school.mongolian.dao.DailySenDao;
import com.school.mongolian.dao.IntroduceDao;
import com.school.mongolian.po.DailySen;
import com.school.mongolian.po.Introduce;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import com.school.mongolian.service.DailySenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@Api(tags = "民俗谚语")
@RequestMapping("/dailySen/")
public class DailySenController {

    public static final String prefixUrl = "http://r3sr4g6ht.hb-bkt.clouddn.com/";

    @Autowired
    DailySenDao dailySenDao;
    @Autowired
    DailySenService dailySenService;

    @Autowired
    IntroduceDao introduceDao;

    @RequestMapping("/getByAllDailySen")
    public Result<List<DailySen>> getAllDailySen(){
        List<DailySen> allDailySen = dailySenDao.getAllDailySen();
        for ( DailySen dailySen:allDailySen
             ) {
            dailySen.setPhotoUrl(prefixUrl+dailySen.getPhotoUrl());
        }
        if(allDailySen==null){
            return Result.error(CodeMsg.DAILY_SEN_List_ERROR);
        }
        return Result.success(allDailySen);
    }

    @RequestMapping("/getDailySen/{id}")
    public Result<DailySen> getByIdDailySen(@PathVariable int id){
        DailySen dailySen = dailySenDao.getById(id);
        dailySen.setPhotoUrl(prefixUrl+dailySen.getPhotoUrl());
        if(dailySen==null){
            return Result.error(CodeMsg.DAILY_SEN_ERROR);
        }
        return Result.success(dailySen);
    }

    //获取 每日一句 或者随机获取谚语
    @ApiOperation(value = "民俗")
    @RequestMapping(value = "/getDailySenType",method = RequestMethod.POST)
    public Result<List<DailySen>> getByIdDailySen1(@RequestParam int type){
        if(type==1){
            DailySen dailySen = dailySenService.getDailySen();
            return getListResult(dailySen);
        }else if(type==2) {
            List<DailySen> allDailySen = dailySenDao.getAllDailySen();
            for ( DailySen dailySen:allDailySen
            ) {
                dailySen.setPhotoUrl(prefixUrl+dailySen.getPhotoUrl());
            }
            return Result.success(allDailySen);
        }else {
            return Result.error(CodeMsg.DAILY_TYPE_ERROR);
        }
    }

    private Result<List<DailySen>> getListResult(DailySen dailySen) {
        dailySen.setPhotoUrl(prefixUrl+dailySen.getPhotoUrl());
        if(dailySen==null){
            return Result.error(CodeMsg.DAILY_SEN_ERROR);
        }
        List<DailySen> list = new ArrayList<>();
        list.add(dailySen);
        return Result.success(list);
    }

}
