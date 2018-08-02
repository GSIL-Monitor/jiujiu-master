package com.jiu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lukai on 2018/8/2.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${my.message}")
    private String message;

    @RequestMapping(value = "/getMessage",method = {RequestMethod.POST})
    public String getMessage(){
        return message;
    }
}
