package com.school.mongolian.controller;

import com.school.mongolian.dao.DailySenDao;
import com.school.mongolian.dao.WordDao;
import com.school.mongolian.po.Text;
import com.school.mongolian.po.Word;
import com.school.mongolian.result.CodeMsg;
import com.school.mongolian.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api
@RestController
@RequestMapping("/word")
public class WordController {
    public static final String prefixUrl = "http://yangboword.lxmyt.com/";
    @Autowired
    WordDao wordDao;
    @Autowired
    DailySenDao dailySenDao;

    //这个暂时没用
    //http://r3whkaj8f.hn-bkt.clouddn.com/
//    @RequestMapping("/getAllWord")
//    public Result<List<Word>> getAllWord(@RequestParam("collect")int collect){
//        List<Word> wordList = wordDao.getAllWord();
//        if(collect==1){
//            List<Word> collectText = new ArrayList<>();
//            for (Word word:wordList
//            ) {
//                word.setPhotoWordUrl(prefixUrl+word.getPhotoWordUrl());
//            }
//            if(wordList.size()==0){
//                return Result.error(CodeMsg.COLLECT_ERROR);
//            }
//            for (Word w: wordList
//            ) {
//                if(w.getCollect()==1){
//                    collectText.add(w);
//                }
//            }
//            return Result.success(collectText);
//        }
//        if(wordList==null){
//            return Result.error(CodeMsg.WORD_ERROR);
//        }
//        return Result.success(wordList);
//    }

    // get.../？ciXing= {}
    @ApiOperation("通过词性获取单词")
    @RequestMapping(value = "/getWordByCiXing",method = RequestMethod.GET)
    public Result<List<Word>> getWordByCiXing(@RequestParam("ciXing")String ciXing,@RequestParam("collect")int collect){
        List<Word> wordByCiXing = wordDao.getWordByCiXing(ciXing);
        if(collect==1){
            List<Word> collectText = new ArrayList<>();
            for (Word word:wordByCiXing
            ) {
                word.setPhotoWordUrl(prefixUrl+word.getPhotoWordUrl());
            }
            if(wordByCiXing.size()==0){
                return Result.error(CodeMsg.COLLECT_ERROR);
            }
            for (Word w: wordByCiXing
            ) {
                if(w.getCollect()==1){
                    collectText.add(w);
                }
            }
            return Result.success(collectText);
        }


        for (Word word:wordByCiXing
             ) {
            word.setPhotoWordUrl(prefixUrl+word.getPhotoWordUrl());
        }
        if(wordByCiXing!=null){
            return Result.success(wordByCiXing);
        }
        return Result.error(CodeMsg.WORD_ERROR);
    }

    //收藏
    @RequestMapping("/getWordCollect")
    public Result<List<Word>> getCollects(){
        List<Word> allWord = wordDao.getAllWord();

        List<Word> collectText = new ArrayList<>();
        for (Word word:allWord
        ) {
            word.setPhotoWordUrl(prefixUrl+word.getPhotoWordUrl());
        }
        if(allWord.size()==0){
            return Result.error(CodeMsg.COLLECT_ERROR);
        }
        for (Word w: allWord
        ) {
            if(w.getCollect()==1){
                collectText.add(w);
            }
        }
        return Result.success(collectText);
    }
}
