package com.sdxb.blog.service;

import com.sdxb.blog.dto.Questiondto;
import com.sdxb.blog.entity.Question;
import com.sdxb.blog.entity.User;
import com.sdxb.blog.mapper.QuestionMapper;
import com.sdxb.blog.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Questiondto> list() {
        List<Question> questions = questionMapper.list();
        List<Questiondto> questiondtoList=new ArrayList<>();
        for (Question question:questions){
            User user=userMapper.findById(question.getCreateid());
            Questiondto questiondto = new Questiondto();
            //把第一个对象的所有属性拷贝到第二个对象中
            BeanUtils.copyProperties(question,questiondto);
            questiondto.setUser(user);
            questiondtoList.add(questiondto);
        }
        return questiondtoList;
    }
}
