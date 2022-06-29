package edu.psuti.pp.practice.bank;

public enum Currency {
    USD(1),
    EUR(0.85),
    JOY(110.48),
    TRY(8.85),
    AED(3.67),
    RUB(72.96);

    public final double IN_USD;
    Currency(double IN_USD){
        this.IN_USD = IN_USD;
    }
}
