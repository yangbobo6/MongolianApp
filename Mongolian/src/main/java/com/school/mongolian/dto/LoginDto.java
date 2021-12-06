package com.school.mongolian.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登录接口测试")
public class LoginDto {
    @ApiModelProperty("name")
    private String name;
    @ApiModelProperty("password")
    private String password;
}

