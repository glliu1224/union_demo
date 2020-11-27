package com.example.demo.service.observer;

public interface ProjectService<T>{

    public void insert(T t);

    Class<?> getEntityClass();

    T getEntity(Class clazz) throws IllegalAccessException, InstantiationException;
}
