package com.example.demo.enums;
/**
*@Description 字段类型枚举
*@Author glliu
*@Date 2020/12/11
*/

public enum ColumnTypeEnum {
    VARCHAR(1, "String"),
    INTEGER(2, "Integer"),
    BIGDECIMAL(3, "Decimal"),
    ;
    private Integer typeCode;
    private String typeDesc;

    ColumnTypeEnum(Integer typeCode, String typeDesc) {
        this.typeCode = typeCode;
        this.typeDesc = typeDesc;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public static Integer getCode(String typeDesc) {
        ColumnTypeEnum[] values = ColumnTypeEnum.values();
        for (ColumnTypeEnum value : values) {
            if (value.typeDesc.equals(typeDesc)) {
                return value.typeCode;
            }
        }
        return 99;
    }

    public static String getDesc(Integer code){
        ColumnTypeEnum[] values = ColumnTypeEnum.values();
        for (ColumnTypeEnum value : values) {
            if (value.typeCode.equals(code)) {
                return value.typeDesc;
            }
        }
        return "";
    }
}
