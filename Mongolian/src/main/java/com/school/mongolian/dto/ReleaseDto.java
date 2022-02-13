package com.school.mongolian.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author: yangbo
 * @Date: 2022-02-13-17:50
 * @Description:
 */
@Data
public class ReleaseDto {
    private int id;
    private int userId;
    private String name;
    private String text;
    private Date date;
}
