package com.jiu.contor;

import com.jiu.common.annotation.LogAnnotation;
import com.jiu.common.exception.CustomException;
import com.jiu.model.TUser;
import com.jiu.service.RegisterService;
import com.jiu.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lukai on 2018/10/26.
 */
@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService iRegister;

    @GetMapping(value = "/addUser")
    @ResponseBody
    @LogAnnotation
    public int addUser(TUser user) {
        try {
            int i = iRegister.addUser(user);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @GetMapping(value = "/queryAll")
    @ResponseBody
    @LogAnnotation
    public List<TUser> queryAll() {
        try {
            return iRegister.queryAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/toRegister")
    public String toRegister() {
        return "test";
    }


    @RequestMapping(value = "/testSend")
    @ResponseBody
    public boolean testSend() {
        boolean succ = false;
        succ = MailUtil.send("smtp.exmail.qq.com", "lukai@hanxinbank.com", "hbwhlukai@163.com",
                "测试", "测试内容", "lukai@hanxinbank.com",
                "Lu2452204520");
        return succ;
    }


    @RequestMapping(value = "/log")
    @ResponseBody
    public String log() {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        return "success";
    }

    @GetMapping("/ex")
    @ResponseBody
    public String ex(Integer num) {
        if (num == null) {
            throw new CustomException(400, "num不能为空");
        }
        int i = 10 / num;
        return "result:" + i;
    }
}
