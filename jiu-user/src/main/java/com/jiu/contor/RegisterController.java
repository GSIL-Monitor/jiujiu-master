package com.jiu.contor;

import com.jiu.common.annotation.LogAnnotation;
import com.jiu.model.TUser;
import com.jiu.service.RegisterService;
import com.jiu.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lukai on 2018/10/26.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService iRegister;

    @PostMapping(value = "/addUser")
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

    @PostMapping(value = "/queryAll")
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
}
