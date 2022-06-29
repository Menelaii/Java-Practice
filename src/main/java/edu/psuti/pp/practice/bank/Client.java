package edu.psuti.pp.practice.bank;

import java.util.List;

public interface Client {

    Account getAccountWith(int id) throws NoAccountWithSuchIdException;

    List<Account> getAccounts();

    List<Account> getDebitAccounts();

    List<Account> getCreditAccounts();

    double getBalanceFromDebitAccounts();

    double getDebts();

    List<Account> getAccountsWithPositiveBalance();

    void deleteAccountWith(int id);

    void add(Account account);

    void payFrom(Account account, double amount) throws InsufficientFundsException;

    void addTo(Account account, double amount);
}
