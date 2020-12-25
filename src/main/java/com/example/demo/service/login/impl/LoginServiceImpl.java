package com.example.demo.service.login.impl;

import com.example.demo.entity.localtest.UserDO;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.mapper.UserInsertMapper;
import com.example.demo.service.login.LoginService;
import com.example.demo.utils.Md5Util;
import com.example.demo.vo.LoginVO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserInsertMapper userInsertMapper;

    @Override
    public String authentication(LoginVO loginVO) {
        if (StringUtils.isBlank(loginVO.getUserName())) {
            throw new BusinessException("用户名不能为空");
        }
        if (StringUtils.isBlank(loginVO.getPassword())) {
            throw new BusinessException("密码不能为空");
        }
        UserDO userDO = userInsertMapper.queryUserByUserNameAndPassword(loginVO);
        if (ObjectUtils.isEmpty(userDO)) {
            throw new BusinessException("用户名或密码错误");
        }
        String token = Md5Util.sign(loginVO.getUserName(), loginVO.getPassword());
        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        return null;
    }
}
