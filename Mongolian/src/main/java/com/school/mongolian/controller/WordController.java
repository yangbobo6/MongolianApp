package com.school.mongolian.controller;

import com.school.mongolian.dao.DailySenDao;
import com.school.mongolian.dao.WordDao;
import com.school.mongolian.po.Word;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/word")
public class WordController {
    public static final String prefixUrl = "http://r3whkaj8f.hn-bkt.clouddn.com/";
    @Autowired
    WordDao wordDao;
    @Autowired
    DailySenDao dailySenDao;

    //http://r3whkaj8f.hn-bkt.clouddn.com/
    @RequestMapping("/getAllWord")
    public Result<List<Word>> getAllWord(){
        List<Word> wordList = wordDao.getAllWord();
        if(wordList==null){
            return Result.error(CodeMsg.WORD_ERROR);
        }
        return Result.success(wordList);
    }
    // get.../？ciXing= {}
    @RequestMapping("/getWordByCiXing")
    public Result<List<Word>> getWordByCiXing(@RequestParam("ciXing")String ciXing){
        List<Word> wordByCiXing = wordDao.getWordByCiXing(ciXing);
        for (Word word:wordByCiXing
             ) {
            word.setPhotoWordUrl(prefixUrl+word.getPhotoWordUrl());
        }
        if(wordByCiXing!=null){
            return Result.success(wordByCiXing);
        }
        return Result.error(CodeMsg.WORD_ERROR);
    }


}
