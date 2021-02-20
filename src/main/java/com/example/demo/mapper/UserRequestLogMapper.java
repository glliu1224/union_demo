package com.example.demo.mapper;

import com.example.demo.entity.UserRequestLog;

public interface UserRequestLogMapper {

    int insert(UserRequestLog record);

    int updateByPrimaryKey(UserRequestLog record);
}