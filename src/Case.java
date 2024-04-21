import java.sql.*;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Long.parseLong;

public class Case {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    Scanner scanner = new Scanner(System.in);
    public Case() throws SQLException {
                this.con = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/ATM", // db url
                "root",
                "root");



    }

    public void menu () {
        System.out.println("Automated Teller Machine");
        System.out.println("Choose 1 for Withdraw");
        System.out.println("Choose 2 for Deposit");
        System.out.println("Choose 3 for Check Balance");
        System.out.println("Choose 4 for Create User");
        System.out.println("Choose 5 for Create Card");
        System.out.println("Choose 6 for EXIT");
        System.out.print("Choose the operation you want to perform:");
    }

    public void withdrawn() throws Exception {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM credit_card WHERE cardNumber = ? and PIN = ?");
        System.out.println("Insert card number: ");
        long cardNumber = scanner.nextLong();
        System.out.println("Insert PIN: ");
        short cardPin = scanner.nextShort();
        ps.setLong(1, cardNumber);
        ps.setShort(2, cardPin);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.print("Enter money to be withdrawn: ");
            double withdrawnAmount = scanner.nextDouble();
            CreditCard creditCard = new CreditCard(
                    rs.getDouble("balance"),
                    rs.getString("titular"),
                    rs.getLong("cardNumber"),
                    rs.getShort("PIN"));
            creditCard.withdrawal(withdrawnAmount);
            ps = con.prepareStatement("update  credit_card set balance = ? where cardNumber = ? and PIN = ?");
            ps.setDouble(1,creditCard.getBalance());
            ps.setLong(2, cardNumber);
            ps.setShort(3, cardPin);
            int execute = ps.executeUpdate();

        } else {
            ps = con.prepareStatement("SELECT * FROM debit_card WHERE cardNumber = ? and PIN = ?");
            ps.setLong(1, cardNumber);
            ps.setShort(2, cardPin);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.print("Enter money to be withdrawn: ");
                double withdrawnAmount = scanner.nextDouble();
                DebitCard debitCard = new DebitCard(
                        rs.getDouble("balance"),
                        rs.getString("titular"),
                        rs.getLong("cardNumber"),
                        rs.getShort("PIN"));
                debitCard.withdrawal(withdrawnAmount);
                ps = con.prepareStatement("update  debit_card set balance = ? where cardNumber = ? and PIN = ?");
                ps.setDouble(1,debitCard.getBalance());
                ps.setLong(2, cardNumber);
                ps.setShort(3, cardPin);
                int execute = ps.executeUpdate();
            }
        }
    }

    public void deposit () throws Exception {
        ps = con.prepareStatement("SELECT * FROM credit_card WHERE cardNumber = ? and PIN = ?");
        System.out.println("Insert card number: ");
        long cardNumber = scanner.nextLong();
        System.out.println("Insert PIN: ");
        short cardPin = scanner.nextShort();
        ps.setLong(1, cardNumber);
        ps.setShort(2, cardPin);
        rs = ps.executeQuery();
        if (rs.next()) {
            System.out.print("Enter money to be deposit: ");
            double depositAmount = scanner.nextDouble();
            CreditCard creditCard = new CreditCard(
                    rs.getDouble("balance"),
                    rs.getString("titular"),
                    rs.getLong("cardNumber"),
                    rs.getShort("PIN"));
            creditCard.deposit(depositAmount);
            ps = con.prepareStatement("update  credit_card set balance = ? where cardNumber = ? and PIN = ?");
            ps.setDouble(1, creditCard.getBalance());
            ps.setLong(2, cardNumber);
            ps.setShort(3, cardPin);
            int execute = ps.executeUpdate();

        } else {
            ps = con.prepareStatement("SELECT * FROM debit_card WHERE cardNumber = ? and PIN = ?");
            ps.setLong(1, cardNumber);
            ps.setShort(2, cardPin);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.print("Enter money to be deposit: ");
                double depositAmount = scanner.nextDouble();
                DebitCard debitCard = new DebitCard(
                        rs.getDouble("balance"),
                        rs.getString("titular"),
                        rs.getLong("cardNumber"),
                        rs.getShort("PIN"));
                debitCard.deposit(depositAmount);
                ps = con.prepareStatement("update  debit_card set balance = ? where cardNumber = ? and PIN = ?");
                ps.setLong(1, cardNumber);
                ps.setShort(2, cardPin);
                int execute = ps.executeUpdate();
            }
        }
    }

    public void balance() throws Exception {
        ps = con.prepareStatement("SELECT * FROM credit_card WHERE cardNumber = ? and PIN = ?");
        System.out.println("Insert card number: ");
        long cardNumber = scanner.nextLong();
        System.out.println("Insert PIN: ");
        short cardPin = scanner.nextShort();
        ps.setLong(1, cardNumber);
        ps.setShort(2, cardPin);
        rs = ps.executeQuery();
        if (rs.next()) {
            CreditCard creditCard = new CreditCard(
                    rs.getDouble("balance"),
                    rs.getString("titular"),
                    rs.getLong("cardNumber"),
                    rs.getShort("PIN"));
            creditCard.checkBalance();

        } else {
            ps = con.prepareStatement("SELECT * FROM debit_card WHERE cardNumber = ? and PIN = ?");
            ps.setLong(1, cardNumber);
            ps.setShort(2, cardPin);
            rs = ps.executeQuery();
            if (rs.next()) {
                DebitCard debitCard = new DebitCard(
                        rs.getDouble("balance"),
                        rs.getString("titular"),
                        rs.getLong("cardNumber"),
                        rs.getShort("PIN"));
                debitCard.checkBalance();
            }
        }
    }

    public void createUser() throws Exception {
        ps = con.prepareStatement("SELECT * FROM users WHERE userName = ? and password = ?");
        System.out.println("Admin Login");
        System.out.println("Insert user name: ");
        String username = scanner.nextLine();
        System.out.println("Insert password: ");
        String password = scanner.nextLine();
        ps.setString(1, username);
        ps.setString(2, password);
        rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Login successful!");
            System.out.println("Insert new user name: ");
            String newUserName = scanner.nextLine();
            System.out.println("Insert new password: ");
            String newPassword = scanner.nextLine();
            System.out.println("Declare is user will be admin: (yes/no) ");
            String isAdminInput = scanner.nextLine();
            boolean userStatus = isAdminInput.equalsIgnoreCase("yes"); //DACA YES ESTI TRUE ALTFEL NU
            User user = new User(rs.getString("userName"), rs.getString("password"), rs.getBoolean("isAdmin"));
            user.createUser(newUserName, newPassword, userStatus, user.isAdmin());
        }
    }

    public void createCard() throws Exception {
        int min = 1000; // Minimum value of range
        int max = 9999; // Maximum value of range
        String commonNumber = "1234";
        long cardnumber = parseLong( commonNumber + new Random().nextInt(min, max));//random card number
        boolean cardExist = verifyCardExist(cardnumber);
        while (cardExist) {
            cardnumber = parseLong( commonNumber + new Random().nextInt(min, max));
            cardExist = verifyCardExist(cardnumber);
        }

        System.out.println("Insert card type :");
        String cardType = scanner.nextLine();
        if (cardType.equalsIgnoreCase("debit")) {
            System.out.println("Insert user name: ");
            String owner = scanner.nextLine();
            System.out.println("Insert card PIN: ");
            short pin = scanner.nextShort();

            ps = con.prepareStatement("INSERT INTO debit_card (titular, cardNumber, balance, PIN) VALUES (?, ?, ?, ?)");
            ps.setString(1, owner);
            ps.setLong(2, cardnumber);
            ps.setDouble(3, 0.0);
            ps.setShort(4, pin);
            int execute = ps.executeUpdate();
        } else if (cardType.equalsIgnoreCase("credit")) {
            System.out.println("Insert user name: ");
            String owner = scanner.nextLine();
            System.out.println("Insert card PIN: ");
            short pin = scanner.nextShort();


            ps = con.prepareStatement("INSERT INTO credit_card (titular, cardNumber, balance, PIN) VALUES (?, ?, ?, ?)");
            ps.setString(1, owner);
            ps.setLong(2, cardnumber);
            ps.setDouble(3, 0.0);
            ps.setShort(4, pin);
            int execute = ps.executeUpdate();


        }

    }
    public boolean verifyCardExist (long cardnumber) throws SQLException {
        ps = con.prepareStatement("SELECT * FROM debit_card WHERE cardNumber = ?");
        ps.setLong(1, cardnumber);
        rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        }

        ps = con.prepareStatement("SELECT * FROM credit_card WHERE cardNumber = ?");
        ps.setLong(1, cardnumber);
        rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }

}
