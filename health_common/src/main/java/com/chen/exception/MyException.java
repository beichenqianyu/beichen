package com.chen.exception;

public class MyException extends RuntimeException{
    /**
     * 构建要传入提示的信息
     */
    public MyException(String message){
        super(message);
    }

}
