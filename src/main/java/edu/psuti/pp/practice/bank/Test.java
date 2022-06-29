package edu.psuti.pp.practice.bank;

public class Test {

     public static void main(String[] args) {
          var client = new NaturalClient("Daniil", "Zhezha", "24234234_2223");
          var account = new DebitAccount(AccountNumberGenerator.getNext(), 3000);

          client.add(account);

          client.addTo(account, 300);

          System.out.println("balance on account: " + account.getBalance() + " " + account.getCurrency());

          account.setCurrency(Currency.USD);

          System.out.println("balance on account: " + account.getBalance() + " " + account.getCurrency());
     }
}
