package com.example.design.responsibility;
/**
*@Description 责任链模式 请求抽象类
*@Author glliu
*@Date 2020/12/11
*/

public abstract class AbstractLogger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;

    //责任链的下一个元素
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level,message);
        }
    }

    abstract protected void write(String message);

}

/*
* 03
* 03
* 01
* 02
* 04
* 05
* 02
* 06
* */
