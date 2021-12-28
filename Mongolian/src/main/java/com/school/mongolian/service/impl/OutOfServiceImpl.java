package com.school.mongolian.service.impl;

import com.school.mongolian.dao.wechatDao.OutOfDao;
import com.school.mongolian.po.wechat.StudentInfo;
import com.school.mongolian.service.OutOfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OutOfServiceImpl implements OutOfService {
    @Autowired
    //@Qualifier("")
    OutOfDao outOfDao;

    @Override
    public List<StudentInfo> findAll() {
        List<StudentInfo> studentInfo = outOfDao.findAll();
        if(studentInfo==null){
            return null;
        }
        return studentInfo;
    }

    @Override
    public StudentInfo findById(int id) {
        StudentInfo std = outOfDao.findById(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(new Date().getTime()-24*60*60*1000);
        String format = sdf.format(date);
        std.setApplicationTime(format);
        if(std==null){
            return null;
        }
        return std;
    }

}
