package com.school.mongolian.po;

import lombok.Data;

@Data
public class Word{
    private int id;
    private String name;
    private String photoWordUrl;
    private String chinese;
    private String mongolian;
    private String ciXing;
    private int collect;
}
