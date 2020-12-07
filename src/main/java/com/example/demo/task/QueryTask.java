package com.example.demo.task;

import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.UserVO;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class QueryTask extends RecursiveTask<List<UserVO>> {
    private int start;
    private int end;
    private UserMapper userMapper;

    public QueryTask(int start, int end, UserMapper userMapper) {
        this.start = start;
        this.end = end;
        this.userMapper = userMapper;
    }

    @Override
    protected List<UserVO> compute() {
        if (end - start <= 10000) {
            List<UserVO> list = userMapper.findUserByLength(start, end);
            return list;
        } else {
            int middle = (start + end) / 2;
            QueryTask task1 = new QueryTask(start, middle, userMapper);
            QueryTask task2 = new QueryTask(middle, end, userMapper);
            ForkJoinTask<List<UserVO>> fork = task1.fork();
            ForkJoinTask<List<UserVO>> fork1 = task2.fork();
            List<UserVO> join = fork.join();
            List<UserVO> join1 = fork1.join();
            join.addAll(join1);
            return join;
        }
    }
}
