package com.example.bankingsystem.models;

import java.math.BigDecimal;

/**
 * Represents the balance of a bank account, ensuring that the balance
 * cannot be negative and can only be modified through specific methods.
 */
public class Balance {

    private BigDecimal balance;

    /**
     * Default constructor that initializes the balance to zero.
     */
    public Balance() {
        balance = BigDecimal.ZERO;
    }

    /**
     * Constructs a Balance object with an initial balance.
     *
     * @param balance the initial balance value. Must be positive.
     * @throws RuntimeException if the initial balance is not positive.
     */
    public Balance(BigDecimal balance) {
        throwIfNotPositive(balance);
        this.balance = balance;
    }

    /**
     * Adds a specified amount to the current balance.
     *
     * @param amount the amount to be added. Must be positive.
     * @throws RuntimeException if the amount is not positive.
     */
    public void add(BigDecimal amount) {
        throwIfNotPositive(amount);
        balance = balance.add(amount);
    }

    /**
     * Subtracts a specified amount from the current balance.
     *
     * @param amount the amount to be subtracted. Must be positive and less than or equal to the current balance.
     * @throws RuntimeException if the amount is not positive or greater than the current balance.
     */
    public void take(BigDecimal amount) {
        if (amount.compareTo(balance) > 0) {
            throw new RuntimeException("Withdrawal amount cannot be less than the actual balance");
        }
        throwIfNotPositive(amount);
        balance = balance.subtract(amount);
    }

    /**
     * Returns a different object of type BigDecimal with the same value as an actual balance object
     * to prevent changing an object directly without class methods.
     *
     * @return a copy of the current balance.
     */
    public BigDecimal info() {
        return BigDecimal.valueOf(balance.doubleValue());
    }

    /**
     * Returns the string representation of the balance.
     *
     * @return the balance as a string.
     */
    @Override
    public String toString(){
        return info().toString();
    }

    /**
     * Validates that the specified amount is positive.
     *
     * @param amount the amount to be validated.
     * @throws RuntimeException if the amount is not positive.
     */
    private void throwIfNotPositive(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Deposit amount cannot be negative");
        }
    }
}
