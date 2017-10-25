package com.paytm;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.paytm.pojo.Event;
import com.paytm.pojo.EventType;
import com.paytm.pojo.Outcome;

import groovy.util.logging.Slf4j;

@Aspect
@Component
@Slf4j
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
        	Event event = resolveEventAndOutCome(
    			new Event(SecurityContextHolder.getContext().getAuthentication().getName()), 
    			request.getRequestURI(), 
    			request.getParameter("action")
    		);
        	log(event);
        	System.out.println("\n\n");
        }
        return value;
    }
    
    private void log(Event event) {
    	System.out.println(event);
    }
    
    private Event resolveEventAndOutCome(Event event, String uri, String action) {
    	EventType resolvedEvent = null;
    	Outcome resolvedOutcome = null;
    	if(uri != null) {
    		
    		if(uri.startsWith("/uaa/login")) {
    			resolvedEvent = EventType.LOGIN;
    			if (action != null) {
					if (action.equals("error"))
						resolvedOutcome = Outcome.INVALID_LOGIN_CREDENTIALS;
					else if (action.equals("registration_success"))
						resolvedOutcome = Outcome.REGISTRATION_SUCCESSFUL;
				}
    		}
    		
    		else if(uri.startsWith("/uaa/create-account"))
    			resolvedEvent = EventType.ATTEMPT_REGISTRATION;
    		
    		else if(uri.startsWith("/uaa/register")) {
    			resolvedEvent = EventType.REGISTRATION_PAGE;
    			if (action != null) {
					if (action.equals("error"))
						resolvedOutcome = Outcome.USER_ALREADY_EXISTS;
				}
    		}
    			
    		else if(uri.startsWith("/uaa/change-password")) {
    			resolvedEvent = EventType.CHANGE_PASSWORD_PAGE;
    			if (action != null) {
					if (action.equals("new_password_mismatch"))
						resolvedOutcome = Outcome.NEW_PASSWORD_AND_CONFIRM_PASSWORD_MISMATCH;
					else if (action.equals("success"))
						resolvedOutcome = Outcome.PASSWORD_CHANGE_SUCCESSFUL;
					else if (action.equals("error"))
						resolvedOutcome = Outcome.INVALID_PASSWORD;
				}
    		}
    			
    		else if(uri.startsWith("/uaa/change_password"))
    			resolvedEvent = EventType.ATTEMPT_CHANGING_PASSWORD;
    		
    		else if(uri.equals("/uaa/"))
    			resolvedEvent = EventType.AUTH_HOMEPAGE;
    	}
    	event.setEventType(resolvedEvent);
    	event.setOutcome(resolvedOutcome);
    	return event;
    }
}