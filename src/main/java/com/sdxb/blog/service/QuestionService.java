package com.sdxb.blog.service;

import com.sdxb.blog.dto.PageDto;
import com.sdxb.blog.dto.Questiondto;
import com.sdxb.blog.entity.Question;
import com.sdxb.blog.entity.User;
import com.sdxb.blog.mapper.QuestionMapper;
import com.sdxb.blog.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private UserMapper userMapper;

    public PageDto list(int page, int size) {
        PageDto pageDto = new PageDto();
        int totalcount = questionMapper.count();
        pageDto.setPagination(totalcount,page,size);
        //size*{page-1}
        int offset = size * (page - 1);
        //每页只展示5条
        List<Question> questions = questionMapper.list(offset, size);
        List<Questiondto> questiondtoList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreateid());
            Questiondto questiondto = new Questiondto();
            //把第一个对象的所有属性拷贝到第二个对象中
            BeanUtils.copyProperties(question, questiondto);
            questiondto.setUser(user);
            questiondtoList.add(questiondto);
        }
        pageDto.setData(questiondtoList);
        return pageDto;
    }

    public PageDto list(int userid, int page, int size) {
        PageDto pageDto = new PageDto();
        int totalcount = questionMapper.countbyid(userid);
        pageDto.setPagination(totalcount,page,size);
        //size*{page-1}
        int offset = size * (page - 1);
        //每页只展示5条
        List<Question> questions = questionMapper.listbyid(userid,offset, size);
        List<Questiondto> questiondtoList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreateid());
            Questiondto questiondto = new Questiondto();
            //把第一个对象的所有属性拷贝到第二个对象中
            BeanUtils.copyProperties(question, questiondto);
            questiondto.setUser(user);
            questiondtoList.add(questiondto);
        }
        pageDto.setData(questiondtoList);
        return pageDto;
    }

    public Questiondto getbyid(int id) {
        Questiondto questiondto=new Questiondto();
        Question question=questionMapper.getbyId(id);
        //把第一个对象的所有属性拷贝到第二个对象中
        BeanUtils.copyProperties(question, questiondto);
        User user = userMapper.findById(question.getCreateid());
        questiondto.setUser(user);
        return questiondto;
    }

    public void increaseview(int id) {
        questionMapper.updateView(id);
    }

    public List<Question> getbytag(int id, String result) {
        return questionMapper.getbytag(id,result);
    }

    public List<Question> gettopten() {
        List<Question> questions=questionMapper.gettopten();
        return questions;
    }
}
