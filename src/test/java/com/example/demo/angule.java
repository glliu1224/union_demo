package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.Inet4Address;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class angule {

    @Test
    public void test() {
        Integer a = 2;
        Integer b = 2;
        log.info("第一个HashCode:{}",a.hashCode());
    }
}
