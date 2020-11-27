package com.example.design.factory;

public class LoggerFactory {

    /**
     * 工厂方法，调用工厂创建对象，摆脱之前的new方法创建的硬编码创建对象方法
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Logger getLogger(Class<?> clazz)throws Exception{
        Logger logger = (Logger)clazz.newInstance();
        return logger;
    }
}
