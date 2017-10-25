package com.paytm.oauth2.repository;


import org.springframework.data.repository.Repository;

import com.paytm.oauth2.pojo.Account;

import java.util.Collection;
import java.util.Optional;

public interface AccountRepo extends Repository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByUsernameAndPassword(String username, String password);
    Account save(Account account);
    int deleteAccountById(Long id);
}
