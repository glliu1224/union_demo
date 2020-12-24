package com.example.demo.entity.localtest;

import lombok.Data;

import java.util.Date;

@Data
public class UserDO {

    private Integer id;

    private String userName;

    private String password;

    private Date createTime;

    private Date updateTime;

    private Integer isValid;
}
