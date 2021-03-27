package com.switchfully.jsonbourne.infrastructure.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class FineCalculatorTest {

    @Test
    void calculateFineWhenItIsNotOverDue() {
        LocalDate returnDate = LocalDate.now().plusDays(10);
        assertThat(0.0).isEqualTo(FineCalculator.CalculateFine(returnDate));
    }

    @Test
    void calculateFineWhenItIs5DaysOverDue() {
        LocalDate returnDate = LocalDate.now().minusDays(5);
        assertThat(5.0).isEqualTo(FineCalculator.CalculateFine(returnDate));
    }

    @Test
    void calculateFineWhenItIs1WeekOverDue() {
        LocalDate returnDate = LocalDate.now().minusDays(8);
        assertThat(7.0).isEqualTo(FineCalculator.CalculateFine(returnDate));
    }

    @Test
    void calculateFineWhenItIs3WeekAnd2DaysOverDue() {
        LocalDate returnDate = LocalDate.now().minusDays(23);
        assertThat(11.0).isEqualTo(FineCalculator.CalculateFine(returnDate));
    }
}