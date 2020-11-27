package com.example.demo.service.impl;

import com.example.demo.entity.BackLog;
import com.example.demo.service.BackLogService;
import com.example.demo.service.StudentService;
import java.util.LinkedList;
import java.util.List;

public class BackLogServiceService implements BackLogService {

    List<StudentService> list = new LinkedList<>();

    public void add(List<StudentService> studentServices) {
        for (StudentService studentService : studentServices) {
            list.add(studentService);
        }
    }
    @Override
    public void generator() {
        BackLog backLog = new BackLog();
    }
}
