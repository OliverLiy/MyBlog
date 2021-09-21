package com.sdxb.blog.interceptor;

import com.sdxb.blog.entity.User;
import com.sdxb.blog.mapper.NotificationMapper;
import com.sdxb.blog.mapper.UserMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author by: Liyu
 * @ClassName: UserInterceptor
 * @Description: TODO
 * @Date: 2021/9/21 17:23
 */
@Configuration
public class UserInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Resource
    private NotificationMapper notificationMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.sendRedirect("/login");
            return false;
        }
        User user = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findBytoken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                    //获取未读的消息数量
                    int unreadnum=notificationMapper.getunreadcount(user.getId());
                    request.getSession().setAttribute("unreadnum",unreadnum);
                }
                break;
            }
        }
        if (user==null){
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
