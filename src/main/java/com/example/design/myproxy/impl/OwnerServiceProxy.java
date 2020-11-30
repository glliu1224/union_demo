package com.example.design.myproxy.impl;

import com.example.design.myproxy.OwnerService;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class OwnerServiceProxy implements OwnerService {

    private OwnerService ownerService;

    public OwnerServiceProxy(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Override
    public void makeFurniture(String person,String furnitureName) {
        log.info("本次被代理类入参:{}", furnitureName);
        log.info("请求接口时间:{}",new Date());
        ownerService.makeFurniture(person,furnitureName);
        log.info("请求结束时间:{}", new Date());
    }
}
