package com.example.rabbit.topic;

import com.example.rabbit.constant.RabbitConstant;
import com.example.rabbit.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ReturnCallback;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 发送消息
 */
public class WeatherBureau {
    public static void main(String[] args) throws IOException, TimeoutException {
        Map area = new LinkedHashMap<String, String>();
        area.put("china.hunan.changsha.20201127", "中国湖南长沙20201127天气数据");
        area.put("china.hubei.wuhan.20201127", "中国湖北武汉20201127天气数据");
        area.put("china.hunan.zhuzhou.20201127", "中国湖南株洲20201127天气数据");
        area.put("us.cal.lsj.20201127", "美国加州洛杉矶20201127天气数据");

        area.put("china.hebei.shijiazhuang.20201128", "中国河北石家庄20201128天气数据");
        area.put("china.hubei.wuhan.20201128", "中国湖北武汉20201128天气数据");
        area.put("china.henan.zhengzhou.20201128", "中国河南郑州20201128天气数据");
        area.put("us.cal.lsj.20201128", "美国加州洛杉矶20201128天气数据");

        //获取连接
        Connection connection = RabbitUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(RabbitConstant.EXCHANGE_WEATHER_TOPIC, "topic",true);
        //开启监听
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                //第二个参数代表接收的消息是否为批量接收，一般用不到
                System.out.println("消息已经被Broker接收，Tag:" + l);
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("消息已经别Broker拒收，Tag:" + l);
            }
        });

        channel.addReturnListener((ReturnCallback) (r) -> {
            System.out.println("==========================");
            System.out.println("Return编码:" + r.getReplyCode() + "Return描述:" + r.getReplyText());
            System.out.println("交换机:" + r.getExchange() + "路由Key:" + r.getRoutingKey());
            System.out.println("==========================");
        });

        Iterator<Map.Entry<String,String>> iterator = area.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();

            //routingKey 第二个参数相当于数据筛选条件
            //第三个参数:mandatory   true代表如果消息无法正常投递则return回生产者，如果false，则直接将消息废弃
            //第一个参数：交换机   第二个参数：routingKey  第三个参数：       第五个：数据
            channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER_TOPIC,next.getKey(),true,null,next.getValue().getBytes());
        }

        //如果此处关闭连接，无法对消息进行监听
        /*channel.close();
        connection.close();*/
    }
}
