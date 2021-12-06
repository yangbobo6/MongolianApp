package com.school.mongolian.dao;

import com.school.mongolian.po.Introduce;
import com.school.mongolian.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IntroduceDao {
    @Select("select * from mongo_introduce where id = #{id}")
    public Introduce getById(@Param("id")int id);

    @Select("select * from mongo_introduce")
    public List<Introduce> getAllIntroduce();

}
