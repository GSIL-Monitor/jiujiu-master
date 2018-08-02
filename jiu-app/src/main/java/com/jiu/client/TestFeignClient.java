package com.jiu.client;

import com.jiu.client.hystrix.TestFeignClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lukai on 2018/8/2.
 * 调用JIU-SERVICE-A服务
 * 熔断机制。方法报错，回调用相应的熔断机制，即TestFeignClientHystrix中的方法
 */
@FeignClient(name = "JIU-SERVICE-A" ,fallback = TestFeignClientHystrix.class)
public interface TestFeignClient {

    /**
     * 通过spring.application.name去调用相应的应用
     * 注意调用接口的method是什么方式。这里必须是一样的方式
     * @return
     */
    @RequestMapping(value = "/test/getMessage", method = RequestMethod.POST)
    public String getMessage();
}
