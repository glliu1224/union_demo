package com.example.demo.controller;

import com.example.demo.service.observer.ObserverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/observer")
public class ObserverController {

    @Autowired
    private ObserverService observerService;

    @GetMapping("/getClass")
    public Boolean getThereClass() throws IllegalAccessException, InstantiationException {
        return observerService.submitAudit();
    }
}
