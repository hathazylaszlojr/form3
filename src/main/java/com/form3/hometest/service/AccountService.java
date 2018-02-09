package com.form3.hometest.service;

import com.form3.hometest.entity.Account;
import com.form3.hometest.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    
    private final AccountRepository accountRepository;
    
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    public Account save(Account account) {
        return accountRepository.save(account);
    }
    
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    public Account findById(String accountNumber) {
        return accountRepository.findById(accountNumber);
    }

    public List<Account> getAccountsByName(String name) {
        return accountRepository.getAccountsByName(name);
    }

    public boolean deleteAccount(String accountId) {
        Account account = accountRepository.findById(accountId);
        if (account != null) {
            accountRepository.delete(account);
        }
        return account != null;
    }

    public boolean update(Account account) {
        if (accountRepository.exists(account.getAccountNumber())) {
            accountRepository.save(account);
            return true;
        }
        return false;
    }
    
}
