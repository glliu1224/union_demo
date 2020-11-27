package com.example.demo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Student {
    private Integer id;

    private String name;

    private Integer height;

    private Integer weight;

    private Integer age;

    private String address;

    private Integer schoolId;

    private String phone;

    private String stuNumber;

    private Integer sex;

    private Integer isValid;

    private Date createTime;

    private Date updateTime;
}