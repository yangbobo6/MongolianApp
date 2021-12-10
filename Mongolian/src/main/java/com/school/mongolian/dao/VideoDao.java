package com.school.mongolian.dao;

import com.school.mongolian.po.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VideoDao {
    @Select("select * from mongo_video")
    public List<Video> getAllVideo();

}
