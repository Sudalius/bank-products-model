package ru.sber.products.builders;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BankProductBuilder {
    private String name;
    private String currency;
    private double balance;
    private double interestRate;
    private ProductType type;

    public enum ProductType {
        DEBIT_CARD,
        CURRENCY_DEBIT_CARD,
        CREDIT_CARD,
        DEPOSIT
    }
}
