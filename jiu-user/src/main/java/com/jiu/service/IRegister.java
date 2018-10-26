package com.jiu.service;

import com.jiu.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by lukai on 2018/10/26.
 */
@Service
public interface IRegister {

    public int addUser(User user) throws Exception;
}
