package com.example.design.myobserver.impl;

import com.example.design.myobserver.OutSource;
import com.example.design.myobserver.Union;

public class XZ extends OutSource {
    public XZ(Union union) {
        union.add(this);
    }
    @Override
    public <String> void recruid(String prople) {
        System.out.println("新致开始招聘" + prople);

    }
}
