package com.example.design;

import com.example.design.factory.Logger;
import com.example.design.factory.LoggerEnum;
import com.example.design.factory.LoggerFactory;
import com.example.design.myobserver.OutSource;
import com.example.design.myobserver.Union;
import com.example.design.myobserver.impl.RH;
import com.example.design.myobserver.impl.WX;
import com.example.design.myobserver.impl.XZ;
import com.example.design.observer.DisasterStation;
import com.example.design.observer.Observer;
import com.example.design.observer.impl.CCTV;
import com.example.design.observer.impl.HNTV;
import com.example.design.observer.impl.SDTV;

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
    }
}
