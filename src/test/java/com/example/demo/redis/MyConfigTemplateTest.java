package com.example.demo.redis;

import com.example.demo.entity.User;
import com.example.demo.utils.GeneratorUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyConfigTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        redisTemplate.opsForValue().set("wujinxing", "lige");
        System.out.println(redisTemplate.opsForValue().get("wujinxing"));
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserName(GeneratorUtil.getName());
            user.setPhone(GeneratorUtil.getTel());
            map.put(String.valueOf(i), user);
        }
        redisTemplate.opsForHash().putAll("测试", map);
        BoundHashOperations hashOps = redisTemplate.boundHashOps("测试");
        Map map1 = hashOps.entries();
        System.out.println(map1);
    }
}
