package com.school.mongolian.po.wechat;

import lombok.Data;

import java.util.Date;
@Data
public class StudentInfo {
    private int id;
    private String  applicationTime;
    private String name;
    private int studentId;
    private String faculty;
    private int grade;
    private String specialty;
    private String sex;
    private String nationality;
    private String phone;
    private String idCard;
    private String counselorName;
    private String url;

    private String outShiJian;
    private String backShiJian;
    private String backTime;
    private String outTime;
    private String otherReason;

}
