package com.example.design.observer;

/**
 * 观察者接口
 */
public abstract class Observer {
    //向本台观众发送灾难预警
    public abstract void warning(String disaster);
}
