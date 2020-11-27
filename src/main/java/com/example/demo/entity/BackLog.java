package com.example.demo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class BackLog {
    private Integer id;

    private Integer proId;

    private Integer conId;

    private String description;

    private Date createTime;

    private Date updateTime;

    private Integer isValid;
}