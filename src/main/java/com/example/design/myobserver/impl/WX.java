package com.example.design.myobserver.impl;

import com.example.design.myobserver.OutSource;
import com.example.design.myobserver.Union;

public class WX extends OutSource {

    public WX(Union union) {
        union.add(this);
    }

    @Override
    public <String> void recruid(String prople) {
        System.out.println("文思海辉开始招聘" + prople);
    }
}
