package com.example.springbootvuetodo.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.google.common.base.Joiner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(entry -> String.format("%s -> (%s)",
                        entry.getKey(), Joiner.on(",").join(entry.getValue())))
                .collect(Collectors.joining(", "));
    }
    @Pointcut("within(com.example.springbootvuetodo.controller..*)")
    public void onRequest() {}

    @After("execution(* com.example.springbootvuetodo.controller.*.*(..))")
    public Object doLogging(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = // 5
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Map<String, String[]> paramMap = request.getParameterMap();
        String params = "";

        if (paramMap.isEmpty() == false) {
            params = " [" + paramMapToString(paramMap) + "]";
        }

        long start = System.currentTimeMillis();
        try {
            return joinPoint.getArgs();
        } finally {
            long end = System.currentTimeMillis();
            log.info("Request: {} {}{} < {} ({}ms)", request.getMethod(), request.getRequestURI(),
                    params, request.getRemoteHost(), end - start);
        }
    }

    @AfterThrowing(pointcut = "execution(* com.example.springbootvuetodo.controller.*.*(..))", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex) throws Throwable
    {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.error("Exception Occured at {} with {}", request.getRequestURI(), ex);
    }
}
