package com.school.mongolian.dao;

import com.school.mongolian.dto.Grade;
import com.school.mongolian.po.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestDao {
    @Select("select max(id) as id ,any_value(name) as name,any_value(typeName) as typeName,type,grade from mongo_test where grade=#{grade} group by type;")
    public List<Grade> getByGradeClassify(@Param("grade")int grade);

    @Select("select * from (select * from mongo_test where grade = #{grade}) \n" +
            "as a where type = #{type};")
    public List<Test> getByGrade(@Param("grade")int grade,@Param("type")int type);

    @Select("select * from mongo_test where collect = 1")
    public List<Test> getTestCollect();
}
