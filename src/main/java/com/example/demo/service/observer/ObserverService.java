package com.example.demo.service.observer;

public interface ObserverService {

    Boolean submitAudit() throws InstantiationException, IllegalAccessException;

    void add(ProjectService projectService);

}
