package com.example.design.decorator.impl;

import com.example.design.decorator.Shape;
import com.example.design.decorator.ShapeColor;
import com.example.design.decorator.ShapeDecorator;

/**
 * 抽象类的实现类，
 * 抽象类中有成员变量decoratedShape
 * 调用自身setRedBorder方法对原有方法进行修饰
 */
public class RedShapeDecorator extends ShapeDecorator {

    private ShapeColor shapeColor;

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        shapeColor.color(decoratedShape);
//        setRedBorder(decoratedShape);
    }

    /**
     * 装饰器模式对方法进行装饰
     * 此处装饰方法，将Border颜色修饰成红色，如果要修饰成蓝色？
     * @param decoratedShape
     */
    private void setRedBorder(Shape decoratedShape) {
        System.out.println("Border Color:Red");
    }

    public void setShapeColor(ShapeColor shapeColor) {
        this.shapeColor = shapeColor;
    }
}
