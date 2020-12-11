package com.example.demo.handle;

import com.example.demo.enums.CodeEnum;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
*@Description 全局异常处理类
*@Author glliu
*@Date 2020/12/11
*/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result jsonErrorHandler(Exception e) {
        Result res = new Result();

        if (e instanceof BusinessException) {
            res.setCode(((BusinessException) e).getCode());
            res.setMessage(e.getMessage());
        } else {
            e.printStackTrace();
            res.setCode(CodeEnum.SYS_ERROR.getCode());
            res.setMessage(CodeEnum.SYS_ERROR.getMessgae());
        }

        return res;
    }
}
