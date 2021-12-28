package com.school.mongolian.dao.wechatDao;

import com.school.mongolian.po.wechat.StudentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutOfDao {
    public List<StudentInfo> findAll();
    public StudentInfo findById(int id);
}
