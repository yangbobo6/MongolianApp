package com.school.mongolian.dto;

import lombok.Data;
import java.util.List;
@Data
public class FolktaleDto {
    private int id;
    private String name;
    private List<String> urlList;
    private int collect;
    private int type;
}
