package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.service.ThreadService;
import com.example.demo.task.QueryTask;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

@Service
public class ThreadServiceImpl implements ThreadService {

    @Autowired
    private UserMapper userMapper;

    ForkJoinPool forkJoinPool = new ForkJoinPool();

    BlockingQueue queue = new ArrayBlockingQueue(5);

    Executor executor = new ThreadPoolExecutor(4,10,1, TimeUnit.MINUTES,queue);
    @Override
    public List<UserVO> getUser() throws ExecutionException, InterruptedException {
        int x = userMapper.findCount();
        QueryTask queryTask = new QueryTask(0,x,userMapper);
        ForkJoinTask<List<UserVO>> submit = forkJoinPool.submit(queryTask);
        List<UserVO> userVOS = submit.get();
        return userVOS;
    }
}
