package ru.sber.products.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AccountProduct implements BankProduct {
    protected final String name;
    protected final String currency;
    protected double balance;
}
