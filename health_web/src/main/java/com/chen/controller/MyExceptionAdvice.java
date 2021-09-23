package com.chen.controller;


import com.chen.entity.Result;
import com.chen.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class MyExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(MyExceptionAdvice.class);

    @ExceptionHandler(MyException.class)
    public Result handleMyException(MyException e){
        log.info("进入了自定义异常处理，用户行为不规范");
        return new Result(false,e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error("发生未知异常了",e);
        return new Result(false,"发生未知异常，请联系管理员");
    }
/*
    @ExceptionHandler(MyException.class)
    public Result handleMyException(MyException e){
        log.info("预约数已满，请下次在预约！");
        return new Result(false,e.getMessage());
    }*/

}
