package com.example.annotations.myannotation;

public class Person {

    @MyAnnotation
    @Deprecated
    public void empty() {
        System.out.println("\nempty");
    }

    @MyAnnotation(value = {"girl", "boy"})
    public void somebody(String name, int age) {
        System.out.println("\nsomebody:" + name + "," + age);
    }
}
