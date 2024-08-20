package com.example.bankingsystem.controllers;

import com.example.bankingsystem.models.BankAccount;
import com.example.bankingsystem.models.BankSystem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankController {

    private final BankSystem bankSystem = new BankSystem();

    private BankAccount currentAccount;

    @FXML
    private VBox mainMenu;

    @FXML
    private VBox createAccountMenu;

    @FXML
    private VBox findAccountMenu;

    @FXML
    private VBox accountMenu;

    @FXML
    private VBox transferMenu;

    @FXML
    private Button createAccountButton;

    @FXML
    private Button findAccountButton;

    @FXML
    private Button deleteAccountButton;

    @FXML
    private Button createButton;

    @FXML
    private Button backFromCreateButton;

    @FXML
    private Button findButton;

    @FXML
    private Button backFromTransferButton;

    @FXML
    private Button backFromFindButton;

    @FXML
    private Button depositButton;

    @FXML
    private Button withdrawButton;

    @FXML
    private Button transferButton;

    @FXML
    private Button executeTransferButton;

    @FXML
    private Button findAllButton;

    @FXML
    private Button deleteButton;

    @FXML
    private VBox allAccountsMenu;

    @FXML
    private VBox deleteAccountMenu;

    @FXML
    private Button backFromAllAccountsButton;

    @FXML
    private Button backFromAccountButton;

    @FXML
    private Button backFromDeleteButton;

    @FXML
    private TextField accountIdField;

    @FXML
    private TextField amountField;

    @FXML
    private TextField accountIdLabel;

    @FXML
    private TextField deleteAccountIdField;

    @FXML
    private Label accountBalanceLabel;

    @FXML
    private Label newAccountIdLabel;

    @FXML
    private TableView<BankAccount> accountsTable;

    @FXML
    private TableColumn<BankAccount, String> accountIdColumn;

    @FXML
    private TableColumn<BankAccount, BigDecimal> balanceColumn;

    @FXML
    private ComboBox<String> transferAccountComboBox;

    @FXML
    public void initialize() {
        createAccountButton.setOnAction(event -> showCreateAccountMenu());
        findAccountButton.setOnAction(event -> showFindAccountMenu());
        deleteAccountButton.setOnAction(event -> showDeleteMenu());
        findAllButton.setOnAction(event -> showAllAccountsMenu());
        deleteButton.setOnAction(event -> deleteAccount());

        createButton.setOnAction(event -> createAccount());
        backFromCreateButton.setOnAction(event -> showMainMenu());

        findButton.setOnAction(event -> findAccount());
        backFromFindButton.setOnAction(event -> showMainMenu());

        depositButton.setOnAction(event -> deposit());
        withdrawButton.setOnAction(event -> withdraw());
        transferButton.setOnAction(event -> showTransferMenu());
        executeTransferButton.setOnAction(event -> transfer());
        backFromAccountButton.setOnAction(event -> showMainMenu());

        backFromAllAccountsButton.setOnAction(event -> showMainMenu());
        backFromTransferButton.setOnAction(event -> showAccountMenu(currentAccount));
        backFromDeleteButton.setOnAction(event -> showMainMenu());

        accountIdColumn.setCellValueFactory(new PropertyValueFactory<>("accountId"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
    }

    private void showMainMenu() {
        mainMenu.setVisible(true);
        createAccountMenu.setVisible(false);
        findAccountMenu.setVisible(false);
        accountMenu.setVisible(false);
        transferMenu.setVisible(false);
        allAccountsMenu.setVisible(false);
        deleteAccountMenu.setVisible(false);
        currentAccount = null;
    }

    private void showDeleteMenu() {
        mainMenu.setVisible(false);
        createAccountMenu.setVisible(false);
        findAccountMenu.setVisible(false);
        accountMenu.setVisible(false);
        allAccountsMenu.setVisible(false);
        transferMenu.setVisible(false);
        deleteAccountMenu.setVisible(true);
    }

    private void showCreateAccountMenu() {
        mainMenu.setVisible(false);
        createAccountMenu.setVisible(true);
        findAccountMenu.setVisible(false);
        accountMenu.setVisible(false);
        allAccountsMenu.setVisible(false);
        transferMenu.setVisible(false);
        deleteAccountMenu.setVisible(false);
        newAccountIdLabel.setText("");
    }

    private void showFindAccountMenu() {
        mainMenu.setVisible(false);
        createAccountMenu.setVisible(false);
        findAccountMenu.setVisible(true);
        transferMenu.setVisible(false);
        accountMenu.setVisible(false);
        deleteAccountMenu.setVisible(false);
        allAccountsMenu.setVisible(false);
    }

    private void showAccountMenu(BankAccount account) {
        currentAccount = account;
        accountIdLabel.setText("Account ID: " + account.getAccountId());
        accountBalanceLabel.setText("Balance: " + account.getBalance().info());
        mainMenu.setVisible(false);
        createAccountMenu.setVisible(false);
        findAccountMenu.setVisible(false);
        transferMenu.setVisible(false);
        accountMenu.setVisible(true);
        deleteAccountMenu.setVisible(false);
        allAccountsMenu.setVisible(false);
    }

    private void showTransferMenu() {
        ObservableList<String> accountIds = FXCollections.observableArrayList();
        for (BankAccount account : bankSystem.findAll()) {
            if (!account.getAccountId().equals(accountIdLabel.getText().replace("Account ID: ", ""))) {
                accountIds.add(account.getAccountId());
            }
        }
        transferAccountComboBox.setItems(accountIds);

        mainMenu.setVisible(false);
        createAccountMenu.setVisible(false);
        findAccountMenu.setVisible(false);
        accountMenu.setVisible(false);
        transferMenu.setVisible(true);
        deleteAccountMenu.setVisible(false);
        allAccountsMenu.setVisible(false);
    }


    private void showAllAccountsMenu() {
        ObservableList<BankAccount> accountsList = FXCollections.observableArrayList(bankSystem.findAll());
        accountsTable.setItems(accountsList);

        mainMenu.setVisible(false);
        createAccountMenu.setVisible(false);
        findAccountMenu.setVisible(false);
        accountMenu.setVisible(false);
        transferMenu.setVisible(false);
        deleteAccountMenu.setVisible(false);
        allAccountsMenu.setVisible(true);
    }

    private void refreshAllAccountsTable() {
        List<BankAccount> refreshedObjectList = new ArrayList<>();
        for (BankAccount bankAccount : bankSystem.findAll()) {
            BankAccount updatedBankAccountObject = new BankAccount(bankAccount.getAccountId());
            updatedBankAccountObject.setBalance(bankAccount.getBalance());
        }
        ObservableList<BankAccount> accounts = FXCollections.observableArrayList(refreshedObjectList);

        accountsTable.setItems(accounts);
    }

    private void createAccount() {
        BankAccount account = bankSystem.createBankAccount();
        newAccountIdLabel.setText(account.getAccountId());
        showAccountMenu(account);
    }

    private void findAccount() {
        String accountId = accountIdField.getText();
        BankAccount account = bankSystem.findByAccountId(accountId);
        showAccountMenu(account);
    }

    private void deposit() {
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(amountField.getText()));
        accountBalanceLabel.setText("Balance: " + currentAccount.deposit(amount));
        amountField.clear();
        refreshAllAccountsTable();
    }

    private void withdraw() {
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(amountField.getText()));
        accountBalanceLabel.setText("Balance: " + currentAccount.withdraw(amount));
        amountField.clear();
        refreshAllAccountsTable();
    }

    private void transfer() {
        String transferAccountId = transferAccountComboBox.getValue();
        BigDecimal transferAmount = BigDecimal.valueOf(Double.parseDouble(amountField.getText()));
        BankAccount targetAccount = bankSystem.findByAccountId(transferAccountId);
        currentAccount.transfer(targetAccount, transferAmount);
        showAccountMenu(currentAccount);
        amountField.clear();
        refreshAllAccountsTable();
    }

    private void deleteAccount() {
        String accountId = deleteAccountIdField.getText().replace("Account ID: ", "");
        bankSystem.deleteAccountByAccountId(accountId);
        showMainMenu();
        refreshAllAccountsTable();
    }
}
