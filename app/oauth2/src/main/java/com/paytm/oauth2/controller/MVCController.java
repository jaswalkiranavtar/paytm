package com.paytm.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MVCController {
	
	@RequestMapping("/login")
    public String login(@RequestParam(value="action", required=false) String action, Model model) {
		model.addAttribute("action", action);
        return "login";
    }
	
	@RequestMapping("/register")
    public String register(@RequestParam(value="action", required=false) String action, Model model) {
		model.addAttribute("action", action);
        return "register";
    }
	
	@RequestMapping("/change-password")
    public String changePassword(@RequestParam(value="action", required=false) String action, Model model) {
		model.addAttribute("action", action);
        return "change-password";
    }

}
