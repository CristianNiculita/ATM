abstract  public class Card {
    private double balance;
    private String owner;
    private long numberCard;
    private short pin;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setNumberCard(long numberCard) {
        this.numberCard = numberCard;
    }

    public void setPin(short pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwner() {
        return owner;
    }

    public long getNumberCard() {
        return numberCard;
    }

    public short getPin() {
        return pin;
    }

    abstract void withdrawal(double amount);

    public void deposit(double amount) {
        balance = balance + amount;
        System.out.println("Deposit : " + amount + "\nThe new balance : " + balance);

    }

    public void checkBalance() {
        System.out.println("\nYour balance is : " + balance);
    }


}

