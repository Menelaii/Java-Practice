package edu.psuti.pp.practice.bank;

import java.util.Objects;

public abstract class Account {

    protected static final double DEFAULT_COMMISSION = 0;
    protected static final Currency DEFAULT_CURRENCY = Currency.RUB;

    private final int id;

    private double balance;
    private double commission;
    private Currency currency;

    public Account(int id, double balance) {
        this(id, balance, DEFAULT_COMMISSION, DEFAULT_CURRENCY);
    }

    public Account(int id, double balance, double commission) {
        this(id, balance, commission, DEFAULT_CURRENCY);
    }

    public Account(int id, double balance, double commission, Currency currency) {
        this.id = id;
        this.balance = balance;
        this.commission = commission;
        this.currency = currency;
    }

    public int getId() {
        return  id;
    }

    public double getBalance() {
        return balance;
    }

    public void topUp(double amount) {
        balance += amount;
    }

    public void pay(double amount) throws InsufficientFundsException {
        if(balance < amount){
            throw  new InsufficientFundsException("Недостаточно средств");
        }

        balance -= amount;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public Currency getCurrency() {
        return  currency;
    }

    public void setCurrency(Currency currency) {
        commission = CurrencyConverter.convert(commission, this.currency, currency);
        balance = CurrencyConverter.convert(balance, this.currency, currency);

        this.currency = currency;
    }

    public void payCommission() throws InsufficientFundsException {
        if(balance < commission){
            throw new InsufficientFundsException("Недостаточно средств для погашения комиссии");
        }

        balance -= commission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Account))
            return false;

        var account = (Account)o;
        return id == account.id
                && Double.compare(account.balance, balance) == 0
                && Double.compare(account.commission, commission) == 0
                && currency == account.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, commission, currency);
    }

    @Override
    public String toString() {
        return "Account{" +
                "identifier=" + id +
                ", balance=" + balance +
                ", commission=" + commission +
                ", currency=" + currency +
                '}';
    }
}
