package com.example.demo.enums;

public enum CodeEnum {
    OK(200,"成功"),
    VALUE_NULL(300,"值为空"),
    SYS_ERROR(500,"系统异常"),
    NULL_LOGIN_INFORMATION(501,"未查询到登录信息")
    ;

    private Integer code;
    private String messgae;

    CodeEnum() {
    }

    CodeEnum(Integer code, String messgae) {
        this.code = code;
        this.messgae = messgae;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessgae() {
        return messgae;
    }
}
