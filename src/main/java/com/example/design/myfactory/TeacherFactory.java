package com.example.design.myfactory;

/**
 * 老师，工厂类
 */
public class TeacherFactory {

    public static Teacher getTeacher(Class<? extends Teacher> clazz) throws IllegalAccessException, InstantiationException {
        Teacher teacher = (Teacher) clazz.newInstance();
        return teacher;
    }
}
