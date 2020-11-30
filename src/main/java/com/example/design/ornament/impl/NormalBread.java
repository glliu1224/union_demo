package com.example.design.ornament.impl;

import com.example.design.ornament.IBread;

/**
 * 装饰器模式
 * 接口正常实现类，正常实现业务
 */
public class NormalBread implements IBread {
    @Override
    public void prepair() {
        System.out.println("准备面粉,水以及发酵粉...");
    }

    @Override
    public void kneadFlour() {
        System.out.println("和面...");
    }

    @Override
    public void steamed() {
        System.out.println("蒸馒头...香喷喷的馒头出炉了");
    }

    @Override
    public void process() {
        prepair();
        kneadFlour();
        steamed();
    }
}
