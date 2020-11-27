package com.example.design.factory.impl;

import com.example.design.factory.Logger;

public class Dblog implements Logger {
    @Override
    public void printLog() {
        System.out.println("打印数据库日志");
    }
}
