package ru.sber.products.model;

import lombok.Getter;

@Getter
public class Deposit extends AccountProduct {
    private boolean isClosed = false;

    public Deposit(String name, String currency, double balance) {
        super(name, currency, balance);
    }

    public void topUp(double amount) {
        if (isClosed) {
            throw new IllegalStateException("Deposit is closed");
        }
        balance += amount;
    }

    public void close() {
        isClosed = true;
    }
}
