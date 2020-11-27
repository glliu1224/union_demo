package com.example.demo.vo;

import lombok.Data;

@Data
public class UserVO {

    private Integer id;
    private String userName;
    private String address;
    private String phone;
    private Integer sex;
    private Integer isValid;
    private String email;
}
