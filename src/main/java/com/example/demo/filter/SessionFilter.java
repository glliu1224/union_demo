//package com.example.demo.filter;
//
//import com.example.demo.entity.localtest.UserDO;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//public class SessionFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest)req;
//        UserDO userDO = (UserDO) request.getSession().getAttribute("userInfo");
//        if (userDO != null) {
//            //先销毁在添加否则触发不了监听器中的attributeAdded
//            request.getSession().removeAttribute("userInfo");
//            //重新设值session
//            request.getSession().setAttribute("userInfo", userDO);
//        }
//        chain.doFilter(request, response);
//    }
//}
