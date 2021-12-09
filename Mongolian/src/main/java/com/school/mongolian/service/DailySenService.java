package com.school.mongolian.service;

import com.school.mongolian.po.DailySen;
import org.springframework.stereotype.Service;


public interface DailySenService {
    //获取每日一句，每天都不同
    public DailySen getDailySen();

}
