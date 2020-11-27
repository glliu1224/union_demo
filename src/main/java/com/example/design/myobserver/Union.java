package com.example.design.myobserver;

import java.util.LinkedList;
import java.util.List;

public class Union {
    List<OutSource> list = new LinkedList<>();

    public void add(OutSource outSources) {
        list.add(outSources);
    }

    public void happen(String peopleType) {
        for (OutSource outSource : list) {
            outSource.recruid(peopleType);
        }
    }
}
