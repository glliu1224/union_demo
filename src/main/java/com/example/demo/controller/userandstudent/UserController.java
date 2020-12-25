package com.example.demo.controller.userandstudent;

import com.example.demo.entity.GCEntity;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据ID获取用户，查询user表
     * @param id
     * @return
     */
    @GetMapping("/getUser")
    public User getUser(@RequestParam("id") Integer id) {
        return userService.getUser(id);
    }

    /**
     * 插入数据--单条  user表
     * @param userVO
     * @return
     */
    @PostMapping("/insertUser")
    public String insertUser(@RequestBody UserVO userVO) {
        userService.insertUser(userVO);
        return "成功";
    }

    /**
     * 批量插入数据  user表
     * @return
     */
    @PostMapping("/insertUserBatch")
    public String insertUserBatch() {
        userService.insertUserBatch();
        return "成功";
    }

    /**
     * 模糊查询email以***结尾的用用户
     * @param keyWord
     * @return
     */
    @GetMapping("/getUserEnd")
    public List<User> getUserEnd(@RequestParam("keyWord") String keyWord) {
        return userService.findUserByKeyWord(keyWord);
    }

    @GetMapping("/exception")
    public void exceptionTest() {
        int i = 1 / 0;
        System.out.println("上面有异常");
    }

    /**
     * 切面测试
     * @return
     */
    @GetMapping("firstAop")
    public String firstAop() {
        this.secondAop();
        return "first";
    }

    /**
     * 切面测试
     * @return
     */
    @GetMapping("secondAop")
    public String secondAop() {
        return "second";
    }

    @GetMapping("/gcTest")
    public String gcTest() {
        List<GCEntity> list = new LinkedList<>();
        while (true) {
            GCEntity gc = new GCEntity();
            list.add(new GCEntity());
        }
    }

    @GetMapping("/testYml")
    public String testYml() {
        return userService.testYml();
    }

}
