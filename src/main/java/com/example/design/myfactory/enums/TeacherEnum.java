package com.example.design.myfactory.enums;

import com.example.design.myfactory.impl.ChineseTeacher;
import com.example.design.myfactory.impl.MathTeacher;
import com.example.design.myfactory.impl.PhysicsTeacher;

public enum TeacherEnum {
    CHINESE(1, ChineseTeacher.class),
    MATH(2, MathTeacher.class),
    PHYSICS(3, PhysicsTeacher.class);
    //科目code  1-语文  2-数学  3:-化学
    private Integer teaCode;
    private Class clazz;

    TeacherEnum(Integer teaCode, Class clazz) {
        this.teaCode = teaCode;
        this.clazz = clazz;
    }

    TeacherEnum() {
    }

    public Integer getTeaCode() {
        return teaCode;
    }

    public Class getClazz() {
        return clazz;
    }
}
