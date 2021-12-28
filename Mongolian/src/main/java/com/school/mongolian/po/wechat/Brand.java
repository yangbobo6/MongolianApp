package com.school.mongolian.po.wechat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@ApiModel(description = "Brand", value = "Brand")
@Table(name = "brand")
@Data
public class Brand {
    @ApiModelProperty(value = "品牌id", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ApiModelProperty(value = "品牌名称", required = false)
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "品牌的首字母", required = false)
    @Column(name = "letter")
    private String letter;

    @ApiModelProperty(value = "排序", required = false)
    @Column(name = "seq")
    private int seq;
}
