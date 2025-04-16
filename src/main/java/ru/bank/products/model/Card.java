package ru.bank.products.model;

public abstract class Card extends AccountProduct {

    protected Card(String name, String currency, double balance) {
        super(name, currency, balance);
    }

    public void topUp(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
    }
}
