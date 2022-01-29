package com.school.mongolian.controller;

import com.school.mongolian.dao.TextDao;
import com.school.mongolian.po.Text;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/text")
public class TextController {

    public static final String prefix = "http://yangbotj.lxmyt.com/";
    @Autowired
    TextDao textDao;
    @RequestMapping("/getAllText")
    public Result<List<Text>> getAllText(@RequestParam("collect")int collect){
        List<Text> textList = textDao.getAllText();
        if (collect==1){
            List<Text> collectText = new ArrayList<>();
            for (Text text:textList
            ) {
                text.setTextUrl(prefix+text.getTextUrl());
            }
            if(textList.size()==0){
                return Result.error(CodeMsg.COLLECT_ERROR);
            }
            for (Text t: textList
            ) {
                if(t.getCollect()==1){
                    collectText.add(t);
                }
            }
            return Result.success(collectText);
        }


        for (Text text:textList
             ) {
            text.setTextUrl(prefix+text.getTextUrl());
        }
        if(textList==null){
            return Result.error(CodeMsg.TEXT_ERROR);
        }
        return Result.success(textList);
    }

    //收藏
    @RequestMapping("/getCollect")
    public Result<List<Text>> getCollects(){

        List<Text> allText = textDao.getAllText();
        List<Text> collectText = new ArrayList<>();
        for (Text text:allText
        ) {
            text.setTextUrl(prefix+text.getTextUrl());
        }
        if(allText.size()==0){
            return Result.error(CodeMsg.COLLECT_ERROR);
        }
        for (Text t: allText
             ) {
            if(t.getCollect()==1){
                collectText.add(t);
            }
        }
        return Result.success(collectText);
    }

}
