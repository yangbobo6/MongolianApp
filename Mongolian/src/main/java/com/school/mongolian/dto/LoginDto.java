package com.school.mongolian.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDto {
    private String phone;
    private String password;
}

