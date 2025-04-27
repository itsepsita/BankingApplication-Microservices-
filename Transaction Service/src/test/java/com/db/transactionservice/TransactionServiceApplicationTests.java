package com.db.TransactionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionServiceApplicationTests {

	 @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransfer() {
        Transaction transaction = new Transaction();
        transaction.setFromAccountId(1L);
        transaction.setToAccountId(2L);
        transaction.setAmount(100.0);

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction savedTransaction = transactionService.transfer(1L, 2L, 100.0);

        assertEquals(1L, savedTransaction.getFromAccountId());
        assertEquals(2L, savedTransaction.getToAccountId());
        assertEquals(100.0, savedTransaction.getAmount());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void testGetTransactions() {
        Transaction t1 = new Transaction();
        t1.setFromAccountId(1L);
        t1.setToAccountId(2L);
        t1.setAmount(100.0);

        Transaction t2 = new Transaction();
        t2.setFromAccountId(2L);
        t2.setToAccountId(1L);
        t2.setAmount(50.0);

        when(transactionRepository.findByFromAccountIdOrToAccountId(1L, 1L)).thenReturn(List.of(t1, t2));

        List<Transaction> transactions = transactionService.getTransactions(1L);

        assertEquals(2, transactions.size());
    }
	@Test
	void contextLoads() {
	}



}
