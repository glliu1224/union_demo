package com.example.design.factory;

import com.example.design.factory.impl.Bizlog;
import com.example.design.factory.impl.Dblog;
import com.example.design.factory.impl.Sallog;

public enum LoggerEnum {

    DB_LOG("dbLog","记录数据库访问的日志","db-info.log",Dblog.class),
    BIZ_LOG("bizLog","记录业务层的日志","db-info.log",Bizlog.class),
    SAL_LOG("salLog","记录接口调用的日志","db-info.log",Sallog.class);

    private String name;
    private String desc;
    private String position;
    private Class<?> clazz;

    private LoggerEnum(String name, String desc, String position, Class<?> clazz) {
        this.name = name;
        this.desc = desc;
        this.position = position;
        this.clazz = clazz;
    }
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPosition() {
        return position;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
