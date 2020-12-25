package com.example.demo.filter;

import com.example.demo.entity.localtest.UserDO;
import com.example.demo.handle.AgentThreadLocal;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
/**
*@Description
 * 本地同一个包下SessionFilter代码中的销毁session是个坑，
 * 经测试如果session的key相同value也相同这个时候重新给session赋值是不会触发session监听器的创建和替换方法的，下面看下session监听
*@Author glliu
*@Date 2020/12/25
*/

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    //创建session时触发
    public void attributeAdded(HttpSessionBindingEvent event) {
        if ("userInfo".equals(event.getName())) {
            AgentThreadLocal.set((UserDO) event.getValue());
        }
    }

    @Override
    //销毁session时触发
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if ("userInfo".equals(event.getName())) {
            AgentThreadLocal.remove();
        }
    }

    @Override
    //替换session时触发
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if ("userInfo".equals(event.getName())) {
            AgentThreadLocal.set((UserDO) event.getValue());
        }
    }
}
