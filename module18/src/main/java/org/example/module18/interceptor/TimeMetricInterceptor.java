package org.example.module18.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Aspect
public class TimeMetricInterceptor {

    @Around("@annotation(org.example.module18.annotation.TimeMetric)")
    public Object processMetric(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = Instant.now().toEpochMilli();
        Object result = joinPoint.proceed();
        long resultedTime = Instant.now().toEpochMilli() - start;
        System.out.println("Method " + joinPoint.getSignature().getName() +
                           " executed in " + resultedTime + " ms");
        return result;
    }
}
