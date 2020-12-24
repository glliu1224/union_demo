package com.example.demo.entity;

import lombok.Data;

@Data
public class GCEntity {

    private String name;
    private String password;
    private byte[] bytes;

    public GCEntity() {
        this.bytes = new byte[1000 * 100];
        System.out.println("初始化GCEntity");
    }
}
