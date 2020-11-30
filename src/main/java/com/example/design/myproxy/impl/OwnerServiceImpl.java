package com.example.design.myproxy.impl;

import com.example.design.myproxy.OwnerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private String furnitureName;

    public OwnerServiceImpl(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    @Override
    public void makeFurniture(String person,String furnitureName) {
        /*此处可以写开发中的与业务相关的逻辑*/
        log.info("{}制造了家具:{}",person,furnitureName);
    }
}
