package com.example.demo.controller;

import com.example.demo.service.StudentDOService;
import com.example.demo.service.UserInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInsert")
public class UserInsertController {

    @Autowired
    private UserInsertService userInsertService;

    @Autowired
    private StudentDOService studentDOService;

    @PostMapping("/insert")
    public String batchInsert() {
        userInsertService.insertBatch();
        return "成功！！！";
    }

    @PostMapping("/studentDO")
    public String batchStudent(@RequestParam("stuPrefix")String stuPrefix) {
        studentDOService.insertStudentBatch(stuPrefix);
        return "成功！！！";
    }
}
