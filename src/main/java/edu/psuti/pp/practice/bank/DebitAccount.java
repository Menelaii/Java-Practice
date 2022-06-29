package edu.psuti.pp.practice.bank;

public class DebitAccount extends Account {

    public DebitAccount(int id, double balance) {
        this(id, balance, DEFAULT_COMMISSION, DEFAULT_CURRENCY);
    }

    public DebitAccount(int id, double balance, double commission) {
        this(id, balance, commission, DEFAULT_CURRENCY);
    }

    public DebitAccount(int id, double balance, double commission, Currency currency) {
        super(id, balance, commission, currency);
    }
}
