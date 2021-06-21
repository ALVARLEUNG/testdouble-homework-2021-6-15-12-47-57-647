package com.tw.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountTest {

    Account account;
    TransactionRepository transactionRepository;
    Printer printer;

    @BeforeEach
    void init() {
        transactionRepository = mock(TransactionRepository.class);
        printer = mock(Printer.class);
        account = new Account(transactionRepository, printer);
    }

    @Test
    void should_deposit_execute_addDeposit() {
        // given
        int amount = 100;

        // when
        account.deposit(amount);

        // then
        verify(transactionRepository, times(1)).addDeposit(eq(amount));
    }

    @Test
    void should_withdraw_execute_addWithdraw() {
        // given
        int amount = 200;

        // when
        account.withdraw(amount);

        // then
        verify(transactionRepository, times(1)).addWithdraw(eq(amount));
    }

    @Test
    void should_printStatement_execute_allTransactions() {
        // given
        String date = "date";
        int amount = 100;
        Transaction transaction = new Transaction(date, amount);
        List<Transaction> transactionList = Collections.singletonList(transaction);

        // when
        when(transactionRepository.allTransactions()).thenReturn(transactionList);
        account.printStatement();

        // then
        verify(transactionRepository, times(1)).allTransactions();
        verify(printer, times(1)).print(eq(transactionList));
    }

}