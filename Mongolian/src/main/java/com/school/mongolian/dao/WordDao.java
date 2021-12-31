package com.school.mongolian.dao;

import com.school.mongolian.po.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WordDao {
    @Select("select * from mongo_word")
    public List<Word> getAllWord();

    @Select("select * from mongo_word where ciXing = #{ciXing}")
    public List<Word> getWordByCiXing(@Param("ciXing")String ciXing);



}
