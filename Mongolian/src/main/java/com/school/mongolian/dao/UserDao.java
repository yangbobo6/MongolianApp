package com.school.mongolian.dao;

import com.school.mongolian.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("select * from mongo_user where id = #{id}")
    public User getById(@Param("id")long id);

    @Insert("insert into mongo_user(id,name,password,sex,phone) values(#{id},#{name},#{password},#{sex},#{phone})")
    public int insert(User user);

    @Select("select * from mongo_user where name = #{name}")
    public User getByName(String name);
}
