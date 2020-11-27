package com.example.demo.entity;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String userName;
    private String address;
    private String phone;
    private Integer sex;
    private String email;
    private Integer isValid;
}
