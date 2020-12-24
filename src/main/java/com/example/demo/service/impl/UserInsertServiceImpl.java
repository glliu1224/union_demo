package com.example.demo.service.impl;

import com.example.demo.entity.localtest.UserDO;
import com.example.demo.mapper.UserInsertMapper;
import com.example.demo.service.UserInsertService;
import com.example.demo.utils.GeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class UserInsertServiceImpl implements UserInsertService {

    @Autowired
    private UserInsertMapper userInsertMapper;

    @Override
    public void insertBatch() {
        List<UserDO> list = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            UserDO userDO = new UserDO();
            String name = GeneratorUtil.getName();
            String password = UUID.randomUUID().toString();
            userDO.setUserName(name);
            userDO.setPassword(password);
            list.add(userDO);
        }
        userInsertMapper.insertUserBatch(list);
    }
}
