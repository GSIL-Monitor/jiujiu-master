package com.jiu.client.hystrix;

import com.jiu.client.TestFeignClient;
import org.springframework.stereotype.Component;

/**
 * Created by lukai on 2018/8/2.
 */
@Component
public class TestFeignClientHystrix implements TestFeignClient {

    @Override
    public String getMessage() {
        return "getMessage error";
    }
}
