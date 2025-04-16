import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import ru.bank.products.builders.BankProductBuilder;
import ru.bank.products.builders.BankProductFactory;
import ru.bank.products.model.CreditCard;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class CreditCardTest {

    @Test
    void testWithdrawAndDebtCalculation() {
        CreditCard card = (CreditCard) BankProductFactory.createProduct(
                BankProductBuilder.builder()
                        .name("Visa Credit")
                        .currency("USD")
                        .balance(0)
                        .interestRate(0.1)
                        .type(BankProductBuilder.ProductType.CREDIT_CARD)
                        .build()
        );

        card.withdraw(1000);
        log.info("Withdrawn: debt = {}, balance = {}", card.getDebt(), card.getBalance());
        assertEquals(-1000, card.getBalance());
        assertEquals(1100, card.getDebt()); // 1000 + 10% interest
    }

    @Test
    void testMultipleWithdrawals() {
        CreditCard card = (CreditCard) BankProductFactory.createProduct(
                BankProductBuilder.builder()
                        .name("MasterCard Credit")
                        .currency("USD")
                        .balance(0)
                        .interestRate(0.2)
                        .type(BankProductBuilder.ProductType.CREDIT_CARD)
                        .build()
        );

        card.withdraw(500);
        card.withdraw(250);
        log.info("Debt after 2 withdrawals = {}", card.getDebt());
        assertEquals(-750, card.getBalance());
        assertEquals(900, card.getDebt()); // (500 + 250) + 20%
    }
}
