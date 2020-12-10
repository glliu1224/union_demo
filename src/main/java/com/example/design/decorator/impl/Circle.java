package com.example.design.decorator.impl;

import com.example.design.decorator.Shape;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape:Circle");
    }
}
