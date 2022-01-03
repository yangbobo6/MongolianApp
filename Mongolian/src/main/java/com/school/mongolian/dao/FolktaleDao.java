package com.school.mongolian.dao;

import com.school.mongolian.po.FolkTale;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FolktaleDao {
    public List<FolkTale> getAllFolktale();

}
