package com.school.mongolian.dao;

import com.school.mongolian.po.Release;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @Author: yangbo
 * @Date: 2022-02-13-18:02
 * @Description:
 */
@Mapper
public interface ReleaseDao {
    @Insert("insert into mongo_release (userId,name,text,date) values(#{userId},#{name},#{text},#{date})")
    public Boolean releaseInfo(@Param("userId")int userId,@Param("name")String name,@Param("text")String text,@Param("date") Date date);

    @Select("select * from mongo_release")
    public List<Release> selectRelease();
}
