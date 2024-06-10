package com.example.atm;

public class DebitCard extends Card {
    public DebitCard(double balance, String owner, long numberCard, short pin) {
        super.setBalance(balance);
        super.setOwner(owner);
        super.setNumberCard(numberCard);
        super.setPin(pin);

    }

    boolean withdrawal(double amount) {
        if (super.getBalance() >= amount) {
            super.setBalance(super.getBalance() - amount);
            return true;
        } else {
            return false;
        }
    }
}



