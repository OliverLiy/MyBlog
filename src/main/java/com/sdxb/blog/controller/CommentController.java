package com.sdxb.blog.controller;

import com.sdxb.blog.dto.CommentCreateDto;
import com.sdxb.blog.dto.CommentDto;
import com.sdxb.blog.dto.ResultDto;
import com.sdxb.blog.entity.Comment;
import com.sdxb.blog.entity.User;
import com.sdxb.blog.mapper.CommentMapper;
import com.sdxb.blog.mapper.QuestionMapper;
import com.sdxb.blog.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private QuestionMapper questionMapper;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDto commentCreateDto,
                       HttpServletRequest request){
        //把User写进session
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
        Comment comment=new Comment();
        comment.setParent_id(commentCreateDto.getParent_id());
        comment.setContent(commentCreateDto.getContent());
        comment.setType(commentCreateDto.getType());
        comment.setCreatetime(System.currentTimeMillis());
        comment.setCommentor(user.getId());
        commentMapper.insert(comment);
        if (commentCreateDto.getType()==2){
            commentMapper.updatecommentcount(commentCreateDto.getParent_id());
        }else {
            questionMapper.updatecomment(commentCreateDto.getParent_id());
        }
        ResultDto resultDto=new ResultDto();
        return resultDto.success();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDto<List<CommentDto>> comments(@PathVariable(name = "id") int id,
                                                HttpServletRequest request){
        //查找type=2，即是回复评论的评论
        List<Comment> comments = commentMapper.getCommentByid(id,2);
        List<CommentDto> commentDto=new ArrayList<>();
        //找到User
        Cookie[] cookies = request.getCookies();
        User user = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findBytoken(token);
                break;
            }
        }
        //把二级评论和对应的User写进每个CommentDto集合中
        for (Comment comment:comments){
            CommentDto dto=new CommentDto();
            BeanUtils.copyProperties(comment,dto);
            dto.setUser(user);
            commentDto.add(dto);
        }
        ResultDto resultDto=new ResultDto();
        return resultDto.success(commentDto);
    }
}
