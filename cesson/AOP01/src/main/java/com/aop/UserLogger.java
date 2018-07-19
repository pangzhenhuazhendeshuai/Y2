package com.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 增强类（前置增强，后置增强）
 */
@Aspect
//@Component
public class UserLogger {
    private static final Logger log = Logger.getLogger(UserLogger.class);

    @Pointcut("execution(public void addInfo(..))")
    public void pointcut() {
    }

    /**
     * 申明前置增强方法
     */
    @Before("pointcut()")
    public void before(JoinPoint jp) {
        log.info(jp.getTarget() + "什么类的。前置增强方法:" +
                jp.getSignature().getName() + "方法的参数是:" + Arrays.toString(jp.getArgs()));
    }

    /**
     * 申明后置增强方法
     */
    @AfterReturning(pointcut = "pointcut()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        log.info(jp.getTarget() + "什么类的。后置增强方法:" + jp.getSignature().getName() +
                "方法的返回值是：" + result);
    }
}
