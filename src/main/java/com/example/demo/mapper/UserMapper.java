package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User findUserById(@Param("id") Integer id);


    void insertUser(UserVO userVO);

    void insertUserBatch(@Param("list") List<UserVO> list);

    List<User> findUserByKeyWord(@Param("keyWord") String keyWord);
}
