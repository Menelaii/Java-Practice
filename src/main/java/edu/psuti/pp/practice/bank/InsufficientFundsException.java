package edu.psuti.pp.practice.bank;

public class InsufficientFundsException extends Exception {

    private static final String DEFAULT_MESSAGE = "Недостаточно средств";

    public InsufficientFundsException(){
        super(DEFAULT_MESSAGE);
    }

    public InsufficientFundsException(String message){
        super(message);
    }
}
