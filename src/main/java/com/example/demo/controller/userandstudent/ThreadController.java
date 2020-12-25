package com.example.demo.controller.userandstudent;

import com.example.demo.exceptions.BusinessException;
import com.example.demo.service.ThreadService;
import com.example.demo.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/thread")
@Slf4j
public class ThreadController {

    @Autowired
    private ThreadService threadService;

    @GetMapping("/getUserByEmail")
    public List<UserVO> getUser() {
        long l = System.currentTimeMillis();
        log.info("线程池任务执行开始时间:{}",l);
        List<UserVO> user = null;
        try {
            user = threadService.getUser();
        } catch (ExecutionException e) {
            log.error("查询出现问题");
            throw new BusinessException("查询用户出现问题");
        } catch (InterruptedException e) {
            log.error("查询出现问题");
            throw new BusinessException("查询用户出现问题");
        }
        Long time = System.currentTimeMillis() - l;
        log.info("线程池任务执行耗时:{}",time);
        return user;
    }
}
