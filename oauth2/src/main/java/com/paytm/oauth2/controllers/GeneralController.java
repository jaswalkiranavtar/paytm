package com.paytm.oauth2.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class GeneralController {

    @RequestMapping("/")
    public RestMsg hello(){
        return new RestMsg("Hello World!");
    }

    @RequestMapping("/api/test")
    public RestMsg apitest(){
        return new RestMsg("Hello apiTest!");
    }
    
    @RequestMapping("/api/me")
	public Principal user(Principal principal) {
		return principal;	
	}

    @RequestMapping(value = "/api/hello", produces = "application/json")
    public RestMsg helloUser(){
        // The authenticated user can be fetched using the SecurityContextHolder
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new RestMsg(String.format("Hello '%s'!", username));
    }

    @RequestMapping("/api/admin")
    // If a controller request asks for the Principal user in
    // the method declaration Spring security will provide it.
    public RestMsg helloAdmin(Principal principal){
        return new RestMsg(String.format("Welcome '%s'!", principal.getName()));
    }

    // A helper class to make our controller output look nice
    public static class RestMsg {
        private String msg;
        public RestMsg(String msg) {
            this.msg = msg;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
