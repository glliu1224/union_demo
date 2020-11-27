package com.example.demo.handle;

import com.example.demo.enums.CodeEnum;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result jsonErrorHandler(Exception e) {
        Result res = new Result();

        if (e instanceof BusinessException) {
            res.setCode(((BusinessException) e).getCode());
            res.setMessage(e.getMessage());
        } else {
            res.setCode(CodeEnum.SYS_ERROR.getCode());
            res.setMessage(CodeEnum.SYS_ERROR.getMessgae());
        }

        return res;
    }
}
