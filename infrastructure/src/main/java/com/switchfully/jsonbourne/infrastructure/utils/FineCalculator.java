package com.switchfully.jsonbourne.infrastructure.utils;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class FineCalculator {

    private static final int DAYS_IN_A_WEEK = 7;
    private static final int NO_FINE = 0;
    private static final int DEFAULT_FINE_AMOUNT = 5;
    private static final int EXTRA_FINE_PER_FULL_WEEK = 2;

    public static double CalculateFine(LocalDate returnDate) {
        if (LocalDate.now().isAfter(returnDate)) {
            if (calculateDaysOverdue(returnDate) < DAYS_IN_A_WEEK) {
                return DEFAULT_FINE_AMOUNT;
            } else {
                return DEFAULT_FINE_AMOUNT + EXTRA_FINE_PER_FULL_WEEK * (calculateDaysOverdue(returnDate) / DAYS_IN_A_WEEK);
            }
        }
        return NO_FINE;
    }

    private static int calculateDaysOverdue(LocalDate returnDate) {
        return (int)DAYS.between(returnDate, LocalDate.now());
    }
}
