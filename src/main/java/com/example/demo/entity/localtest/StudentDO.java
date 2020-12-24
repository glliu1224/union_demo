package com.example.demo.entity.localtest;

import lombok.Data;

import java.util.Date;

@Data
public class StudentDO {

    private Integer stuId;

    private String name;

    private Integer grade;

    private String address;

    private String phone;

    private Integer sex;

    private String email;

    /**
     * 班级
     */
    private Integer clazz;

    private String stuNumber;

    private Integer isValid;

    private Date createTime;

    private Date updateTime;
}
