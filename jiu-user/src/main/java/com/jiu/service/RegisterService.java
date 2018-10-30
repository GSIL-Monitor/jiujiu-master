package com.jiu.service;

import com.jiu.model.TUser;

import java.util.List;

/**
 * Created by lukai on 2018/10/26.
 */
public interface RegisterService {

    public int addUser(TUser user) throws Exception;

    public List<TUser> queryAll() throws Exception;
}
