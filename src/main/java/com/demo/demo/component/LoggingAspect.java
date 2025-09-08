package com.demo.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.demo.demo.serviceImpl.mongo..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName=joinPoint.getSignature().getName();
        Object[] objects=joinPoint.getArgs();
        long start = System.currentTimeMillis();
        // run the actual method
        Object result = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - start;
        log.info(joinPoint.getSignature() + " executed in " + timeTaken + "ms");
        log.info("➡️ Entering " + methodName + " with args: " + Arrays.toString(objects));
        return result;
    }
}
