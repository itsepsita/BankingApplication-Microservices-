package com.db.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public Account deposit(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    public Account withdraw(Long id, Double amount) {
        Account account = accountRepository.findById(id).orElseThrow();
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }

    public Double getBalance(Long id) {
        return accountRepository.findById(id).orElseThrow().getBalance();
    }
}
