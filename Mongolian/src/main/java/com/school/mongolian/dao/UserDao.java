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

    //注册新用户
    @Insert("insert into mongo_user(name,password,phone) values(#{name},#{password},#{phone})")
    public Boolean insert(@Param("name")String name, @Param("password")String password,@Param("phone")String phone);

    @Select("select * from mongo_user where name = #{name}")
    public User getByName(@Param("name")String name);

    @Select("select * from mongo_user where phone = #{phone}")
    public User getByPhone(@Param("phone")String phone);


}
