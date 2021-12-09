package com.school.mongolian.service.impl;

import com.school.mongolian.dao.DailySenDao;
import com.school.mongolian.po.DailySen;
import com.school.mongolian.service.DailySenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DailySenServiceImpl implements DailySenService {

    @Autowired
    DailySenDao dailySenDao;
    @Override
    public DailySen getDailySen() {
        //查看有多少个对象，每一天循环
        List<DailySen> dailySenList = dailySenDao.getAllDailySen();
        log.info(String.valueOf(dailySenList));
        int size = dailySenList.size();
        int days = getDays();
        int i = days % size;
        log.info(String.valueOf(i)+  size+  days);
        DailySen dailySen = dailySenList.get(i);
        return dailySen;
    }

    //获取天数的方法
    public int getDays(){
        String dataInit = "2021-12-5 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = new Date().getTime();
        int days = 0;
        try {
            Date date = simpleDateFormat.parse(dataInit);
            days = (int) ((currentTime - date.getTime()) / (1000*3600*24));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

}
