package com.jiu.mapper;

import com.jiu.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by lukai on 2018/10/26.
 */
@Mapper
public interface UserMapper {

    @Insert("insert into t_user (user_name,pass_word,phone) VALUES (#{userName},#{passWord},#{phone})")
    public int addUser(User user);
}
