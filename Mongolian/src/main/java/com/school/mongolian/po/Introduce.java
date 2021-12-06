package com.school.mongolian.po;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

@Data
public class Introduce {
    private int id;
    private String name;
    private String mainUrl;
    private String firstUrl;
    private String secondUrl;

}
