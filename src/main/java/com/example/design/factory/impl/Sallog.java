package com.example.design.factory.impl;

import com.example.design.factory.Logger;

public class Sallog implements Logger {
    @Override
    public void printLog() {
        System.out.println("打印调用日志接口");
    }
}
