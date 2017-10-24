package com.paytm.oauth2.repositories;


import com.paytm.oauth2.models.Account;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public interface AccountRepo extends Repository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByUsernameAndPassword(String username, String password);
    Account save(Account account);
    int deleteAccountById(Long id);
}
