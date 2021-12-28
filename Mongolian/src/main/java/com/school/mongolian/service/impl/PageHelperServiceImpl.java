package com.school.mongolian.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.school.mongolian.dao.wechatDao.BrandDao;
import com.school.mongolian.po.wechat.Brand;
import com.school.mongolian.service.PageHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageHelperServiceImpl implements PageHelperService {

    @Autowired
    BrandDao brandDao;

    @Override
    public PageInfo<Brand> findAllByPage(Integer currentPage, Integer pageSize) {
        /*
         * 分页实现相关参数：PageHelper.startPage(currentPage,pageSize); 之后紧跟Brand对象集合的查询
         * 1.当前页
         * 2.每页多少条记录
         */

        PageHelper.startPage(currentPage, pageSize);
        System.out.println(currentPage+" "+pageSize);
        List<Brand> brandList = brandDao.selectAll();
        //封装到PageInfo对象
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        System.out.println(pageInfo);
        return pageInfo;
    }
}
