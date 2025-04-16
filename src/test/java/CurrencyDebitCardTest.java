import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import ru.sber.products.builders.BankProductBuilder;
import ru.sber.products.builders.BankProductFactory;
import ru.sber.products.model.CurrencyDebitCard;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class CurrencyDebitCardTest {

    @Test
    void testTopUp() {
        CurrencyDebitCard card = (CurrencyDebitCard) BankProductFactory.createProduct(
                BankProductBuilder.builder()
                        .name("Travel Card")
                        .currency("USD")
                        .balance(100.0)
                        .type(BankProductBuilder.ProductType.CURRENCY_DEBIT_CARD)
                        .build()
        );

        card.topUp(50.0);
        log.info("Top-up complete. New balance: {}", card.getBalance());
        assertEquals(150.0, card.getBalance());
    }

    @Test
    void testWithdraw() {
        CurrencyDebitCard card = (CurrencyDebitCard) BankProductFactory.createProduct(
                BankProductBuilder.builder()
                        .name("USD Card")
                        .currency("USD")
                        .balance(200.0)
                        .type(BankProductBuilder.ProductType.CURRENCY_DEBIT_CARD)
                        .build()
        );

        card.withdraw(75.0);
        log.info("Withdrawal complete. New balance: {}", card.getBalance());
        assertEquals(125.0, card.getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        CurrencyDebitCard card = (CurrencyDebitCard) BankProductFactory.createProduct(
                BankProductBuilder.builder()
                        .name("Low Balance Card")
                        .currency("USD")
                        .balance(30.0)
                        .type(BankProductBuilder.ProductType.CURRENCY_DEBIT_CARD)
                        .build()
        );

        log.info("Attempting to withdraw more than balance...");
        assertThrows(IllegalArgumentException.class, () -> card.withdraw(100.0));
    }
}
