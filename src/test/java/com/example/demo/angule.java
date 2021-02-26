package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openjdk.jol.info.ClassLayout;
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

    /**
     * 对象头信息
     */
    @Test
    public void jolSample() {
        System.out.println("Object类:");
        ClassLayout layOut = ClassLayout.parseInstance(new Object());
        System.out.println(layOut.toPrintable());

        System.out.println("int数组:");
        ClassLayout intArrayLayout = ClassLayout.parseInstance(new int[]{});
        System.out.println(intArrayLayout.toPrintable());

        System.out.println("内部类A:");
        ClassLayout aLayout = ClassLayout.parseInstance(new A());
        System.out.println(aLayout.toPrintable());
    }

    /**
     * 逃逸分析
     */
    public void analyse() {

    }

    public static class A{
        int id;
        String name;
        byte aByte;
        Object object;
    }

    public static void main(String[] args) {
        long a1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            alloc();
        }
        long a2 = System.currentTimeMillis();
        System.out.println("cost" + (a2 - a1) + "ms");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void alloc() {

    }

    public static StringBuffer createStringBuffer(String a, String b) {
        StringBuffer sb = new StringBuffer();
        sb.append(a);
        sb.append(b);
        return sb;
    }

    public static StringBuffer createStringBuffer(StringBuilder a, StringBuilder b) {
        StringBuffer sb = new StringBuffer();
        sb.append(a);
        sb.append(b);
        return sb;
    }

//    public static String createStringBuffer(String a, String b) {
//        StringBuffer sb = new StringBuffer();
//        sb.append(a);
//        sb.append(b);
//        return sb.toString();
//    }

    public static class User{

    }

}
