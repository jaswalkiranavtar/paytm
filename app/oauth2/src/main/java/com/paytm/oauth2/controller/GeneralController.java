package com.paytm.oauth2.controller;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class GeneralController {

    @RequestMapping("/")
    public RestMsg hello(){
        return new RestMsg("Hello World!");
    }

    @RequestMapping("/api/me")
	public Principal user(Principal principal) {
		return principal;	
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
