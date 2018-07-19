package com.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.util.Arrays;

/**
 * 全部增强类测试
 */
@Component
@Aspect
public class AllInfoLogger {
    private static final Logger log = Logger.getLogger(AfterErrorLogger.class);

//    @Pointcut("execution(public void addInfo(..))")
    @Pointcut("execution(public String addDogInfoOf(com.entity.Dog))")
    public void pointcut() {
    }

    /**
     * 异常抛出增强
     */
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint jp, RuntimeException e) {
        log.error(jp.getSignature().getName() + "方法发生异常啦。异常信息是OPOPOP:" + e);
    }

    /**
     * 声明前置增强方法
     */
    @Before("pointcut()")
    public void before(JoinPoint jp) {
        log.info(jp.getTarget() + "什么类的。前置增强方法:" +
                jp.getSignature().getName() + "方法的参数是:" + Arrays.toString(jp.getArgs()));
    }

    /**
     * 声明后置增强方法
     */
    @AfterReturning(pointcut = "pointcut()", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        log.info(jp.getTarget().getClass().getName() + "什么类的。后置增强方法:" + jp.getSignature().getName() +
                "方法的返回值是：" + result);
    }

    /**
     * 最终增强
     */
    @After("pointcut()")
    public void after(JoinPoint jp) {
        log.info(jp.getSignature().getName() + "最终增强方法结束啦！");
    }

}
