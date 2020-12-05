package com.example.rabbit.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Ticket {

    private Integer id;
    private String name;
    private BigDecimal price;
    private Date createTime;
    private Date updateTime;
}
