package com.school.mongolian.dao.wechatDao;

import com.school.mongolian.po.wechat.Brand;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface BrandDao extends Mapper<Brand> {
}
