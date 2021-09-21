package com.sdxb.blog.exception;

import com.sdxb.blog.dto.ResultDto;
import com.sdxb.blog.enums.ResponseCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by: Liyu
 * @ClassName: MyExceptionHandler
 * @Description: TODO
 * @Date: 2020/11/26 9:28
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public void Myexceptionhandler(Exception e){
        System.out.println("出现了异常"+e);
    }
}
