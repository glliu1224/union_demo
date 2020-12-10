package com.example.annotations;
@Inheritable
public class InheritableFather {
    public InheritableFather() {
        System.out.println("InheritableFather:" + InheritableFather.class.isAnnotationPresent(Inheritable.class));
    }
}
