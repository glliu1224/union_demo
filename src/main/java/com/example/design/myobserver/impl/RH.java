package com.example.design.myobserver.impl;

import com.example.design.myobserver.OutSource;
import com.example.design.myobserver.Union;

public class RH extends OutSource{

    public RH(Union union) {
        union.add(this);
    }

    @Override
    public <String> void recruid(String t) {
        System.out.println("润和开始招聘" + t);
    }
}
