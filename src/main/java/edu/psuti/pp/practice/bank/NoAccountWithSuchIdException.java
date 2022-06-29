package edu.psuti.pp.practice.bank;

public class NoAccountWithSuchIdException extends Exception {

    private static final String DEFAULT_MESSAGE = "Аккаунт с таким id не найден";

    public NoAccountWithSuchIdException()
    {
        super(DEFAULT_MESSAGE);
    }
}
