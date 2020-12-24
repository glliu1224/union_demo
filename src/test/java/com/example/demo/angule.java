package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.Inet4Address;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
