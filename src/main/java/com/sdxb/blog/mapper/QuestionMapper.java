package com.sdxb.blog.mapper;

import com.sdxb.blog.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("insert into question(title,description,createid,tag,createtime) values (#{title},#{description},#{createid},#{tag},#{createtime})")
    void createquestion(Question question);

    @Select("select * from question;")
    List<Question> list();
}
