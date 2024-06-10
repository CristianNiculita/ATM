package com.example.atm;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

import static java.lang.Long.parseLong;
import static java.lang.Short.parseShort;

public class Atm extends Application {
    private Case atmCase;

    @Override
    public void start(Stage stage) throws SQLException {
        atmCase = new Case();

        // Main Scene
        VBox mainPane = new VBox(15);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setStyle("-fx-background-color: #333444");
        Label titleLabel = new Label("AUTOMATED TELLER MACHINE");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: white; -fx-font-family: Cambria;");
        titleLabel.setAlignment(Pos.TOP_CENTER);
        Button depositButton = new Button("Deposit");
        depositButton.setPrefSize(100, 25);
        Button withdrawalButton = new Button("Withdrawal");
        withdrawalButton.setPrefSize(100, 25);
        Button balanceButton = new Button("Balance");
        balanceButton.setPrefSize(100, 25);
        Button createUserButton = new Button("Create User");
        createUserButton.setPrefSize(100, 25);
        Button createCardButton = new Button("Create Card");
        createCardButton.setPrefSize(100, 25);
        Button deleteUserButton = new Button("Delete user");
        deleteUserButton.setPrefSize(100, 25);
        Button deleteCardButton = new Button("Delete card");
        deleteCardButton.setPrefSize(100, 25);
        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(100, 25);
        mainPane.getChildren().addAll(titleLabel, depositButton, withdrawalButton, balanceButton, createUserButton, createCardButton, deleteUserButton, deleteCardButton, exitButton);
        Scene mainScene = new Scene(mainPane, 600, 400);

        // Deposit Scene
        VBox depositPane = new VBox(10);
        depositPane.setAlignment(Pos.CENTER);
        depositPane.setStyle("-fx-background-color: #333444");
        Label depositLabel = new Label("Enter the amount you want to deposit");
        depositLabel.setStyle("-fx-text-fill: #ffffff;");
        Label depositError = new Label();
        depositError.setStyle("-fx-text-fill: red;");
        TextField depositAmountField = new TextField();
        depositAmountField.setPrefWidth(200);
        depositAmountField.setMinWidth(200);
        depositAmountField.setMaxWidth(200);
        Button confirmDepositButton = new Button("Confirm");
        confirmDepositButton.setPrefSize(100, 25);
        Button depositBackButton = new Button("Back");
        depositBackButton.setPrefSize(100, 25);
        depositPane.getChildren().addAll(depositError, depositLabel, depositAmountField, confirmDepositButton, depositBackButton);
        Scene depositScene = new Scene(depositPane, 600, 400);

        // Withdrawal Scene
        VBox withdrawalPane = new VBox(10);
        withdrawalPane.setAlignment(Pos.CENTER);
        withdrawalPane.setStyle("-fx-background-color: #333444");
        Label withdrawalLabel = new Label("Enter the amount you want to withdraw");
        withdrawalLabel.setStyle("-fx-text-fill: #ffffff;");
        Label withdrawalError = new Label();
        withdrawalError.setStyle("-fx-text-fill: red;");
        TextField withdrawalAmountField = new TextField();
        withdrawalAmountField.setPrefWidth(200);
        withdrawalAmountField.setMinWidth(200);
        withdrawalAmountField.setMaxWidth(200);
        Button confirmWithdrawalButton = new Button("Confirm");
        confirmWithdrawalButton.setPrefSize(100, 25);
        Button withdrawalBackButton = new Button("Back");
        withdrawalBackButton.setPrefSize(100, 25);
        withdrawalPane.getChildren().addAll(withdrawalError, withdrawalLabel, withdrawalAmountField, confirmWithdrawalButton, withdrawalBackButton);
        Scene withdrawalScene = new Scene(withdrawalPane, 600, 400);

        // Balance Scene
        VBox balancePane = new VBox(10);
        balancePane.setAlignment(Pos.CENTER);
        balancePane.setStyle("-fx-background-color: #333444");
        Label balanceLabel = new Label();
        balanceLabel.setStyle("-fx-text-fill: #ffffff;");
        Button balanceBackButton = new Button("Back");
        balanceBackButton.setPrefSize(100, 25);
        balancePane.getChildren().addAll(balanceLabel, balanceBackButton);
        Scene balanceScene = new Scene(balancePane, 600, 400);

        // Create insert card number and pin scene for deposit
        VBox depositCardNumberPane = new VBox(10);
        depositCardNumberPane.setAlignment(Pos.CENTER);
        depositCardNumberPane.setStyle("-fx-background-color: #333444");
        Label depositCardNumberLabel = new Label("Enter number card and pin");
        depositCardNumberLabel.setStyle("-fx-text-fill: #ffffff;");
        Label depositLoginError = new Label();
        depositLoginError.setStyle("-fx-text-fill: red;");
        TextField depositCardNumberField = new TextField();
        depositCardNumberField.setPromptText("card number");
        depositCardNumberField.setPrefWidth(200);
        depositCardNumberField.setMinWidth(200);
        depositCardNumberField.setMaxWidth(200);
        TextField passwordDepositSceneField = new TextField();
        passwordDepositSceneField.setPromptText("pin");
        passwordDepositSceneField.setPrefWidth(200);
        passwordDepositSceneField.setMinWidth(200);
        passwordDepositSceneField.setMaxWidth(200);
        Button depositLoginButton = new Button("Login");
        depositLoginButton.setPrefSize(100, 25);
        Button depositLoginBackButton = new Button("Back");
        depositLoginBackButton.setPrefSize(100, 25);
        depositCardNumberPane.getChildren().addAll(depositLoginError,depositCardNumberLabel, depositCardNumberField, passwordDepositSceneField, depositLoginButton, depositLoginBackButton);
        Scene depositLoginScene = new Scene(depositCardNumberPane, 600, 400);

        // Create insert card number and pin scene for withdrawal
        VBox withdrawalCardNumberPane = new VBox(10);
        withdrawalCardNumberPane.setAlignment(Pos.CENTER);
        withdrawalCardNumberPane.setStyle("-fx-background-color: #333444");
        Label withdrawalCardNumberLabel = new Label("Enter number card and pin");
        withdrawalCardNumberLabel.setStyle("-fx-text-fill: #ffffff;");
        Label withdrawalLoginError = new Label();
        withdrawalLoginError.setStyle("-fx-text-fill: red;");
        TextField withdrawalCardNumberField = new TextField();
        withdrawalCardNumberField.setPromptText("card number");
        withdrawalCardNumberField.setPrefWidth(200);
        withdrawalCardNumberField.setMinWidth(200);
        withdrawalCardNumberField.setMaxWidth(200);
        TextField passwordWithdrawalSceneField = new TextField();
        passwordWithdrawalSceneField.setPromptText("pin");
        passwordWithdrawalSceneField.setPrefWidth(200);
        passwordWithdrawalSceneField.setMinWidth(200);
        passwordWithdrawalSceneField.setMaxWidth(200);
        Button withdrawalLoginButton = new Button("Login");
        withdrawalLoginButton.setPrefSize(100, 25);
        Button withdrawalLoginBackButton = new Button("Back");
        withdrawalLoginBackButton.setPrefSize(100, 25);
        withdrawalCardNumberPane.getChildren().addAll(withdrawalLoginError, withdrawalCardNumberLabel, withdrawalCardNumberField, passwordWithdrawalSceneField, withdrawalLoginButton, withdrawalLoginBackButton);
        Scene withdrawalLoginScene = new Scene(withdrawalCardNumberPane, 600, 400);

        // Create insert card number and pin scene for balance
        VBox balanceCardNumberPane = new VBox(10);
        balanceCardNumberPane.setAlignment(Pos.CENTER);
        balanceCardNumberPane.setStyle("-fx-background-color: #333444");
        Label balanceCardNumberLabel = new Label("Enter number card and pin");
        balanceCardNumberLabel.setStyle("-fx-text-fill: #ffffff;");
        Label balanceErrorLabel = new Label();
        balanceErrorLabel.setStyle("-fx-text-fill: red;");
        TextField balanceCardNumberField = new TextField();
        balanceCardNumberField.setPromptText("card number");
        balanceCardNumberField.setPrefWidth(200);
        balanceCardNumberField.setMinWidth(200);
        balanceCardNumberField.setMaxWidth(200);
        TextField passwordBalanceSceneField = new TextField();
        passwordBalanceSceneField.setPromptText("pin");
        passwordBalanceSceneField.setPrefWidth(200);
        passwordBalanceSceneField.setMinWidth(200);
        passwordBalanceSceneField.setMaxWidth(200);
        Button balanceLoginButton = new Button("Login");
        balanceLoginButton.setPrefSize(100, 25);
        Button balanceLoginBackButton = new Button("Back");
        balanceLoginBackButton.setPrefSize(100, 25);
        balanceCardNumberPane.getChildren().addAll(balanceErrorLabel,balanceCardNumberLabel, balanceCardNumberField, passwordBalanceSceneField, balanceLoginButton, balanceLoginBackButton);
        Scene balanceLoginScene = new Scene(balanceCardNumberPane, 600, 400);

        // Create insert username and password scene for create user
        VBox createUserUserNamePane = new VBox(10);
        createUserUserNamePane.setAlignment(Pos.CENTER);
        createUserUserNamePane.setStyle("-fx-background-color: #333444");
        Label createUserUserNameLabel = new Label("Enter admin user name and password");
        createUserUserNameLabel.setStyle("-fx-text-fill: #ffffff;");
        Label createUserAdminLoginError = new Label();
        createUserAdminLoginError.setStyle("-fx-text-fill: red;");
        TextField createUserUserNameField = new TextField();
        createUserUserNameField.setPrefWidth(200);
        createUserUserNameField.setMinWidth(200);
        createUserUserNameField.setMaxWidth(200);
        TextField createUserUserPasswordField = new TextField();
        createUserUserPasswordField.setPrefWidth(200);
        createUserUserPasswordField.setMinWidth(200);
        createUserUserPasswordField.setMaxWidth(200);
        Button createUserLoginButton = new Button("Login");
        createUserLoginButton.setPrefSize(100, 25);
        Button createUserLoginBackButton = new Button("Back");
        createUserLoginBackButton.setPrefSize(100, 25);
        createUserUserNamePane.getChildren().addAll(createUserAdminLoginError, createUserUserNameLabel, createUserUserNameField, createUserUserPasswordField, createUserLoginButton, createUserLoginBackButton);
        Scene createUserLoginScene = new Scene(createUserUserNamePane, 600, 400);

        // Create User Scene
        VBox createUserPane = new VBox(10);
        createUserPane.setAlignment(Pos.CENTER);
        createUserPane.setStyle("-fx-background-color: #333444");
        Label createUserLabel = new Label("Enter user name and password");
        createUserLabel.setStyle("-fx-text-fill: #ffffff;");
        Label createUserError = new Label();
        createUserError.setStyle("-fx-text-fill: red;");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setPrefWidth(200);
        usernameField.setMinWidth(200);
        usernameField.setMaxWidth(200);
        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");
        passwordField.setPrefWidth(200);
        passwordField.setMinWidth(200);
        passwordField.setMaxWidth(200);
        RadioButton isAdmin = new RadioButton();
        Label isAdminLabel = new Label("isAdmin");
        isAdminLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px;");
        HBox isAdminContainer = new HBox(10, isAdmin, isAdminLabel);
        isAdminContainer.setAlignment(Pos.CENTER);
        isAdminContainer.setPrefWidth(200);
        isAdminContainer.setMinWidth(200);
        isAdminContainer.setMaxWidth(200);
        Button confirmCreateUserButton = new Button("Confirm");
        confirmCreateUserButton.setPrefSize(100, 25);
        Button createUserBackButton = new Button("Back");
        createUserBackButton.setPrefSize(100, 25);
        createUserPane.getChildren().addAll(createUserError, createUserLabel, usernameField, passwordField, isAdminContainer, confirmCreateUserButton, createUserBackButton);
        Scene createUserScene = new Scene(createUserPane, 600, 400);

        // Choose card to be created scene
        VBox createCardTypePane = new VBox(10);
        createCardTypePane.setAlignment(Pos.CENTER);
        createCardTypePane.setStyle("-fx-background-color: #333444");
        Label createCardTypeLabel = new Label("Card Type");
        createCardTypeLabel.setStyle("-fx-text-fill: #ffffff;");
        Button debitCardButton = new Button("Debit Card");
        debitCardButton.setPrefSize(100, 25);
        Button creditCardButton = new Button("Credit Card");
        creditCardButton.setPrefSize(100, 25);
        Button createCardTypeBackButton = new Button("Back");
        createCardTypeBackButton.setPrefSize(100, 25);
        createCardTypePane.getChildren().addAll(createCardTypeLabel, debitCardButton, creditCardButton, createCardTypeBackButton);
        Scene createCardTypeScene = new Scene(createCardTypePane, 600, 400);

        // Debit card scene
        VBox createDebitCardPane = new VBox(10);
        createDebitCardPane.setAlignment(Pos.CENTER);
        createDebitCardPane.setStyle("-fx-background-color: #333444");
        Label createDebitCardLabel = new Label("Enter user name and pin");
        createDebitCardLabel.setStyle("-fx-text-fill: #ffffff;");
        Label debitError = new Label();
        debitError.setStyle("-fx-text-fill: red;");
        TextField createDebitCardField = new TextField();
        createDebitCardField.setPromptText("Name");
        createDebitCardField.setPrefWidth(200);
        createDebitCardField.setMinWidth(200);
        createDebitCardField.setMaxWidth(200);
        TextField pinDebitField = new TextField();
        pinDebitField.setPromptText("Pin");
        pinDebitField.setPrefWidth(200);
        pinDebitField.setMinWidth(200);
        pinDebitField.setMaxWidth(200);
        Button confirmCreateDebitCardButton = new Button("Confirm");
        confirmCreateDebitCardButton.setPrefSize(100, 25);
        Button createDebitCardBackButton = new Button("Back");
        createDebitCardBackButton.setPrefSize(100, 25);
        createDebitCardPane.getChildren().addAll(debitError, createDebitCardLabel, createDebitCardField, pinDebitField, confirmCreateDebitCardButton, createDebitCardBackButton);
        Scene createDebitCardScene = new Scene(createDebitCardPane, 600, 400);

        // Credit card scene
        VBox createCreditCardPane = new VBox(10);
        createCreditCardPane.setAlignment(Pos.CENTER);
        createCreditCardPane.setStyle("-fx-background-color: #333444");
        Label createCreditCardLabel = new Label("Enter user name and pin");
        createCreditCardLabel.setStyle("-fx-text-fill: #ffffff;");
        Label creditError = new Label();
        creditError.setStyle("-fx-text-fill: red;");
        TextField createCreditCardField = new TextField();
        createCreditCardField.setPromptText("Name");
        createCreditCardField.setPrefWidth(200);
        createCreditCardField.setMinWidth(200);
        createCreditCardField.setMaxWidth(200);
        TextField pinCreditField = new TextField();
        pinCreditField.setPromptText("Pin");
        pinCreditField.setPrefWidth(200);
        pinCreditField.setMinWidth(200);
        pinCreditField.setMaxWidth(200);
        Button confirmCreateCreditCardButton = new Button("Confirm");
        confirmCreateCreditCardButton.setPrefSize(100, 25);
        Button createCreditCardBackButton = new Button("Back");
        createCreditCardBackButton.setPrefSize(100, 25);
        createCreditCardPane.getChildren().addAll(creditError, createCreditCardLabel, createCreditCardField, pinCreditField, confirmCreateCreditCardButton, createCreditCardBackButton);
        Scene createCreditCardScene = new Scene(createCreditCardPane, 600, 400);

        // Delete card login scene
        VBox deleteCardLoginPane = new VBox(10);
        deleteCardLoginPane.setAlignment(Pos.CENTER);
        deleteCardLoginPane.setStyle("-fx-background-color: #333444");
        Label deleteCardLoginLabel = new Label("Enter admin user name and password");
        deleteCardLoginLabel.setStyle("-fx-text-fill: #ffffff;");
        Label deleteCardLoginError = new Label();
        deleteCardLoginError.setStyle("-fx-text-fill: red;");
        TextField deleteCardLoginField = new TextField();
        deleteCardLoginField.setPromptText("username");
        deleteCardLoginField.setPrefWidth(200);
        deleteCardLoginField.setMinWidth(200);
        deleteCardLoginField.setMaxWidth(200);
        TextField deleteCardLoginPasswordField = new TextField();
        deleteCardLoginPasswordField.setPromptText("password");
        deleteCardLoginPasswordField.setPrefWidth(200);
        deleteCardLoginPasswordField.setMinWidth(200);
        deleteCardLoginPasswordField.setMaxWidth(200);
        Button deleteCardLoginButton = new Button("Login");
        deleteCardLoginButton.setPrefSize(100, 25);
        Button deleteCardLoginBackButton = new Button("Back");
        deleteCardLoginBackButton.setPrefSize(100, 25);
        deleteCardLoginPane.getChildren().addAll(deleteCardLoginError, deleteCardLoginLabel, deleteCardLoginField, deleteCardLoginPasswordField, deleteCardLoginButton, deleteCardLoginBackButton);
        Scene deleteCardLoginScene = new Scene(deleteCardLoginPane, 600, 400);

        // Delete card scene
        VBox deleteCardPane = new VBox(10);
        deleteCardPane.setAlignment(Pos.CENTER);
        deleteCardPane.setStyle("-fx-background-color: #333444");
        Label enterCardNumber = new Label("Enter card number to be deleted");
        enterCardNumber.setStyle("-fx-text-fill: #ffffff;");
        Label deleteCardError = new Label();
        deleteCardError.setStyle("-fx-text-fill: red;");
        TextField deleteCardField = new TextField();
        deleteCardField.setPromptText("card number");
        deleteCardField.setPrefWidth(200);
        deleteCardField.setMinWidth(200);
        deleteCardField.setMaxWidth(200);
        Button deleteCardConfirmButton = new Button("Confirm");
        deleteCardConfirmButton.setPrefSize(100, 25);
        Button deleteCardBackButton = new Button("Back");
        deleteCardBackButton.setPrefSize(100, 25);
        deleteCardPane.getChildren().addAll(deleteCardError, enterCardNumber, deleteCardField, deleteCardConfirmButton, deleteCardBackButton);
        Scene deleteCardScene = new Scene(deleteCardPane, 600, 400);

        // Delete user login scene
        VBox deleteUserLoginPane = new VBox(10);
        deleteUserLoginPane.setAlignment(Pos.CENTER);
        deleteUserLoginPane.setStyle("-fx-background-color: #333444");
        Label deleteUserLoginLabel = new Label("Enter admin user name and password");
        deleteUserLoginLabel.setStyle("-fx-text-fill: #ffffff;");
        Label deleteUserLoginError = new Label();
        deleteUserLoginError.setStyle("-fx-text-fill: red;");
        TextField deleteUserLoginField = new TextField();
        deleteUserLoginField.setPromptText("username");
        deleteUserLoginField.setPrefWidth(200);
        deleteUserLoginField.setMinWidth(200);
        deleteUserLoginField.setMaxWidth(200);
        TextField deleteUserLoginPasswordField = new TextField();
        deleteUserLoginPasswordField.setPromptText("password");
        deleteUserLoginPasswordField.setPrefWidth(200);
        deleteUserLoginPasswordField.setMinWidth(200);
        deleteUserLoginPasswordField.setMaxWidth(200);
        Button deleteUserLoginButton = new Button("Login");
        deleteUserLoginButton.setPrefSize(100, 25);
        Button deleteUserLoginBackButton = new Button("Back");
        deleteUserLoginBackButton.setPrefSize(100, 25);
        deleteUserLoginPane.getChildren().addAll(deleteUserLoginError, deleteUserLoginLabel, deleteUserLoginField, deleteUserLoginPasswordField, deleteUserLoginButton, deleteUserLoginBackButton);
        Scene deleteUserLoginScene = new Scene(deleteUserLoginPane, 600, 400);

        // Delete user scene
        VBox deleteUserPane = new VBox(10);
        deleteUserPane.setAlignment(Pos.CENTER);
        deleteUserPane.setStyle("-fx-background-color: #333444");
        Label enterUserNumber = new Label("Enter user id");
        enterUserNumber.setStyle("-fx-text-fill: #ffffff;");
        Label deleteUserError = new Label();
        deleteUserError.setStyle("-fx-text-fill: red;");
        TextField deleteUserField = new TextField();
        deleteUserField.setPromptText("userId");
        deleteUserField.setPrefWidth(200);
        deleteUserField.setMinWidth(200);
        deleteUserField.setMaxWidth(200);
        Button deleteUserConfirmButton = new Button("Confirm");
        deleteUserConfirmButton.setPrefSize(100, 25);
        Button deleteUserBackButton = new Button("Back");
        deleteUserBackButton.setPrefSize(100, 25);
        deleteUserPane.getChildren().addAll(deleteUserError, enterUserNumber, deleteUserField, deleteUserConfirmButton, deleteUserBackButton);
        Scene deleteUserScene = new Scene(deleteUserPane, 600, 400);

        // Button Actions
        depositButton.setOnAction(e -> stage.setScene(depositLoginScene));
        depositLoginButton.setOnAction(e -> stage.setScene(depositScene));
        withdrawalButton.setOnAction(e -> stage.setScene(withdrawalLoginScene));
        withdrawalLoginButton.setOnAction(e -> stage.setScene(withdrawalScene));
        balanceButton.setOnAction(e -> stage.setScene(balanceLoginScene));
        balanceLoginButton.setOnAction(e -> stage.setScene(balanceScene));
        createUserButton.setOnAction(e -> stage.setScene(createUserLoginScene));
        createUserLoginButton.setOnAction(e -> stage.setScene(createUserScene));
        createCardButton.setOnAction(e -> stage.setScene(createCardTypeScene));
        debitCardButton.setOnAction(e -> stage.setScene(createDebitCardScene));
        creditCardButton.setOnAction(e -> stage.setScene(createCreditCardScene));
        deleteCardButton.setOnAction(e -> stage.setScene(deleteCardLoginScene));
        deleteCardLoginButton.setOnAction(e -> stage.setScene(deleteCardScene));
        deleteUserButton.setOnAction(e -> stage.setScene(deleteUserLoginScene));
        deleteUserLoginButton.setOnAction(e -> stage.setScene(deleteUserScene));
        exitButton.setOnAction(e -> stage.close());

        // Back Buttons
        depositBackButton.setOnAction(e -> stage.setScene(mainScene));
        depositLoginBackButton.setOnAction(e -> stage.setScene(mainScene));
        withdrawalBackButton.setOnAction(e -> stage.setScene(mainScene));
        withdrawalLoginBackButton.setOnAction(e -> stage.setScene(mainScene));
        balanceBackButton.setOnAction(e -> stage.setScene(mainScene));
        balanceLoginBackButton.setOnAction(e -> stage.setScene(mainScene));
        createUserBackButton.setOnAction(e -> stage.setScene(mainScene));
        createUserLoginBackButton.setOnAction(e -> stage.setScene(mainScene));
        createCardTypeBackButton.setOnAction(e -> stage.setScene(mainScene));
        createDebitCardBackButton.setOnAction(e -> stage.setScene(mainScene));
        createCreditCardBackButton.setOnAction(e -> stage.setScene(mainScene));
        deleteCardLoginBackButton.setOnAction(e -> stage.setScene(mainScene));
        deleteCardBackButton.setOnAction(e -> stage.setScene(mainScene));
        deleteUserLoginBackButton.setOnAction(e -> stage.setScene(mainScene));
        deleteUserBackButton.setOnAction(e -> stage.setScene(mainScene));

        // Deposit Login Button
        depositLoginButton.setOnAction(e -> {
            String cardNumber = depositCardNumberField.getText();
            String pin = passwordDepositSceneField.getText();
            try {
                if (atmCase.verifyCardExist(parseLong(cardNumber), parseShort(pin))) {
                    stage.setScene(depositScene);
                } else {
                    depositLoginError.setText("Invalid card number or PIN. Please try again");
                }
            } catch (NumberFormatException ex) {
                depositLoginError.setText("Please enter a valid card number and PIN");
            }
        });

        // Withdrawal login button
        withdrawalLoginButton.setOnAction(e -> {
            String cardNumber = withdrawalCardNumberField.getText();
            String pin = passwordWithdrawalSceneField.getText();
            try {
                if (atmCase.verifyCardExist(parseLong(cardNumber), parseShort(pin))) {
                    stage.setScene(withdrawalScene);
                } else {
                    withdrawalLoginError.setText("Invalid card number or PIN. Please try again");
                }
            } catch (NumberFormatException ex) {
                withdrawalLoginError.setText("Please enter a valid card number and PIN");
            }
        });

        // Balance login button
        balanceLoginButton.setOnAction(e -> {
            String cardNumber = balanceCardNumberField.getText();
            String pin = passwordBalanceSceneField.getText();
            try {
                long cardNumberLong = Long.parseLong(cardNumber);
                short pinShort = Short.parseShort(pin);
                if (atmCase.verifyCardExist(cardNumberLong, pinShort)) {
                    double balance = atmCase.balance(cardNumberLong, pinShort);
                    balanceLabel.setText(String.valueOf(balance));
                    stage.setScene(balanceScene);
                } else {
                    balanceErrorLabel.setText("Invalid card number or PIN. Please try again");
                }
            } catch (NumberFormatException ex) {
                balanceErrorLabel.setText("Please enter a valid card number and PIN");
            }
        });

        // Create user if you admin login button
        createUserLoginButton.setOnAction(e -> {
            String username = createUserUserNameField.getText();
            String password = createUserUserPasswordField.getText();
            try {
                if (atmCase.verifyUserExist(username, password)) {
                    stage.setScene(createUserScene);
                } else {
                    createUserAdminLoginError.setText("Incorrect user name or password");
                }
            } catch (NumberFormatException ex) {
                createUserAdminLoginError.setText("Please enter a valid user name and password");
            }
        });

        // Confirm Deposit Button Action
        confirmDepositButton.setOnAction(e -> {

            try {
                long cardNumber = Long.parseLong(depositCardNumberField.getText());
                short pin = parseShort(passwordDepositSceneField.getText());
                double depositAmount = Double.parseDouble(depositAmountField.getText());
                atmCase.deposit(cardNumber, pin, depositAmount);
                depositError.setText("Deposit successful");
            } catch (NumberFormatException ex) {
                depositError.setText("Please enter a valid amount");
            }
        });

        // Confirm Withdrawal Button Action
        confirmWithdrawalButton.setOnAction(e -> {
            try {
                long cardNumber = Long.parseLong(withdrawalCardNumberField.getText());
                short pin = parseShort(passwordWithdrawalSceneField.getText());
                double withdrawnAmount = Double.parseDouble(withdrawalAmountField.getText());
                if (atmCase.withdrawn(cardNumber, pin, withdrawnAmount)) {
                    withdrawalError.setText("Withdrawal successful");
                } else {
                    withdrawalError.setText("Insufficient founds");
                }
            } catch (NumberFormatException ex) {
                withdrawalError.setText("Please enter a valid amount");
            }
        });

        // Create new user
        confirmCreateUserButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String isAdminInput = isAdmin.isSelected() ? "yes" : "no";
            // Verify if username contains only letters
            try {
                if (!username.matches("[a-zA-Z\\s]+")) {
                    createUserError.setText("Username must contain only letters");
                    return;
                }
                atmCase.createUser(username, password, isAdminInput);
                createUserError.setText("User created successfully");
            } catch (NumberFormatException ex) {
                createUserError.setText("Invalid input. Please enter valid details");
            }
        });

        // Create debit card button
        confirmCreateDebitCardButton.setOnAction(e -> {
            String owner = createDebitCardField.getText();
            String pin = pinDebitField.getText();
            try {
               if (!owner.matches("[a-zA-Z\\s]+")) {
                   debitError.setText("Username must contain only letters");
                   return;
               }
                atmCase.createCard("debit", owner, Short.parseShort(pin));
                debitError.setText("Card created successful");
            } catch (NumberFormatException ex) {
                debitError.setText("Invalid input. Please enter valid details");
            }

        });

        // Create credit card button
        confirmCreateCreditCardButton.setOnAction(e -> {
            String owner = createCreditCardField.getText();
            String pin = pinCreditField.getText();
            try {
                if (!owner.matches("[a-zA-Z\\s]+")) {
                    creditError.setText("Username must contain only letters");
                    return;
                }
                atmCase.createCard("credit", owner, Short.parseShort(pin));
                creditError.setText("Card created successful.");
            } catch (NumberFormatException ex) {
             creditError.setText("Invalid input. Please enter valid details");
            }
        });

        // Delete card login button
        deleteCardLoginButton.setOnAction(e -> {
            String username = deleteCardLoginField.getText();
            String password = deleteCardLoginPasswordField.getText();
            try {
                if (atmCase.verifyUserExist(username, password)) {
                    stage.setScene(deleteCardScene);
                } else {
                    deleteCardLoginError.setText("Incorrect user name or password");
                }
            } catch (NumberFormatException ex) {
                deleteCardLoginError.setText("Please enter a valid user name and password");
            }
        });

        // Delete card
        deleteCardConfirmButton.setOnAction(e -> {
            try {
                long cardNumber = Long.parseLong(deleteCardField.getText());
                if (atmCase.deleteCard(cardNumber)) {
                    deleteCardError.setText("Card deleted successfully");
                } else {
                    deleteCardError.setText("Card number does not exist");
                }
            } catch (NumberFormatException ex) {
                deleteCardError.setText("Invalid input. Please enter a valid card number");
            }
        });

        // Delete user login button
        deleteUserLoginButton.setOnAction(e -> {
            String username = deleteUserLoginField.getText();
            String password = deleteUserLoginPasswordField.getText();
            try {
                if (atmCase.verifyUserExist(username, password)) {
                    stage.setScene(deleteUserScene);
                } else {
                    deleteUserLoginError.setText("Incorrect user name or password");
                }
            } catch (NumberFormatException ex) {
                deleteUserLoginError.setText("Please enter a valid user name and password");
            }
        });

        // Delete users
        deleteUserConfirmButton.setOnAction(e -> {
            try {
                int userId = Integer.parseInt(deleteUserField.getText());
                if (atmCase.deleteUser(userId)) {
                    deleteUserError.setText("User deleted successfully");
                } else {
                    deleteUserError.setText("User ID does not exist");
                }
            } catch (NumberFormatException ex) {
                deleteUserError.setText("Invalid input. Please enter a valid number");
            }
        });

        // Set Main Scene
        stage.setScene(mainScene);
        stage.setTitle("ATM");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
