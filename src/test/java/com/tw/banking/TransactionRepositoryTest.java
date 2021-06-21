package com.tw.banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionRepositoryTest {

    @Test
    void should_addDeposit_add_transactions() {
        // given
        Clock clock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        int amount = 100;

        // when
        when(clock.todayAsString()).thenReturn("2021/06/21");
        transactionRepository.addDeposit(amount);

        // then
        Assertions.assertEquals(1, transactionRepository.transactions.size());
        Assertions.assertEquals("2021/06/21", transactionRepository.transactions.get(0).date());
        Assertions.assertEquals(100, transactionRepository.transactions.get(0).amount());
    }

    @Test
    void should_addWithdraw_add_transactions() {
        // given
        Clock clock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        int amount = 100;

        // when
        when(clock.todayAsString()).thenReturn("2021/06/21");
        transactionRepository.addWithdraw(amount);

        // then
        Assertions.assertEquals(1, transactionRepository.transactions.size());
        Assertions.assertEquals("2021/06/21", transactionRepository.transactions.get(0).date());
        Assertions.assertEquals(-100, transactionRepository.transactions.get(0).amount());
    }

    @Test
    void should_allTransactions_return_all_transactions() {
        // given
        Clock clock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        int amount = 100;

        // when
        when(clock.todayAsString()).thenReturn("2021/06/21");
        transactionRepository.addWithdraw(amount);
        List<Transaction> transactions =  transactionRepository.allTransactions();

        // then
        Assertions.assertEquals(1, transactions.size());
        Assertions.assertEquals("2021/06/21", transactions.get(0).date());
        Assertions.assertEquals(-100, transactions.get(0).amount());
    }

    @Test
    void should_allTransactions_throw_exception() {
        // given
        Clock clock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        int amount = 100;

        // when
        when(clock.todayAsString()).thenReturn("2021/06/21");
        transactionRepository.addWithdraw(amount);
        List<Transaction> transactions =  transactionRepository.allTransactions();

        // then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            transactions.add(new Transaction("date", amount));
        });
    }

}