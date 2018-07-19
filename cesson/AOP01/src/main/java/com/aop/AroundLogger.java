package com.aop;

import java.util.Arrays;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 增强类（环绕增强）
 * 
 * @author lenovo
 *
 */
//@Aspect
//@Component
public class AroundLogger {
	private static final Logger log = Logger.getLogger(AroundLogger.class);

//	@Pointcut("execution(public void addInfo(..))")
	public void pointcut(){}

//	@Around("pointcut()")
	public Object aroundLogger(ProceedingJoinPoint jp) {
		log.info("(前)调用：" + jp.getTarget() + "的：" + jp.getSignature().getName() + "方法。的返回值是："
				+ Arrays.toString(jp.getArgs()));
		try {
			Object result = jp.proceed();
			log.info("(后)调用 :" + jp.getTarget() + " 的: " + jp.getSignature().getName() + " 方法。方法返回值:" + result);
			return result;
		} catch (Throwable e) {
			log.error(jp.getSignature().getName() + "方法发生异常啦:" + e);
		} finally {
			log.info(jp.getSignature().getName() + "最终增强方法结束啦!");
		}
		return null;
	}

}
