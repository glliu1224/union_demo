package com.example.demo.enums;

import com.example.demo.entity.BackLog;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;

public enum ClassEnum {
    STUDENT_CLASS("学生", Student.class),
    USER_CLASS("用户", User.class),
    BACK_LOG("待办任务", BackLog.class)
    ;

    private String desc;
    private Class<?> aClass;

    ClassEnum(String desc, Class<?> aClass) {
        this.desc = desc;
        this.aClass = aClass;
    }

    public String getDesc() {
        return desc;
    }

    public Class<?> getaClass() {
        return aClass;
    }
}
