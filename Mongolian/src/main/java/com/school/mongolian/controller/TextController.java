package com.school.mongolian.controller;

import com.school.mongolian.dao.TextDao;
import com.school.mongolian.po.Text;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/text")
public class TextController {

    public static final String prefix = "http://r3bne66hw.hb-bkt.clouddn.com/";
    @Autowired
    TextDao textDao;
    @RequestMapping("/getAllText")
    public Result<List<Text>> getAllText(){
        List<Text> textList = textDao.getAllText();
        for (Text text:textList
             ) {
            text.setTextUrl(prefix+text.getTextUrl());
        }
        if(textList==null){
            return Result.error(CodeMsg.TEXT_ERROR);
        }
        return Result.success(textList);
    }

}
