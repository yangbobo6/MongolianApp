package com.school.mongolian.service;

import com.github.pagehelper.PageInfo;
import com.school.mongolian.po.wechat.Brand;

public interface PageHelperService {
    PageInfo<Brand> findAllByPage(Integer currentPage,Integer pageSize);
}
