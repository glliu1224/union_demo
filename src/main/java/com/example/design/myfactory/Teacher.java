package com.example.design.myfactory;

/**
 * 老师，抽象类
 */
public abstract class Teacher {
    public abstract void teach();

    public void speak(String message) {
        System.out.println("老师说:" + message);
    }
}
