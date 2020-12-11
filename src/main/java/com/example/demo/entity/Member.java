package com.example.demo.entity;

import com.example.demo.annotations.DColumn;
import com.example.demo.annotations.DTable;
import com.example.demo.constant.ColumnTypeConstant;

@DTable(value = "member")
public class Member {

    //主键ID
    @DColumn(name = "id",columnType = ColumnTypeConstant.INTEGER,length = 11,comment = "主键ID")
    private Integer id;

    //姓名
    @DColumn(name = "name",columnType = ColumnTypeConstant.VARCHAR,length = 20,comment = "姓名")
    private String name;

    //会员编号
    @DColumn(name = "mem_no",columnType = ColumnTypeConstant.VARCHAR,length = 20,comment = "会员编号")
    private String memNo;
}
