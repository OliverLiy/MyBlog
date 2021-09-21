package com.sdxb.blog.controller;

import com.sdxb.blog.entity.User;
import com.sdxb.blog.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//登陆
@Controller
public class logincontroller {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    @PostMapping("/logincheck")
    public String checklogin(HttpServletRequest request, HttpServletResponse response) {
        //通过request获取输入的用户名和密码在数据库中查找相关用户，如果存在就登陆成功
        User user = new User();
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        user.setName(name);
        user.setPassword(password);
        User newUser = userMapper.select(user);
        if (newUser != null) {
            String token = newUser.getToken();
            response.addCookie(new Cookie("token", token));
        } else {
            //登陆失败，重新登陆
        }
        return "redirect:/index";
    }

    //退出登陆
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/index";
    }
}
