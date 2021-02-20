package com.example.demo.service.observer.impl;

import com.example.demo.service.observer.IDEAService;
import org.springframework.stereotype.Service;

@Service(value = "")
public class IDEAService1 implements IDEAService {
    @Override
    public int key() {
        return 0;
    }

    @Override
    public void doSomeThing() {

    }
}
