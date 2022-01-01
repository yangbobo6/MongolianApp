package com.school.mongolian.controller;

import com.school.mongolian.dao.TestDao;
import com.school.mongolian.dto.Grade;
import com.school.mongolian.po.Test;
import com.school.mongolian.po.Text;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Api("testGrade")
@RestController
@RequestMapping("/testGrade")
public class TestController {
    public static final String prefix = "http://r3bne66hw.hb-bkt.clouddn.com/";
    @Autowired
    TestDao testDao;

    @ApiOperation("testGradeClassify")
    @RequestMapping("/getByGradeClassify")
    public Result<List<Grade>> getByGradeClassify(@RequestParam("grade")int grade){
        List<Grade> gradeList = testDao.getByGradeClassify(grade);
        if(gradeList.size()==0){
            log.info("为空");
            return Result.error(CodeMsg.TEST_ERROR);
        }
        log.info("不为空");
        return Result.success(gradeList);
    }

    @ApiOperation("testGrade")
    @RequestMapping("/getByGrade")
    public Result<List<Test>> getByGrade(@RequestParam("grade")int grade,@RequestParam("type")int type,
                                         @RequestParam("collect")int collect){
        List<Test> testList = testDao.getByGrade(grade,type);
        if(collect==1){
            for (Test t:testList
                 ) {
                if(t.getCollect()==0) {
                    testList.remove(t);
                }
//                }else {
//                    System.out.println("123");
//                    t.setTestPhoto(prefix+t.getTestPhoto());
//                }
            }
            for (Test t:testList
                 ) {
                t.setTestPhoto(prefix+t.getTestPhoto());
            }
            return Result.success(testList);
        }

        for (Test test:testList
             ) {
            test.setTestPhoto(prefix+test.getTestPhoto());
        }

        log.info(testList.toString());
        if(testList.size()==0){
            return Result.error(CodeMsg.GRADE_ERROR);
        }
        return Result.success(testList);
    }

    //收藏
    @RequestMapping("/getTestCollect")
    public Result<List<Test>> getTestCollects(){
        List<Test> testCollect = testDao.getTestCollect();
        if(testCollect.size()==0){
            return Result.error(CodeMsg.COLLECT_ERROR);
        }
        for (Test t:testCollect
             ) {
            t.setTestPhoto(prefix+t.getTestPhoto());
        }
        return Result.success(testCollect);
    }
}
