package com.example.design.myfactory;

import com.example.design.myfactory.enums.TeacherEnum;

public class TeacherTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        Teacher teacher = TeacherFactory.getTeacher(TeacherEnum.CHINESE.getClazz());
        teacher.teach();
    }
}
