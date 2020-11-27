package com.example.demo.service.observer.impl;

import com.example.demo.entity.BackLog;
import com.example.demo.mapper.BackLogMapper;
import com.example.demo.service.observer.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("BackLogInsertServiceImpl")
public class BackLogInsertServiceImpl implements ProjectService<BackLog>{

    @Autowired
    private BackLogMapper backLogMapper;

    @Override
    public void insert(BackLog backLog) {
        backLogMapper.insert(backLog);
    }

    @Override
    public Class<?> getEntityClass() {
        return BackLog.class;
    }

    @Override
    public BackLog getEntity(Class clazz) throws IllegalAccessException, InstantiationException {
        BackLog backLog = (BackLog) clazz.newInstance();
        backLog.setConId(1111);
        backLog.setCreateTime(new Date());
        backLog.setDescription("没什么描述");
        backLog.setProId(2222);
        return backLog;
    }
}
