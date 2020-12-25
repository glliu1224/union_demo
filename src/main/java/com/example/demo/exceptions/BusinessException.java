package com.example.demo.exceptions;

import com.example.demo.enums.CodeEnum;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(CodeEnum codeEnum) {
        super(codeEnum.getMessgae());
        this.code = codeEnum.getCode();
    }
}
