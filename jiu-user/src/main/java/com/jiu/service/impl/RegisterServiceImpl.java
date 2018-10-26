package com.jiu.service.impl;

import com.jiu.mapper.UserMapper;
import com.jiu.model.User;
import com.jiu.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lukai on 2018/10/26.
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(User user) throws Exception {
        int i = userMapper.addUser(user);
        return i;
    }
}
