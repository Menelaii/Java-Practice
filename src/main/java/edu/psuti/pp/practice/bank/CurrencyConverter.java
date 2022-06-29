package edu.psuti.pp.practice.bank;

public class CurrencyConverter {

    public static double convert(double amount, Currency from, Currency to) {
        return amount / from.IN_USD * to.IN_USD;
    }
}
