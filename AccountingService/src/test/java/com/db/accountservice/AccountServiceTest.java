package com.db.AccountService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount() {
        Account account = new Account();
        account.setName("John");
        account.setEmail("john@example.com");
        account.setPhoneNumber("1234567890");
        account.setPassword("password");

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account savedAccount = accountService.createAccount(account);

        assertNotNull(savedAccount);
        assertEquals("John", savedAccount.getName());
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testGetBalance_Success() {
        // Given
        Long accountId = 1L;
        Account account = new Account();
        account.setId(accountId);
        account.setBalance(100.0);

        // Mock the repository method
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // When
        Double balance = accountService.getBalance(accountId);

        // Then
        assertNotNull(balance);
        assertEquals(100.0, balance);
    }

    @Test
    public void testGetBalance_AccountNotFound() {
        // Given
        Long accountId = 2L;

        // Mock the repository method to return empty
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

       /*  // When / Then
        assertThrows(NoSuchElementException.class, () -> accountService.getBalance(accountId));
    */ }
    @Test
    void testDeposit() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(500.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account updatedAccount = accountService.deposit(1L, 300.0);

        assertEquals(800.0, updatedAccount.getBalance());
    }

    @Test
    void testWithdrawSuccess() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(500.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account updatedAccount = accountService.withdraw(1L, 200.0);

        assertEquals(300.0, updatedAccount.getBalance());
    }

    @Test
    void testWithdrawFailure_InsufficientBalance() {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(100.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            accountService.withdraw(1L, 200.0);
        });

        assertEquals("Insufficient balance", thrown.getMessage());
    }
}
