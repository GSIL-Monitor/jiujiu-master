package com.jiu.api;

import com.jiu.client.TestFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lukai on 2018/8/2.
 */
@Controller
@RequestMapping
public class ApiTestController {

    @Autowired
    TestFeignClient testClient;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/getMessage",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getMessage(){
        String res = testClient.getMessage();
        System.out.println("res===>"+res);
        return res;
    }

    @RequestMapping(value = "/getMessage2",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getMessage2(){
        return restTemplate.postForObject("http://JIU-SERVICE-A:9100/test/getMessage",null,String.class);
    }

    @RequestMapping(value = "/toMessage",method = {RequestMethod.GET,RequestMethod.POST})
    public String toMessage(){
        return "test";
    }
}
