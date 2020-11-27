package com.example.design.observer.impl;

import com.example.design.observer.DisasterStation;
import com.example.design.observer.Observer;

public class HNTV extends Observer {

    public HNTV(DisasterStation disasterStation) {
        disasterStation.add(this);
    }
    @Override
    public void warning(String disaster) {
        System.out.println("河南电视台提醒您:" + disaster + "即将到来");
    }
}
