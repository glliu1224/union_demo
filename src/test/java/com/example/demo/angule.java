package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Inet4Address;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class angule {

    @Test
    public void test() {
        Integer a = 2;
        Integer b = 2;
        log.info("第一个HashCode:{}",a.hashCode());
    }

    @Test
    public void getDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = format.parse("2020-12-18");
        Date parse1 = format.parse("2020-12-19");
        long l = parse1.getTime() - parse.getTime();
        System.out.println("一天时间戳差:" + l);
    }

    @Test
    public void testBigDecimal() {
        BigDecimal decimal = new BigDecimal("3");
        BigDecimal divide = new BigDecimal("10").divide(decimal,6,RoundingMode.HALF_UP);
        System.out.println("获取结果:" + divide.toString());
    }

    @Test
    public void mapTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("第一个", 1);
        map.put("第二个", 2);
    }

    @Test
    public void foolTest() {
        int a = 1;
        int b = 2;
        int x = 20;
        int y = 30;
        int copacity = 29;
        System.out.println("|||打印数字:" + (a | b));
        System.out.println("|||打印数字:" + (x | y));
        System.out.println("CAPACITY:" + (1 << copacity));
    }

    @Test
    public void atomicTest() {
        final AtomicInteger integer = new AtomicInteger();
        int i1 = integer.incrementAndGet();
        int i = integer.get();
        System.out.println("初始化的值:" + i1);
    }
}
