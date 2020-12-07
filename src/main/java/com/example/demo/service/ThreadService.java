package com.example.demo.service;

import com.example.demo.vo.UserVO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ThreadService {

    List<UserVO> getUser() throws ExecutionException, InterruptedException;

}
