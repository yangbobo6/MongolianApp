package com.school.mongolian.controller.wechatController;

import com.school.mongolian.po.wechat.StudentInfo;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import com.school.mongolian.service.OutOfService;
import com.school.mongolian.service.impl.OutOfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Wechat")
public class WechatOutController {
    @Autowired
    OutOfService outOfService;

    @RequestMapping("outOfSchool1")
    public Result<List<StudentInfo>> findAll(){
        List<StudentInfo> infoList = outOfService.findAll();
        if(infoList.size()==0){
            return Result.error(CodeMsg.OutOfSchool);
        }
        return Result.success(infoList);
    }
    @RequestMapping("/outOfSchool")
    public Result<StudentInfo> findById(@RequestParam int id){
        StudentInfo std = outOfService.findById(id);
        if(std==null){
            return Result.error(CodeMsg.OutOfSchool);
        }
        return Result.success(std);
    }

}
