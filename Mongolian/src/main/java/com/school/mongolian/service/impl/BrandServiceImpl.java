package com.school.mongolian.service.impl;

import com.school.mongolian.dao.wechatDao.BrandDao;
import com.school.mongolian.po.wechat.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl {
    @Autowired
    private BrandDao brandDao;

    public List<Brand> findAll(){
        return brandDao.selectAll();
    }

    public List<Brand> search(Brand brand){
        //自定义条件搜索对象 Example
        Example example = new Example(Brand.class);
        // 根据条件搜索对象 example 创建条件构造器
        Example.Criteria criteria = example.createCriteria();
        //brand 不为空
        if (brand!=null){
            // brand.name != null 根据品牌名称模糊搜索：
            // SELECT * FROM brand WHERE name LIKE "%brand.getName%"
            if(!StringUtils.isEmpty(brand.getName())){
                /*
                 * 1.Brand对象的成员属性名
                 * 2.占位符参数，搜索的条件
                 */
                criteria.andLike("name","%"+brand.getName()+"%");
            }

            // brand.letter != null 根据品牌首字母搜索
            // SELECT * FROM tb_brand WHERE name LIKE "%歪比巴卜%" AND letter = 'W'
            if(!StringUtils.isEmpty(brand.getLetter())){
                criteria.andEqualTo("letter",brand.getLetter());
            }
        }
        // 执行根据自定义搜索对象查询
        return brandDao.selectByExample(example);
    }
}
