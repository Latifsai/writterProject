package com.example.writterproject.configurations;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
@Slf4j
public class LoggingConfiguration {

    @Pointcut("execution(public * com.example.writterproject.controllers.*.*(..))")
    public void controllersLog() {
    }

    @Before("controllersLog()")
    public void doBeforeController(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest httpServletRequest = attributes.getRequest();
        log.info("""
                RECEIVED REQUEST:
                IP: {},
                URL: {},
                HTTP_METHOD: {}
                """,
                httpServletRequest.getRemoteAddr(),
                httpServletRequest.getRequestURL().toString(),
                httpServletRequest.getMethod()
                );
    }

    @AfterThrowing(pointcut = "controllersLog()", throwing = "exception")
    public void doAfterException(JoinPoint joinPoint, Exception exception){
        log.error("""
                Exception handle,
                Cause: {},{}
                """,
                joinPoint.getSignature().getName(),
                exception.getMessage());
    }
}
