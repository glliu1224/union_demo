package com.example.annotations.table;

import lombok.Data;

@DBTable(name = "member")
@Data
public class Member {

    @SQLInteger(value = "id",constraint = @Constraints(primaryKey = true))
    private Integer id;

    @SQLString(name = "name",value = 30)
    private String name;

    @SQLInteger(value = "10")
    private int age;

    @SQLString(name = "description",value = 150,constraint = @Constraints(allowNull = true))
    private String description;
}
