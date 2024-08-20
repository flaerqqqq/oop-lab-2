package com.example.bankingsystem.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Represents a bank account with a unique account ID and a balance.
 */
@Getter
@Setter
public class BankAccount {

     private final String accountId;
     private Balance balance;

     /**
      * Constructs a BankAccount object with a specified account ID and initializes the balance to zero.
      *
      * @param accountId the unique ID of the bank account.
      */
     public BankAccount(String accountId) {
          this.accountId = accountId;
          balance = new Balance();
     }

     /**
      * Deposits a specified amount into the bank account.
      *
      * @param amount the amount to deposit. Must be positive.
      * @return the updated balance after the deposit.
      */
     public BigDecimal deposit(BigDecimal amount) {
          balance.add(amount);
          return balance.info();

     }

     /**
      * Withdraws a specified amount from the bank account.
      *
      * @param amount the amount to withdraw. Must be positive and less than or equal to the current balance.
      * @return the updated balance after the withdrawal.
      */
     public BigDecimal withdraw(BigDecimal amount) {
          balance.take(amount);
          return balance.info();
     }

     /**
      * Transfers a specified amount from this account to another bank account.
      *
      * @param transferTo the bank account to transfer to.
      * @param amount the amount to transfer. Must be positive and less than or equal to the current balance.
      * @return the updated balance after the transfer.
      * @throws RuntimeException if the transfer is to the same account or if there is an issue during the transfer process.
      */
     public BigDecimal transfer(BankAccount transferTo, BigDecimal amount) {
          if (transferTo.getAccountId() == accountId) {
               throw new RuntimeException("You cannot transfer to the same account");
          }

          if (balance.info().compareTo(amount) < 0) {
               throw new RuntimeException("The actual balance is less than the amount that is going to be transferred");
          }

          BigDecimal originalBalance = balance.info();
          BigDecimal transferToOriginalBalance = transferTo.getBalance().info();

          try {
               withdraw(amount);
               transferTo.deposit(amount);
          } catch (Exception e) {
               balance = new Balance(originalBalance);
               transferTo.setBalance(new Balance(transferToOriginalBalance));
               throw new RuntimeException(e);
          }

          return balance.info();
     }
}
