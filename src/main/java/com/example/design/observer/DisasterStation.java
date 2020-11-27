package com.example.design.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 被观察者
 */
public class DisasterStation {

    //存放电视台的注册信息
    List<Observer> list = new ArrayList<>();

    //添加注册
    public void add(Observer observer) {
        list.add(observer);
    }

    //监测到地震
    public void happen(String disaster) {
        //通知所有的观察者
        Iterator<Observer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            iterator.next().warning(disaster);
        }
    }
}
