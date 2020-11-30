package com.example.design.ornament;

import com.example.design.ornament.impl.NormalBread;
import com.example.design.ornament.impl.special.CornDecorator;
import com.example.design.ornament.impl.special.SweetDecorator;

/**
 * 装饰器模式测试类
 */
public class OrnamentMain {
    public static void main(String[] args) {
        System.out.println("=======开始装饰馒头");
        IBread normalBread = new NormalBread();
        normalBread = new SweetDecorator(normalBread);
        normalBread = new CornDecorator(normalBread);
        normalBread.process();
        System.out.println("=======装饰馒头结束");
    }
}
