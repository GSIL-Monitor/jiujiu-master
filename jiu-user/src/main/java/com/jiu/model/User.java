package com.jiu.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lukai on 2018/10/26.
 */
@Data
public class User implements Serializable {


    private static final long serialVersionUID = 3653769728989907922L;

    private Integer userId;
    private String userName;
    private String passWord;
    private String phone;
}
