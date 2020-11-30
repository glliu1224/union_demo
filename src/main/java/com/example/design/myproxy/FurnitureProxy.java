package com.example.design.myproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理的中介类
 */
public class FurnitureProxy implements InvocationHandler {

    private OwnerService ownerService;

    public FurnitureProxy(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始调用自己写的动态代理");
        Object invoke = method.invoke(ownerService, args);
        return invoke;
    }
}
