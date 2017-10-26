package com.paytm;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.paytm.pojo.Event;

@Aspect
@Component
public class RequestLogAspect {
	
	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) && execution(public * *(..))")
    public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();

        Object value;

        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
        	String user = null;
        	if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null)
        		user = SecurityContextHolder.getContext().getAuthentication().getName();
        	log(new Event(user, request.getRequestURI(), request.getParameter("action")));
        }
        return value;
    }
    
    private void log(Event event) {
    	String encodedAuth = Base64.getEncoder().encodeToString("user:logger".getBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " + encodedAuth);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		new RestTemplate().exchange(
			"http://localhost:7777/event/", 
			HttpMethod.POST, 
			new HttpEntity<Object>(event, httpHeaders), 
			Event.class
	    );
    }
}