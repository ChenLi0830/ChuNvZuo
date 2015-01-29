package com.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Chen on 15-01-18.
 */
@Controller

public class TestController {
    @RequestMapping("/test")
    public String showTest(){
        return "test";
    }

}
