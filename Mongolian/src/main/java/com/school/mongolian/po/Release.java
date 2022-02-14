package com.school.mongolian.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author: yangbo
 * @Date: 2022-02-13-17:55
 * @Description:
 */
@Data
public class Release {
    private int id;
    private int userId;   //用户id
    private String name;  //用户名
    private String text;  //发布内容
    private Date date;    //发布日期
}
