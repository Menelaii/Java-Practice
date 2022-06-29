package edu.psuti.pp.practice.bank;

public class AccountNumberGenerator {

    private static final int DEFAULT_NUMBER = 0;

    private static int number;

    public static int getNext() {
        return number++;
    }

    public static int getCurrent() {
        return number;
    }

    public static void reset() {
        number = DEFAULT_NUMBER;
    }
}
