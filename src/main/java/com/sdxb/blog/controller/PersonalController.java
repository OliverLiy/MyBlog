package com.sdxb.blog.controller;

import com.sdxb.blog.dto.PageDto;
import com.sdxb.blog.entity.User;
import com.sdxb.blog.mapper.UserMapper;
import com.sdxb.blog.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

//个人中心
@Controller
public class PersonalController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private QuestionService questionService;

    @GetMapping("/personal/{action}")
    public String personal(@PathVariable(name = "action")String action,
                           Model model,
                           HttpServletRequest request,
                           @RequestParam(name = "page",defaultValue = "1")int page,
                           @RequestParam(name = "size",defaultValue = "5")int size){
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
        if (action.equals("questions")){
            model.addAttribute("section","questions");
            model.addAttribute("sectionname","我的问题");
        }else if (action.equals("information")){
            model.addAttribute("section","information");
            model.addAttribute("sectionname","我的消息");
        }

        PageDto pagination=questionService.list(user.getId(),page,size);
        model.addAttribute("pagination", pagination);
        return "personal";
    }

}
