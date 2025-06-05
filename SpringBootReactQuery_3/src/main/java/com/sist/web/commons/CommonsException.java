package com.sist.web.commons;
// 공통 예외처리

import java.io.IOException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonsException {
    @ExceptionHandler(Exception.class)
    public void exception(Exception e) {
        System.out.println("===== 오류 발생 =====");
        e.printStackTrace();
        System.out.println("===================");
    }
    
    @ExceptionHandler(IOException.class)
    public void ioException(IOException e) {
        System.out.println("===== IO 오류 발생 =====");
        e.printStackTrace();
        System.out.println("=======================");
    }
}
