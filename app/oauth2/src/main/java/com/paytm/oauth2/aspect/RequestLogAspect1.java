package com.paytm.oauth2.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import groovy.util.logging.Slf4j;

//@Aspect
//@Component
//@Slf4j
public class RequestLogAspect1 {
//    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) && execution(public * *(..))")
//    public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//                .currentRequestAttributes())
//                .getRequest();
//
//        Object value;
//
//        try {
//            value = proceedingJoinPoint.proceed();
//        } catch (Throwable throwable) {
//            throw throwable;
//        } finally {
//            System.out.format(
//                    "{} {} from {}",
//                    request.getMethod(),
//                    request.getRequestURI(),
//                    request.getRemoteAddr());
//        }
//
//        return value;
//    }
}