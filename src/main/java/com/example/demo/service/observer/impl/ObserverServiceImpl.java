package com.example.demo.service.observer.impl;

import com.example.demo.service.observer.ObserverService;
import com.example.demo.service.observer.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * 被观察者
 */
@Service
@Slf4j
public class ObserverServiceImpl<T> implements ObserverService {

    List<ProjectService> list = new LinkedList<>();

    /**
     * 提交审核
     */
    @Override
    @Transactional
    public Boolean submitAudit() throws InstantiationException, IllegalAccessException {
        long l = System.currentTimeMillis();
        for (ProjectService projectService : list) {
            /*执行观察者方法*/
            Class clazz = projectService.getEntityClass();
            T entity = (T)projectService.getEntity(clazz);
            log.info("获取到的实体类为:{}",entity);
            projectService.insert(entity);
        }
        /*此处可执行相关业务逻辑代码*/
        long l1 = System.currentTimeMillis();
        log.info("业务方法执行耗时:{}毫秒",l1 - l);
        return true;
    }

    @Override
    public void add(ProjectService projectService) {
        list.add(projectService);
    }
}
