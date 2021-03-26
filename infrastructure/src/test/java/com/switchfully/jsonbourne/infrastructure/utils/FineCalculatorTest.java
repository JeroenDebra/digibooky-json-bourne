package com.switchfully.jsonbourne.infrastructure.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FineCalculatorTest {

    @Test
    void calculateFineWhenItIsNotOverDue() {
        LocalDate returnDate = LocalDate.now().plusDays(10);
        assertEquals(0,FineCalculator.CalculateFine(returnDate));
    }

    @Test
    void calculateFineWhenItIs5DaysOverDue() {
        LocalDate returnDate = LocalDate.now().minusDays(5);
        assertEquals(5,FineCalculator.CalculateFine(returnDate));
    }

    @Test
    void calculateFineWhenItIs1WeekOverDue() {
        LocalDate returnDate = LocalDate.now().minusDays(8);
        assertEquals(7,FineCalculator.CalculateFine(returnDate));
    }
}