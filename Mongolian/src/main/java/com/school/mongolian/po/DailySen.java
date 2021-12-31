package com.school.mongolian.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailySen {
    private int id;
    private String name;
    private String photoUrl;
    private int collect;
}
