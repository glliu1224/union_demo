package com.example.demo.service.observer.impl;

import com.example.demo.service.observer.ObserverService;
import com.example.demo.service.observer.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 被观察者
 */
@Service
@Slf4j
public class ObserverServiceImpl<T> implements ObserverService {

    List<ProjectService> list = new LinkedList<>();

    public ObserverServiceImpl(List<ProjectService> projectServices) {
        for (ProjectService projectService : projectServices) {
            list.add(projectService);
        }
    }

    /**
     * 提交审核
     */
    @Override
    public Boolean submitAudit() throws InstantiationException, IllegalAccessException {
        for (ProjectService projectService : list) {
            /*执行观察者方法*/
            Class clazz = projectService.getEntityClass();
            T entity = (T)projectService.getEntity(clazz);
            projectService.insert(entity);
        }
        return true;
    }
}
