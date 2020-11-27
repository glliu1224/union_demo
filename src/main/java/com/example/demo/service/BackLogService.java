package com.example.demo.service;

import com.example.demo.service.abstracts.Observer;

import java.util.List;

public interface BackLogService {

    void add(List<StudentService> studentServices);

    void generator();
}
