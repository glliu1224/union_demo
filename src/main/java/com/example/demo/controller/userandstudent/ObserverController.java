package com.example.demo.controller.userandstudent;

import com.example.demo.service.observer.IDEAService;
import com.example.demo.service.observer.ObserverService;
import com.example.demo.service.observer.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
