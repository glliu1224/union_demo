package com.example.design.decorator;

import com.example.design.decorator.impl.Circle;
import com.example.design.decorator.impl.Rectangle;
import com.example.design.decorator.impl.RedShapeColor;
import com.example.design.decorator.impl.RedShapeDecorator;

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Shape circle = new Circle();
        ShapeColor shapeColor = new RedShapeColor();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        ((RedShapeDecorator) redRectangle).setShapeColor(shapeColor);

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
