package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public User getUser(@RequestParam("id") Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/insertUser")
    public String insertUser(@RequestBody UserVO userVO) {
        userService.insertUser(userVO);
        return "成功";
    }

    @PostMapping("/insertUserBatch")
    public String insertUserBatch() {
        userService.insertUserBatch();
        return "成功";
    }

    @GetMapping("/getUserEnd")
    public List<User> getUserEnd(@RequestParam("keyWord") String keyWord) {
        return userService.findUserByKeyWord(keyWord);
    }

}
