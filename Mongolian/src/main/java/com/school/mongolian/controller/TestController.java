package com.school.mongolian.controller;

import com.school.mongolian.dao.TestDao;
import com.school.mongolian.dto.Grade;
import com.school.mongolian.po.Test;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<List<Test>> getByGrade(@RequestParam("grade")int grade,@RequestParam("type")int type){
        List<Test> testList = testDao.getByGrade(grade,type);
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
    @RequestMapping("/getAllList")
    public Result<List<Test>> getAllList(){
        List<Test> tests = testDao.getAllList();
        if(tests.size()==0){
            return Result.error(CodeMsg.TEST_ERROR);
        }
        return Result.success(tests);
    }

}
