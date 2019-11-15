package com.sdxb.blog.controller;

import com.sdxb.blog.dto.Questiondto;
import com.sdxb.blog.mapper.QuestionMapper;
import com.sdxb.blog.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@Controller
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")int id,
                           Model model){
        Questiondto questiondto=questionService.getbyid(id);
        model.addAttribute("questionDto",questiondto);
        return "question";
    }
}
