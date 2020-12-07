package com.example.design.myfactory.impl;

import com.example.design.myfactory.Teacher;

public class MathTeacher extends Teacher {
    @Override
    public void teach() {
        System.out.println("我正在教数学课");
    }
}
