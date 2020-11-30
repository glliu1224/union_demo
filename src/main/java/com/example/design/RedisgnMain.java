package com.example.design;

import com.example.design.factory.Logger;
import com.example.design.factory.LoggerEnum;
import com.example.design.factory.LoggerFactory;
import com.example.design.moveproxy.CoderDynamicProxy;
import com.example.design.myobserver.OutSource;
import com.example.design.myobserver.Union;
import com.example.design.myobserver.impl.RH;
import com.example.design.myobserver.impl.WX;
import com.example.design.myobserver.impl.XZ;
import com.example.design.myproxy.FurnitureProxy;
import com.example.design.myproxy.OwnerService;
import com.example.design.myproxy.impl.OwnerServiceImpl;
import com.example.design.observer.DisasterStation;
import com.example.design.observer.Observer;
import com.example.design.observer.impl.CCTV;
import com.example.design.observer.impl.HNTV;
import com.example.design.observer.impl.SDTV;
import com.example.design.proxy.ICoder;
import com.example.design.proxy.impl.CoderProxy;
import com.example.design.proxy.impl.JavaCoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class RedisgnMain {

    /**
     * 调用观察者模式
     */
    public static void main(String[] args) {

        /*调用观察者模式*/
        DisasterStation disasterStation = new DisasterStation();
        Observer cctv = new CCTV(disasterStation);
        Observer hntv = new HNTV(disasterStation);
        Observer sdtv = new SDTV(disasterStation);
        disasterStation.happen("地震");

        /**
         * 调用工厂模式
         */
        try {
            Logger logger = LoggerFactory.getLogger(LoggerEnum.BIZ_LOG.getClazz());
            logger.printLog();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=============");
        Union union = new Union();
        OutSource wx = new WX(union);
        OutSource rh = new RH(union);
        OutSource xz = new XZ(union);
        union.happen("JAVA开发工程师");

//        System.out.println("================代理模式实现测试================");
//        System.out.println("================================================");
//        //定义一个java码农（真实角色）
//        ICoder coder = new JavaCoder("Zhang");
//        //定义一个产品经理（代理角色）
//        ICoder proxy = new CoderProxy(coder);
//        //让产品经理实现一个需求
//        proxy.implDemands("Add user manageMent");

        System.out.println("================动态代理模式测试================");
        System.out.println("============模拟用户找产品经理改需求============");

        //要代理的真实对象
        ICoder coder = new JavaCoder("张三");
        //创建中介类实例
        InvocationHandler handler = new CoderDynamicProxy(coder);
        //获取类加载器
        ClassLoader cl = coder.getClass().getClassLoader();
        //动态产生一个代理类
        ICoder proxy = (ICoder) Proxy.newProxyInstance(cl, coder.getClass().getInterfaces(), handler);
        //通过代理类，执行doSomething方法；
        proxy.implDemands("登录认证方式修改");


        System.out.println("================我的动态代理模式测试================");
        System.out.println("===================模拟本地制造家具=================");

        //要代理的真实对象
        OwnerService ownerService = new OwnerServiceImpl("你爸爸");
        //创建中介类实例
        FurnitureProxy proxy1 = new FurnitureProxy(ownerService);
        //获取类加载器
        ClassLoader classLoader = ownerService.getClass().getClassLoader();
        //动态产生一个代理类
        OwnerService ownerService1 = (OwnerService) Proxy.newProxyInstance(classLoader, ownerService.getClass().getInterfaces(), proxy1);
        //通过代理类，执行doSomething方法；
        ownerService1.makeFurniture("你爸爸","椅子");
    }
}