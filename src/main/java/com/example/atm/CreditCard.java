package com.example.atm;

public class CreditCard extends Card {
    public CreditCard(double balance, String owner, long numberCard, short pin) {
        super.setBalance(balance);
        super.setOwner(owner);
        super.setNumberCard(numberCard);
        super.setPin(pin);

    }
    boolean withdrawal(double amount) {
        int creditLimit = -2500;
        if (super.getBalance() - amount >= creditLimit) {
            super.setBalance(super.getBalance() - amount);
            return true;
        } else {
            return false;
        }
    }
}

