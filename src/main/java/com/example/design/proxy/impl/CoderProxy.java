package com.example.design.proxy.impl;

import com.example.design.proxy.ICoder;

public class CoderProxy implements ICoder {

    private ICoder coder;

    public CoderProxy(ICoder coder){
        this.coder = coder;
    }

    @Override
    public void implDemands(String demandName) {
        if(demandName.startsWith("Add")){
            System.out.println("No longer receive 'Add' demand");
            return;
        }
        coder.implDemands(demandName);
    }
}
