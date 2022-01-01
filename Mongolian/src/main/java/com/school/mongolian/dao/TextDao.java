package com.school.mongolian.dao;

import com.school.mongolian.po.Text;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TextDao {
    @Select("select * from mongo_text ")
    public List<Text> getAllText();

}
