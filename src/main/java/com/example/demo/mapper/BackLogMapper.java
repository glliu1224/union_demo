package com.example.demo.mapper;

import com.example.demo.entity.BackLog;

public interface BackLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BackLog record);

    int insertSelective(BackLog record);

    BackLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BackLog record);

    int updateByPrimaryKey(BackLog record);
}