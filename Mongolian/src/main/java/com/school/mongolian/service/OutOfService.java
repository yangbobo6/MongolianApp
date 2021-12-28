package com.school.mongolian.service;

import com.school.mongolian.po.wechat.StudentInfo;

import java.util.List;

public interface OutOfService {
    public List<StudentInfo> findAll();
    public StudentInfo findById(int id);

}
