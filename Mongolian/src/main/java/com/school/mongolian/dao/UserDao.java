package com.school.mongolian.dao;

import com.school.mongolian.po.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Select("select * from mongo_user where id = #{id}")
    public User getById(@Param("id")int id);

    //注册新用户
    @Insert("insert into mongo_user(name,password,phone) values(#{name},#{password},#{phone})")
    public Boolean insert(@Param("name")String name,@Param("password")String password,@Param("phone")String phone);

    @Select("select * from mongo_user where name = #{name}")
    public User getByName(@Param("name")String name);

    @Select("select * from mongo_user where phone = #{phone}")
    public User getByPhone(@Param("phone")String phone);

    //user   完善个人信息接口  （性别  等）
    @Update("update mongo_user set name = #{name},sex = #{sex} where id = #{id}")
    public Boolean updateInfo(@Param("name")String name,@Param("sex")String sex,@Param("id")int id);


}
