package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/observer")
public class ObserverController {

    @Autowired
    private ObserverService observerService;

    @PostMapping("/insert")
    public void insert() {
        observerService.insertStudentAndUser();
    }
}
