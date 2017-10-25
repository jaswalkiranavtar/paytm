package com.paytm.oauth2.controllers;

import com.paytm.oauth2.models.Account;
import com.paytm.oauth2.models.ChangePassword;
import com.paytm.oauth2.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @PreAuthorize("hasRole('TRUSTED_CLIENT')")
    @RequestMapping(method = {RequestMethod.POST}, path = {"/create-account"}, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    public ResponseEntity<Object> registerAccount(Account account, @RequestParam(value = "redirect_uri") String redirectUri, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	try {
    		account = accountService.registerUser(account);
    	} catch (EntityExistsException eee) {
    		response.sendRedirect("register?action=error&redirect_uri=login");
    		return null;
    	}
        response.sendRedirect(redirectUri + "?action=registration_success");
        return null;
    }

    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping("/delete-account")
    public ResponseEntity<GeneralController.RestMsg> removeAccount(Principal principal){
        boolean isDeleted = accountService.removeAuthenticatedAccount();
        if ( isDeleted ) {
            return new ResponseEntity<>(
                    new GeneralController.RestMsg(String.format("[%s] removed.", principal.getName())),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<GeneralController.RestMsg>(
                    new GeneralController.RestMsg(String.format("An error ocurred while delete [%s]", principal.getName())),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
    
    @PreAuthorize("isFullyAuthenticated()")
    @RequestMapping(method = {RequestMethod.POST}, path = {"/change_password"}, consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    public ResponseEntity<GeneralController.RestMsg> changePassword(ChangePassword changePassword, Principal principal, HttpServletResponse response) throws IOException{
    	
    	if(accountService.validatePassword(principal.getName(), changePassword.getOldPassword())) {
    		accountService.updatePassword(principal.getName(), changePassword.getNewPassword());
    		response.sendRedirect("change-password?action=success");
    	} else {
    		response.sendRedirect("change-password?action=error");
    	}
    	return null;
    }

}
