package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.service.abstracts.Observer;
import com.example.demo.utils.GeneratorUtil;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class UserServiceImpl extends Observer<UserVO> implements UserService {

    BlockingQueue queue = new ArrayBlockingQueue(5);
    Executor executor = new ThreadPoolExecutor(4,4, 5,TimeUnit.MILLISECONDS,queue);

    @Value("${value}")
    private String value;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public void insertUser(UserVO userVO) {
        userMapper.insertUser(userVO);
    }

    @Override
    public void insertUserBatch() {
        for (int i = 0; i < 4; i++) {
            List<UserVO> userVOS = new LinkedList<>();
            for (int i1 = 0; i1 < 10000; i1++) {
                UserVO userVO = new UserVO();
                userVO.setUserName(GeneratorUtil.getName());
                userVO.setAddress(GeneratorUtil.getRoad());
                userVO.setEmail(GeneratorUtil.getEmail(10,20));
                userVO.setPhone(GeneratorUtil.getTel());
                userVO.setSex(GeneratorUtil.getSex());
                userVOS.add(userVO);
            }
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    userMapper.insertUserBatch(userVOS);
                }
            });
        }
    }

    @Override
    public List<User> findUserByKeyWord(String keyWord) {
        List<User> users = userMapper.findUserByKeyWord(keyWord);
        if (CollectionUtils.isEmpty(users)) {
            return Collections.emptyList();
        }
        return users;
    }

    @Override
    public String testYml() {
        return value;
    }

    @Override
    @Transactional
    public void transactional(Integer first,Integer second,String firstName,String secondName) {
        userMapper.updateById(first);
        this.updateAddressByUserName(firstName,secondName);
        int i = 1 / 0;
        userMapper.updateById(second);
    }

    @Override
    @Transactional
    public void updateAddressByUserName(String first,String second) {
        userMapper.updateByUserName(first);
        userMapper.updateByUserName(second);
    }


    @Override
    public void insert(UserVO user) {
        userMapper.insertUser(user);
    }
}
