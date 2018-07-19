package com.aop;

import com.entity.User;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 增强类（异常抛出增强）
 */
@Aspect
//@Component
public class AfterErrorLogger {
    private static final Logger log = Logger.getLogger(AfterErrorLogger.class);

    @Pointcut("execution(public void addInfo(..))")
    public void pointcut(){}

    /**
     * 异常抛出增强
     */
    @AfterThrowing(value = "pointcut()",throwing = "e")
    public void afterThrowing(JoinPoint jp, RuntimeException e) {
        log.error(jp.getSignature().getName() + "方法发生异常啦。异常信息是OPOPOP:" + e);
    }

}
