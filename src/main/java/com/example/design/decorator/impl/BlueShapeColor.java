package com.example.design.decorator.impl;

import com.example.design.decorator.Shape;
import com.example.design.decorator.ShapeColor;

public class BlueShapeColor implements ShapeColor {
    @Override
    public void color(Shape shape) {
        System.out.println("Border Color:Blue");
    }
}
