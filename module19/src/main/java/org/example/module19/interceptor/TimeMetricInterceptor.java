package org.example.module19.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.module19.model.dto.ErrorResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import java.time.Instant;

@Component
@Aspect
public class TimeMetricInterceptor {

    @Around("@annotation(org.example.module19.annotation.TimeMetric)")
    public Object processMetric(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = Instant.now().toEpochMilli();
        Object result = joinPoint.proceed();
        long resultedTime = Instant.now().toEpochMilli() - start;
        System.out.println("Method " + joinPoint.getSignature().getName() +
                           " executed in " + resultedTime + " ms");
        return result;
    }

    @AfterReturning(value = """
                        within(@org.springframework.web.bind.annotation.ControllerAdvice *)
                        && !args(jakarta.persistence.OptimisticLockException)""",
            returning = "errorResponse")
    public void countErrorResponses(ErrorResponse errorResponse) {
        HttpServletRequest currentRequest = getCurrentRequest();
        String path = currentRequest.getServletPath();
        if (is4xxErrorCode(errorResponse)) {
            System.out.println("Sending error event for 4xx response for path " + path);
        } else {
            System.out.println("Sending error event for 5xx response for path " + path);
        }
    }

    private static boolean is4xxErrorCode(ErrorResponse errorResponse) {
        return errorResponse.errorCode() >= 400 && errorResponse.errorCode() < 500;
    }

    private HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
    }
}
