package edu.psuti.pp.practice.bank;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class CreditAccount extends Account {

    private static final double DEFAULT_INTEREST_CHARGES = 0;
    private static final double DEFAULT_ACCRUED_COMMISSION = 0;
    private static final double DEFAULT_INTEREST_RATE = 0;
    private static final double DEFAULT_CREDIT_LIMIT = 0;

    private double interestRate;
    private double creditLimit;
    private double interestCharges;
    private double accruedCommission;

    public CreditAccount(int id, double balance, double commission) {
        this(id, balance, commission, DEFAULT_CURRENCY, DEFAULT_INTEREST_RATE, DEFAULT_CREDIT_LIMIT);
    }

    public CreditAccount(int id, double balance, double commission, Currency currency) {
        this(id, balance, commission, currency, DEFAULT_INTEREST_RATE, DEFAULT_CREDIT_LIMIT);
    }

    public CreditAccount(int id, double balance, double commission, Currency currency,
                         double interestRate, double creditLimit) {
        super(id, balance, commission, currency);

        this.interestRate = interestRate;
        this.creditLimit = creditLimit;

        interestCharges = DEFAULT_INTEREST_CHARGES;
        accruedCommission = DEFAULT_ACCRUED_COMMISSION;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public double getAccruedCommission() {
        return  accruedCommission;
    }

    public double getInterestCharges() {
        return interestCharges;
    }

    public void chargeInterest(double interest) {
        double balance = getBalance();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);

        if(balance < creditLimit) {
            interestCharges += (creditLimit - balance) * (interestRate / numberOfDays) / 100;
        }
    }

    @Override
    public void payCommission() {
        accruedCommission += getCommission();
    }

    @Override
    public void topUp(double amount) {
        accruedCommission -= amount;
        if(amount > accruedCommission){
            super.topUp(amount - accruedCommission);
        }
    }

    @Override
    public void pay(double amount) throws InsufficientFundsException {
        if(amount > creditLimit){
            throw  new InsufficientFundsException("Сумма больше лимита по кредитной карте");
        }

        super.pay(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CreditAccount))
            return false;
        if (!super.equals(o))
            return false;

        var that = (CreditAccount)o;
        return Double.compare(that.interestRate, interestRate) == 0
                && Double.compare(that.creditLimit, creditLimit) == 0
                && Double.compare(that.interestCharges, interestCharges) == 0
                && Double.compare(that.accruedCommission, accruedCommission) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), interestRate, creditLimit, interestCharges, accruedCommission);
    }

    @Override
    public String toString() {
        return "CreditAccount{" +
                "interestRate=" + interestRate +
                ", creditCardLimit=" + creditLimit +
                ", interestCharges=" + interestCharges +
                ", accruedCommission=" + accruedCommission +
                '}';
    }
}
