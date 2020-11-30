package com.example.design.ornament.impl.special;

import com.example.design.ornament.IBread;
import com.example.design.ornament.impl.AbstractBread;

/**
 * 装饰器模式
 * 生产有甜蜜素的"甜馒头"
 */
public class SweetDecorator extends AbstractBread {
    public SweetDecorator(IBread bread) {
        super(bread);
    }

    public void paint(){
        System.out.println("添加甜蜜素...");
    }

    @Override
    public void kneadFlour() {
        //添加甜蜜素后和面
        this.paint();
        super.kneadFlour();
    }
}
