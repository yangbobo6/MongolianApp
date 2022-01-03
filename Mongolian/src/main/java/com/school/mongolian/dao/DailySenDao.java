package com.school.mongolian.dao;

import com.school.mongolian.po.DailySen;
import com.school.mongolian.po.Introduce;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DailySenDao {
    @Select("select * from mongo_dailySen where id = #{id}")
    public DailySen getById(@Param("id")int id);

    @Select("select * from mongo_dailySen")
    public List<DailySen> getAllDailySen();

    @Select("select * from mongo_dailySen where collect = 1")
    public List<DailySen> getCollectDailySen();

    public boolean setCollect(int id,int collect);
    public boolean setWordCollect(int id,int collect);
    public boolean setTestCollect(int id,int collect);
    public boolean setTextCollect(int id,int collect);
    public boolean setIntroduceCollect(int id,int collect);
    public boolean setFolktaleCollect(int id,int collect);



}
