package com.jiu.contor;

import com.jiu.model.User;
import com.jiu.service.IRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lukai on 2018/10/26.
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private IRegister iRegister;

    @PostMapping(value = "/addUser")
    public int addUser(User user) {
        try {
            int i = iRegister.addUser(user);
            return i;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
