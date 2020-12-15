package com.example.design.responsibility;

import com.example.design.responsibility.impl.ConsoleLogger;
import com.example.design.responsibility.impl.ErrorLogger;
import com.example.design.responsibility.impl.FileLogger;

public class ChainPatternDemo {
    private static AbstractLogger getChainLoggers() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);
        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);
        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainLoggers();
        loggerChain.logMessage(AbstractLogger.INFO,"This is information");
        loggerChain.logMessage(AbstractLogger.DEBUG, "This is debug level information");
        loggerChain.logMessage(AbstractLogger.ERROR,"This is error information");
    }
}
