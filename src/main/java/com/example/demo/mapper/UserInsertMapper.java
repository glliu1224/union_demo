package com.example.demo.mapper;

import com.example.demo.entity.localtest.UserDO;

import java.util.List;

public interface UserInsertMapper {

    void insertUserBatch(List<UserDO> list);

}
