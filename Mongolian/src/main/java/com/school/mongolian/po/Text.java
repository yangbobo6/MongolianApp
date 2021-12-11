package com.school.mongolian.po;

import lombok.Data;

@Data
public class Text {
    private int id;
    private String name;
    private String chinese;
    private String mongolian;
    private String textUrl;
    private String type;
}
