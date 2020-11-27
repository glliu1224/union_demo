package com.example.demo.controller;

import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/insertBatch")
    public String insertBatch() {
        studentService.insertBatch();
        return "成功";
    }

    /**
     * 调用观察者模式
     */
    @PostMapping("/observer")
    public void observer() {

    }
}
