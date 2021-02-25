package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.vo.UserVO;

import java.util.List;

public interface UserService {
    User getUser(Integer id);

    void insertUser(UserVO userVO);

    void insertUserBatch();

    List<User> findUserByKeyWord(String keyWord);

    String testYml();

    void transactional(Integer first,Integer second,String firstName,String secondName);

    void updateAddressByUserName(String first,String second);
}
