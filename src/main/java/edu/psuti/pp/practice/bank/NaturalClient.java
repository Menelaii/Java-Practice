package edu.psuti.pp.practice.bank;

import java.util.ArrayList;
import java.util.List;

public class NaturalClient implements Client {
    private final List<Account> accounts;

    private String passportId;
    private String name;
    private String surname;


    public NaturalClient(String name, String surname, String passportId) {
        this(name, surname, passportId, new ArrayList<>());
    }

    public NaturalClient(String name, String surname, String passportId, List<Account> accounts) {
        this.name = name;
        this.surname = surname;
        this.passportId = passportId;
        this.accounts = accounts;
    }

    public Account getAccountWith(int id) throws NoAccountWithSuchIdException {
        for (Account account : accounts)
        {
            if (id == account.getId())
            {
                return account;
            }
        }

        throw new NoAccountWithSuchIdException();
    }

    public List<Account> getAccounts()
    {
        return accounts;
    }

    @Override
    public List<Account> getDebitAccounts() {
        return getAccountsWithType(DebitAccount.class);
    }

    @Override
    public List<Account> getCreditAccounts() {
        return getAccountsWithType(CreditAccount.class);
    }

    @Override
    public double getBalanceFromDebitAccounts() {
        var debitAccounts = getAccountsWithType(DebitAccount.class);
        return getBalanceFrom(debitAccounts);
    }

    @Override
    public double getDebts() {
        double debt = 0;
        double balance;
        for (Account account : accounts) {
            balance = account.getBalance();
            debt += balance < 0 ? balance * -1 : 0;

            if(account instanceof CreditAccount) {
                var creditAccount = (CreditAccount)account;
                debt += creditAccount.getAccruedCommission();
                debt += creditAccount.getInterestCharges();
            }
        }

        return debt;
    }

    public double getBalanceFromAllAccounts() {
        return getBalanceFrom(accounts);
    }

    public List<Account> getAccountsWithPositiveBalance() {
        List<Account> accountsWithPositiveBalance = new ArrayList<>();
        for (Account account : accounts) {
            if(account.getBalance() >= 0) {
                accountsWithPositiveBalance.add(account);
            }
        }

        return accountsWithPositiveBalance;
    }

    public void deleteAccountWith(int id) {
        accounts.removeIf(account -> account.getId() == id);
    }

    public void add(Account account) {
        accounts.add(account);
    }

    public void payFrom(Account account, double amount) throws InsufficientFundsException {
        if(account.getBalance() < amount){
            throw new InsufficientFundsException();
        }

        account.pay(amount);
    }

    @Override
    public void addTo(Account account, double amount) {
        if(account == null){
            throw new NullPointerException();
        }

        account.topUp(amount);
    }

    private List<Account> getAccountsWithType(Class<? extends Account> type){
        var accounts = new ArrayList<Account>();
        for (Account account : this.accounts){
            if(account.getClass() == type){
                accounts.add(account);
            }
        }

        return accounts;
    }

    private double getBalanceFrom(List<Account> accounts){
        double balance = 0;
        for (Account account : accounts) {
            balance += account.getBalance();
        }

        return balance;
    }
}
