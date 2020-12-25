package com.example.demo.controller.login;

import com.example.demo.exceptions.BusinessException;
import com.example.demo.service.login.LoginService;
import com.example.demo.vo.LoginVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
*@Description 用户登录Controller，如果用户没有登录，返回501状态码
*@Author glliu
*@Date 2020/12/25
*/

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/authentication")
    public String authentication(@RequestBody LoginVO loginVO) {
        if (ObjectUtils.isEmpty(loginVO)) {
            throw new BusinessException("请求参数不能为空");
        }
        return loginService.authentication(loginVO);
    }
}
