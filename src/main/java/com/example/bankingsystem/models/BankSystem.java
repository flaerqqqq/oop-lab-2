package com.example.bankingsystem.models;

import java.util.*;

/**
 * Manages a collection of bank accounts, allowing for account creation, lookup, and deletion.
 */
public class BankSystem {

    private final Map<String, BankAccount> bankAccounts = new HashMap<>();

    /**
     * Creates a new bank account with a unique ID and adds it to the system.
     *
     * @return the newly created bank account.
     */
    public BankAccount createBankAccount() {
         String uuid = UUID.randomUUID().toString();
         BankAccount bankAccount = new BankAccount(uuid);
         bankAccounts.put(uuid, bankAccount);

         return bankAccount;
    }

    /**
     * Finds and returns a bank account by its ID.
     *
     * @param accountId the ID of the bank account to find.
     * @return the bank account with the specified ID.
     * @throws RuntimeException if the account ID is not found.
     */
    public BankAccount findByAccountId(String accountId) {
        if (!bankAccounts.containsKey(accountId)) {
            throw new RuntimeException("Bank Account with such accountId is not found: %s".formatted(accountId));
        }
        return bankAccounts.get(accountId);
    }

    /**
     * Deletes a bank account from the system by its ID.
     *
     * @param accountId the ID of the bank account to delete.
     * @throws RuntimeException if the account ID is not found.
     */
    public void deleteAccountByAccountId(String accountId) {
        if (!bankAccounts.containsKey(accountId)) {
            throw new RuntimeException("Bank Account with such accountId is not found: %s".formatted(accountId));
        }
        bankAccounts.remove(accountId);
    }

    /**
     * Returns a list of all bank accounts in the system.
     *
     * @return a list of all bank accounts.
     */
    public List<BankAccount> findAll() {
        return bankAccounts.values().stream().toList();
    }
}
