package com.jiu.api;

import com.jiu.client.TestFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lukai on 2018/8/2.
 */
@RestController
@RequestMapping("/api")
public class ApiTestController {

    @Autowired
    TestFeignClient testClient;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/getMessage",method = {RequestMethod.GET,RequestMethod.POST})
    public String getMessage(){
        return testClient.getMessage();
    }

    @RequestMapping(value = "/getMessage2",method = {RequestMethod.GET,RequestMethod.POST})
    public String getMessage2(){
        return restTemplate.postForObject("http://JIU-SERVICE-A:9100/test/getMessage",null,String.class);
    }
}
