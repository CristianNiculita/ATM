import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
    private String userName;
    private String password;
    private boolean isAdmin;

    public User(String userName, String password, boolean isAdmin) {
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;

    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }




    //method for creating new user if user is admin
    public  void createUser(String userName, String password,boolean userStatus, boolean isAdmin) {
        if (isAdmin) {
            try {
                //db connection
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/ATM",
                        "root",
                        "root");

                String sql = "INSERT INTO users (userName, password, isAdmin) VALUES (?, ? , ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, userName);
                statement.setString(2, password);
                statement.setBoolean(3, userStatus);

                statement.executeUpdate();
                connection.close();
                System.out.println("User created successfully");

            } catch (SQLException e) {
                System.out.println("Error creating user : " + e.getMessage());
            }

        } else {
            System.out.println("Error : Only admin can create new users!");

        }

    }

}

