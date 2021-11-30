package com.school.mongolian.controller;

import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoController {
    @RequestMapping("/resultSuccess")
    public Result<String> testSuccess(){
        return Result.success("hello success");
    }
    @RequestMapping("/resultError")
    public Result<String> update(){
        return Result.error(CodeMsg.UPDATE_APP);
    }


}
