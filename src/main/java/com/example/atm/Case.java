package com.example.atm;
import java.sql.*;
import java.util.Random;
import static java.lang.Long.parseLong;

public class Case {
    private final Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    public Case() throws SQLException {
        this.con = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/ATM", // db url
                "root",
                "root");



    }

    public void deposit (long cardNumber, short cardPin, double depositAmount) {
        try {
            ps = con.prepareStatement("SELECT * FROM credit_card WHERE cardNumber = ? and PIN = ?");
            ps.setLong(1, cardNumber);
            ps.setShort(2, cardPin);
            rs = ps.executeQuery();
            if (rs.next()) {
                CreditCard creditCard = new CreditCard(
                        rs.getDouble("balance"),
                        rs.getString("titular"),
                        rs.getLong("cardNumber"),
                        rs.getShort("PIN"));
                creditCard.deposit(depositAmount);
                ps = con.prepareStatement("update credit_card set balance = ? where cardNumber = ? and PIN = ?");
                ps.setDouble(1, creditCard.getBalance());
                ps.setLong(2, cardNumber);
                ps.setShort(3, cardPin);
                ps.executeUpdate();

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
                    debitCard.deposit(depositAmount);
                    ps = con.prepareStatement("update  debit_card set balance = ? where cardNumber = ? and PIN = ?");
                    ps.setDouble(1, debitCard.getBalance());
                    ps.setLong(2, cardNumber);
                    ps.setShort(3, cardPin);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.out.println( ex.getMessage());
        }
    }

    public boolean withdrawn(long cardNumber, short cardPin, double withdrawnAmount) {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM credit_card WHERE cardNumber = ? and PIN = ?");
            ps.setLong(1, cardNumber);
            ps.setShort(2, cardPin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CreditCard creditCard = new CreditCard(
                        rs.getDouble("balance"),
                        rs.getString("titular"),
                        rs.getLong("cardNumber"),
                        rs.getShort("PIN"));
                if (creditCard.withdrawal(withdrawnAmount)) {
                    ps = con.prepareStatement("update  credit_card set balance = ? where cardNumber = ? and PIN = ?");
                    ps.setDouble(1, creditCard.getBalance());
                    ps.setLong(2, cardNumber);
                    ps.setShort(3, cardPin);
                    ps.executeUpdate();
                    return true;
                }
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
                    if (debitCard.withdrawal(withdrawnAmount)) {
                        ps = con.prepareStatement("update  debit_card set balance = ? where cardNumber = ? and PIN = ?");
                        ps.setDouble(1, debitCard.getBalance());
                        ps.setLong(2, cardNumber);
                        ps.setShort(3, cardPin);
                        ps.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public double balance(long cardNumber, short cardPin)  {
        try {
            ps = con.prepareStatement("SELECT * FROM credit_card WHERE cardNumber = ? and PIN = ?");
            ps.setLong(1, cardNumber);
            ps.setShort(2, cardPin);
            rs = ps.executeQuery();
            if (rs.next()) {
                CreditCard creditCard = new CreditCard(
                        rs.getDouble("balance"),
                        rs.getString("titular"),
                        rs.getLong("cardNumber"),
                        rs.getShort("PIN"));
                return creditCard.checkBalance();

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
                   return debitCard.checkBalance();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return 0.0;
    }

    public boolean verifyUserExist(String userName, String password) {
        boolean userExists = false;
        try {
            ps = con.prepareStatement("SELECT * FROM users WHERE userName = ? AND password = ? AND isAdmin = 1");
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            // Verificăm dacă există un rezultat
            if (rs.next()) {
                userExists = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userExists;
    }

    public void createUser(String newUserName, String newPassword, String isAdminInput) {
        try {
            boolean userStatus = isAdminInput.equalsIgnoreCase("yes");
            // Pregătirea interogării SQL pentru inserarea unui nou utilizator
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (userName, password, isAdmin) VALUES (?, ?, ?)");
            ps.setString(1, newUserName);
            ps.setString(2, newPassword);
            ps.setBoolean(3, userStatus);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void createCard(String cardType, String owner, short pin)  {

        int min = 1000; // Minimum value of range
        int max = 9999; // Maximum value of range
        String commonNumber = "1234";
        long cardnumber = parseLong( commonNumber + new Random().nextInt(min, max));//random card number
        boolean cardExist = verifyCardExist(cardnumber, pin);
        while (cardExist) {
            cardnumber = parseLong( commonNumber + new Random().nextInt(min, max));
            cardExist = verifyCardExist(cardnumber, pin);
        }
        try {
            if (cardType.equalsIgnoreCase("debit")) {
                ps = con.prepareStatement("INSERT INTO debit_card (titular, cardNumber, balance, PIN) VALUES (?, ?, ?, ?)");
                ps.setString(1, owner);
                ps.setLong(2, cardnumber);
                ps.setDouble(3, 0.0);
                ps.setShort(4, pin);
                ps.executeUpdate();
            } else if (cardType.equalsIgnoreCase("credit")) {
                ps = con.prepareStatement("INSERT INTO credit_card (titular, cardNumber, balance, PIN) VALUES (?, ?, ?, ?)");
                ps.setString(1, owner);
                ps.setLong(2, cardnumber);
                ps.setDouble(3, 0.0);
                ps.setShort(4, pin);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean deleteUser (int userId) {
        try {
            // Verifică dacă userId există
            ps = con.prepareStatement("SELECT COUNT(*) FROM users WHERE userId = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            rs.next();  // Mută cursorul la primul rând
            int count = rs.getInt(1);
            if (count > 0) {
                // Dacă userId există, șterge utilizatorul
                ps = con.prepareStatement("DELETE FROM users WHERE userId = ?");
                ps.setInt(1, userId);
                ps.executeUpdate();
                return true;
            } else {
               return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean deleteCard(long cardNumber) {
        try {
            // Verificăm dacă numărul cardului există în tabela credit_card
            ps = con.prepareStatement("SELECT * FROM credit_card WHERE cardNumber = ?");
            ps.setLong(1, cardNumber);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Dacă cardul există în credit_card, îl ștergem
                ps = con.prepareStatement("DELETE FROM credit_card WHERE cardNumber = ?");
                ps.setLong(1, cardNumber);
                ps.executeUpdate();
                return true; // Returnăm true pentru a indica că ștergerea a fost realizată cu succes
            } else {
                // Verificăm dacă numărul cardului există în tabela debit_card
                ps = con.prepareStatement("SELECT * FROM debit_card WHERE cardNumber = ?");
                ps.setLong(1, cardNumber);
                rs = ps.executeQuery();
                if (rs.next()) {
                    // Dacă cardul există în debit_card, îl ștergem
                    ps = con.prepareStatement("DELETE FROM debit_card WHERE cardNumber = ?");
                    ps.setLong(1, cardNumber);
                    ps.executeUpdate();
                    return true; // Returnăm true pentru a indica că ștergerea a fost realizată cu succes
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false; // Returnăm false pentru a indica că ștergerea nu a putut fi realizată
    }

    public boolean verifyCardExist (long cardnumber, short pin) {
        try {
            ps = con.prepareStatement("SELECT * FROM debit_card WHERE cardNumber = ? and PIN = ?");
            ps.setLong(1, cardnumber);
            ps.setShort(2, pin);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

            ps = con.prepareStatement("SELECT * FROM credit_card WHERE cardNumber = ? and PIN = ?");
            ps.setLong(1, cardnumber);
            ps.setShort(2, pin);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            return false;
    }
}

