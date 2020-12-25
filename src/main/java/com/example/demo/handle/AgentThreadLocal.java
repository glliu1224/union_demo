package com.example.demo.handle;

import com.example.demo.entity.localtest.UserDO;

/**
*@Description 开辟本地线程，用于保存用户信息
 * 由于每一次请求都是一个独立的线程，ThreadLocal中的变量需要我们通过session做一个中转的配置，
 * 每次请求都判断这个session中是否存在用户信息，如果session中存在用户信息就将用户信息保存到ThreadLocal
 * 中
*@Author glliu
*@Date 2020/12/25
*/

public class AgentThreadLocal {

    private AgentThreadLocal() {

    }

    private static final ThreadLocal<UserDO> LOCAL = new ThreadLocal<>();

    public static void set(UserDO userDO) {
        LOCAL.set(userDO);
    }

    public static UserDO get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

}
