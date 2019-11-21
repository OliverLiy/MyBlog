package com.sdxb.blog.controller;

import com.sdxb.blog.dto.CommentDto;
import com.sdxb.blog.dto.Questiondto;
import com.sdxb.blog.entity.User;
import com.sdxb.blog.mapper.QuestionMapper;
import com.sdxb.blog.mapper.UserMapper;
import com.sdxb.blog.service.CommentService;
import com.sdxb.blog.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {

    @Resource
    private QuestionService questionService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")int id,
                           Model model,
                           HttpServletRequest request){
        //查找cookies，观察是否有token存在
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "login";
        }
        User user = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findBytoken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        Questiondto questiondto=questionService.getbyid(id);
        //增加阅读数
        questionService.increaseview(id);
        model.addAttribute("questionDto",questiondto);

        List<CommentDto> comments=commentService.getByid(id);
        model.addAttribute("comments",comments);
        return "question";
    }
}
