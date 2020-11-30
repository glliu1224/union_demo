package com.example.design.ornament.impl.special;

import com.example.design.ornament.IBread;
import com.example.design.ornament.impl.AbstractBread;

/**
 * 装饰器模式抽象类的子类
 * 继承AbstarctBread类,所以可以有选择的覆盖正常生产馒头的方法,并添加原有方法原来的信息,同时也可以添加自己的方法
 * 装饰者模式中这里最关键
 *  对应上述的第1个注意点:装饰者类要实现真实类同样的接口
 */
public class CornDecorator extends AbstractBread {
    public CornDecorator(IBread bread) {
        super(bread);
    }

    public void paint(){
        System.out.println("添加柠檬黄的着色剂");
    }
    @Override
    public void kneadFlour() {
        //添加着色剂后和面
        this.paint();
        super.kneadFlour();
    }
}
