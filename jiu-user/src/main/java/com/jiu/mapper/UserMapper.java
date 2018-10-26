package com.jiu.mapper;

import com.jiu.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by lukai on 2018/10/26.
 */
@Repository
public interface UserMapper {

    public int addUser(User user);
}
