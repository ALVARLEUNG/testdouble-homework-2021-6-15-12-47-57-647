package com.tw.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrinterTest {

    @Test
    void should_print_with_header_without_transactions() {
        // given
        Console console = mock(Console.class);
        Printer printer = new Printer(console);

        // when
        printer.print(new ArrayList<Transaction>());

        // then
        verify(console, times(1)).printLine(eq(Printer.STATEMENT_HEADER));
    }

    @Test
    void should_print_with_header_and_transactions () {
        // given
        Console console = mock(Console.class);
        Printer printer = new Printer(console);

        String date1 = "15/06/2021";
        int amount1 = -100;
        Transaction transaction1 = new Transaction(date1, amount1);

        String date2 = "15/06/2021";
        int amount2 = 200;
        Transaction transaction2 = new Transaction(date2, amount2);

        List<Transaction> transactionList = Arrays.asList(transaction1, transaction2);

        // when
        printer.print(transactionList);

        // then
        verify(console, times(3)).printLine(anyString());
        verify(console, times(1)).printLine(eq(Printer.STATEMENT_HEADER));
        verify(console, times(1)).printLine(eq("15/06/2021 | -100 | -100"));
        verify(console, times(1)).printLine(eq("15/06/2021 | 200 | 100"));
    }

}