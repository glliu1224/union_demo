package com.example.design.moveproxy;

import com.example.design.proxy.ICoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 定义一个位于代理类与委托类之间的中介类，也叫动态代理类
 * 要求实现InvocationHandler接口
 */
public class CoderDynamicProxy implements InvocationHandler {

    //被代理的实例
    private ICoder coder;
    public CoderDynamicProxy(ICoder _coder){
        this.coder = _coder;
    }

    /**
     * 调用被代理的方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(System.currentTimeMillis());
        Object result = method.invoke(coder, args);
        System.out.println(System.currentTimeMillis());
        return result;
    }
}
