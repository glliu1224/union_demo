package com.example.annotations;

import java.util.Date;

public class InheritableSon extends InheritableFather{
    public InheritableSon() {
        super();
        System.out.println("InheritableSon:" + InheritableSon.class.isAnnotationPresent(Inheritable.class));
    }

    public static void main(String[] args) {
        InheritableSon is = new InheritableSon();

        @SuppressWarnings(value = {"deprecation"})
        Date date = new Date(113, 2, 13);
    }
}
