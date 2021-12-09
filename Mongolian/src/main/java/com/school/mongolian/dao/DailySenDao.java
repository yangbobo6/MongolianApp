package com.school.mongolian.dao;

import com.school.mongolian.po.DailySen;
import com.school.mongolian.po.Introduce;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DailySenDao {
    @Select("select * from mongo_dailySen where id = #{id}")
    public DailySen getById(@Param("id")int id);

    @Select("select * from mongo_dailySen")
    public List<DailySen> getAllDailySen();
}
