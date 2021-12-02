package com.school.mongolian.controller;

import com.school.mongolian.log.LogHelper;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/test")
@Slf4j
public class DemoController {

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

    @RequestMapping("/helpLog")
    public Result<String> testLog(){
        log.info("开始打印日志");
        logHelper.helpLog();
        log.info("结束打印日志INFO");
        return Result.success("log");
    }



}
