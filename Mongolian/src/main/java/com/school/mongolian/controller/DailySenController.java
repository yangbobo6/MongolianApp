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
@Api(tags = "每日一句谚语")
@RequestMapping("/dailySen/")
public class DailySenController {

    public static final String prefixUrl = "http://yangboproverb.lxmyt.com/";

    @Autowired
    DailySenDao dailySenDao;
    @Autowired
    DailySenService dailySenService;

    @Autowired
    IntroduceDao introduceDao;

    //没有用到
    @ApiOperation("获取谚语")
    @RequestMapping("/getByAllDailySen")
    public Result<List<DailySen>> getAllDailySen(@RequestParam("collect")int collect){
        if(collect==1){
            List<DailySen> collectDailySen = dailySenDao.getCollectDailySen();
            for ( DailySen dailySen:collectDailySen
            ) {
                dailySen.setPhotoUrl(prefixUrl+dailySen.getPhotoUrl());
            }
            return Result.success(collectDailySen);
        }

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

    //没有用到
    @RequestMapping("/getDailySen/{id}")
    public Result<DailySen> getByIdDailySen(@PathVariable int id){
        DailySen dailySen = dailySenDao.getById(id);
        dailySen.setPhotoUrl(prefixUrl+dailySen.getPhotoUrl());
        if(dailySen==null){
            return Result.error(CodeMsg.DAILY_SEN_ERROR);
        }
        return Result.success(dailySen);
    }

    /**
     *
     * @param type  1代表每日一句   2代表谚语
     * @param collect  1代表查找收藏  0查找正常程序
     * @return
     */
    @ApiOperation(value = "每日一句")
    @RequestMapping(value = "/getDailySenType",method = RequestMethod.GET)
    public Result<List<DailySen>> getByIdDailySen1(@RequestParam("type") int type,@RequestParam("collect")int collect){
        //每日一句   收藏  collect=1，type随意取
        if(collect==1){
            List<DailySen> collectDailySen = dailySenDao.getCollectDailySen();
            for ( DailySen dailySen:collectDailySen
            ) {
                dailySen.setPhotoUrl(prefixUrl+dailySen.getPhotoUrl());
            }
            return Result.success(collectDailySen);
        }
        else if(collect==0){
            if(type==1){
                //获取每日一句
                DailySen dailySen = dailySenService.getDailySen();
                return getListResult(dailySen);
            }else if(type==2) {
                //获取谚语 （所有的内容）
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

       return Result.error(CodeMsg.COLLECT_ERROR_VALUE);
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

    //添加收藏功能  0代表不收藏
    @RequestMapping("/getCollectDailySen")
    public Result<List<DailySen>> getCollectDailySen(){
        List<DailySen> collectDailySen = dailySenDao.getCollectDailySen();
        for ( DailySen dailySen:collectDailySen
        ) {
            dailySen.setPhotoUrl(prefixUrl+dailySen.getPhotoUrl());
        }
        return Result.success(collectDailySen);
    }

    //收藏功能的改变
    @RequestMapping("/setCollect")
    public Result<Boolean> setCollect(@RequestParam("id")int id,@RequestParam("collect")int collect){
        //DailySen daoById = dailySenDao.getById(id);
        //int collect = daoById.getCollect();
        boolean b = dailySenDao.setCollect(id,collect);
        return getBooleanResult(collect, b);
    }

    private Result<Boolean> getBooleanResult(@RequestParam("collect") int collect, boolean b) {
        if(b&&collect==1){
            return new Result<>("收藏成功",true);
        }else if(b&&collect==0){
            return new Result<>("取消收藏成功",false);
        }else {
            return Result.error(CodeMsg.COLLECT_ERROR);
        }
    }

    @RequestMapping("/setWordCollect")
    public Result<Boolean> setWordCollect(@RequestParam("id")int id,@RequestParam("collect")int collect){
        boolean b = dailySenDao.setWordCollect(id,collect);
        return getBooleanResult(collect, b);
    }

    @RequestMapping("/setTextCollect")
    public Result<Boolean> setTextCollect(@RequestParam("id")int id,@RequestParam("collect")int collect){
        boolean b = dailySenDao.setTextCollect(id,collect);
        return getBooleanResult(collect, b);
    }

    @RequestMapping("/setTestCollect")
    public Result<Boolean> setTestCollect(@RequestParam("id")int id,@RequestParam("collect")int collect){
        boolean b = dailySenDao.setTestCollect(id, collect);
        return getBooleanResult(collect, b);
    }

    @RequestMapping("/setIntroduceCollect")
    public Result<Boolean> setIntroduceCollect(@RequestParam("id")int id,@RequestParam("collect")int collect){
        boolean b = dailySenDao.setIntroduceCollect(id,collect);
        return getBooleanResult(collect, b);
    }

    @RequestMapping("/setFolktaleCollect")
    public Result<Boolean> setFolktaleCollect(@RequestParam("id")int id,@RequestParam("collect")int collect){
        boolean b = dailySenDao.setFolktaleCollect(id,collect);
        return getBooleanResult(collect, b);
    }
}
