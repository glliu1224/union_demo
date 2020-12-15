package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
*@Description AOP切面类，交给spring管理
*@Author glliu
*@Date 2020/12/15
*/

@Component
@Aspect//声明这个类是一个切面类
public class HttpAspect {

    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    //配置切入点
    @Pointcut("execution(public * com.example.demo.controller.UserController.*(..))")
    public void log() {

    }

    //前置通知，在目标方法(切入点)执行之前执行
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //url
        logger.info("url={}", request.getRequestURL());
        //method
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("ip={}", request.getLocalAddr());
        //类方法
        logger.info("class={}", joinPoint.getSignature().getDeclaringType() + "." + joinPoint.getSignature().getName());
        //方法参数
        logger.info("args={}",joinPoint.getArgs());
    }

    //后置通知，在目标方法(切入点)执行之后执行
    @After("log()")
    public void doAfter() {
        logger.info("doAfter={}", "end");
    }

    //环绕通知,得到response返回的数据，returning代表切入点方法返回的数据
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}",object.toString());
    }
}
