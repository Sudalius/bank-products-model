package ru.sber.products.builders;

import ru.sber.products.model.*;

public class BankProductFactory {

    public static BankProduct createProduct(BankProductBuilder builder) {
        return switch (builder.getType()) {
            case DEBIT_CARD -> new DebitCard(builder.getName(), builder.getCurrency(), builder.getBalance());
            case CURRENCY_DEBIT_CARD -> new CurrencyDebitCard(builder.getName(), builder.getCurrency(), builder.getBalance());
            case CREDIT_CARD -> new CreditCard(builder.getName(), builder.getCurrency(), builder.getBalance(), builder.getInterestRate());
            case DEPOSIT -> new Deposit(builder.getName(), builder.getCurrency(), builder.getBalance());
        };
    }
}
