package com.example.design.proxy.impl;

import com.example.design.proxy.ICoder;

/**
 * 真是角色，（程序员）实现真正的业务
 */
public class JavaCoder implements ICoder {

    private String name;

    public JavaCoder(String name){
        this.name = name;
    }

    @Override
    public void implDemands(String demandName) {
        System.out.println(name + "开发了接口" + demandName + "用JAVA!");
    }
}
