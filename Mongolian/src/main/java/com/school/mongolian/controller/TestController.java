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
    public static final String prefix = "http://yangbotj.lxmyt.com/";

    /*
      type 用3位数表示，初级用10x ，中级用 20x， 高级用30x
      初级中，测试 100，一年级 101... 六年级106

       typeName ，等级的名字，例如 测试，一年级.....六年级
     */
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
        List<Test> tests = new ArrayList<>();
        if(collect==1){
            for (Test t:testList
                 ) {
                if(t.getCollect()==1) {
                    tests.add(t);
                }

            }
            for (Test t:tests
                 ) {
                t.setTestPhoto(prefix+t.getTestPhoto());
            }
            return Result.success(tests);
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
