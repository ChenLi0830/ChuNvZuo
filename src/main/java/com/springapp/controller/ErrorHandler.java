package com.springapp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Chen on 15-01-17.
 */
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public String handleException (Exception ex){
        System.out.println(ex.getMessage());
        ex.printStackTrace();
        return "error";
    }

}
