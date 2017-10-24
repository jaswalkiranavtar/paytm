package com.paytm.oauth2.services;

import com.paytm.oauth2.models.Account;
import com.paytm.oauth2.models.Role;
import com.paytm.oauth2.repositories.AccountRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import javax.persistence.EntityExistsException;

@Service
public class AccountService implements UserDetailsService {

    private final Logger logger = Logger.getLogger(AccountService.class);

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Account> account = accountRepo.findByUsername( s );
        if ( account.isPresent() ) {
            return account.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", s));
        }
    }

    public Account findAccountByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepo.findByUsername( username );
        if ( account.isPresent() ) {
            return account.get();
        } else {
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }
    }
    
    public boolean validatePassword(String username, String password) {
    	
    	Account account = accountRepo.findByUsername(username).get();
    	return passwordEncoder.matches(password, account.getPassword());
    }

    public Account updatePassword(String userName, String password) {
    	Account account = accountRepo.findByUsername(userName).get();
    	account.setPassword(passwordEncoder.encode(password));
    	return accountRepo.save(account);
    }
    
    public Account registerUser(Account account) throws EntityExistsException {
    	boolean userAlreadyExists = true;
    	try {
    		findAccountByUsername(account.getUsername());
    	} catch(UsernameNotFoundException unf) {
    		userAlreadyExists = false;
    	}
    	if(userAlreadyExists)
    		throw new EntityExistsException("Account with this user already exists");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.grantAuthority(Role.ROLE_USER);
        return accountRepo.save( account );
    }

    @Transactional // To successfully remove the date @Transactional annotation must be added
    public boolean removeAuthenticatedAccount() throws UsernameNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account acct = findAccountByUsername(username);
        int del = accountRepo.deleteAccountById(acct.getId());
        return del > 0;
    }
}
