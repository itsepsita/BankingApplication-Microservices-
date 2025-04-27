package com.db.accountservice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;


    public AccountService() {
        this.passwordEncoder = new BCryptPasswordEncoder();  // BCryptPasswordEncoder to hash passwords
    }

    public Account createAccount(Account account) {
       
        try {           

            // Hash the password before saving
            String hashedPassword = passwordEncoder.encode(account.getPassword());
            account.setPassword(hashedPassword);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting sensitive data", e);
        }

        return accountRepository.save(account);
    }

    public Optional<Account> getAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        
        return Optional.of(account);
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

    // Method to verify if the password matches
    public boolean checkPassword(String rawPassword, String storedHashedPassword) {
        return passwordEncoder.matches(rawPassword, storedHashedPassword);
    }

    public boolean validateLogin(String enteredPassword, String storedHashedPassword) {
        return passwordEncoder.matches(enteredPassword, storedHashedPassword);
    }

    
    
    
}
