public class DebitCard extends Card {



    public DebitCard(double balance, String owner, long numberCard, short pin) {
        super.setBalance(balance);
        super.setOwner(owner);
        super.setNumberCard(numberCard);
        super.setPin(pin);

    }



    void withdrawal(double amount) {
        if (super.getBalance() >= amount) {
            super.setBalance(super.getBalance() - amount);
            System.out.println("Withdrawal : " + amount + "\nThe new balance : " + super.getBalance());

        } else {
            System.out.println("Insufficient founds");
        }

    }


}



