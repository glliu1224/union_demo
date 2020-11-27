package com.example.design.factory.impl;

import com.example.design.factory.Logger;

public class Bizlog implements Logger {
    @Override
    public void printLog() {
        System.out.println("打印biz日志");
    }
}
