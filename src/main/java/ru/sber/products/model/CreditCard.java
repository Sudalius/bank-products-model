package ru.sber.products.model;

import lombok.Getter;

import lombok.Getter;

@Getter
public class CreditCard extends Card {
    private final double interestRate;
    private double debt;

    public CreditCard(String name, String currency, double balance, double interestRate) {
        super(name, currency, balance);
        this.interestRate = interestRate;
        this.debt = 0;
    }

    @Override
    public void withdraw(double amount) {
        debt += amount;
        balance -= amount;
    }

    public double getDebt() {
        return debt + debt * interestRate;
    }
}
