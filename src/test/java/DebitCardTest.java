import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import ru.sber.products.builders.BankProductBuilder;
import ru.sber.products.builders.BankProductFactory;
import ru.sber.products.model.DebitCard;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class DebitCardTest {

    @Test
    void testTopUp() {
        DebitCard card = (DebitCard) BankProductFactory.createProduct(
                BankProductBuilder.builder()
                        .name("Main Debit")
                        .currency("RUB")
                        .balance(1000)
                        .type(BankProductBuilder.ProductType.DEBIT_CARD)
                        .build()
        );

        card.topUp(500);
        log.info("Top-up: new balance = {}", card.getBalance());
        assertEquals(1500, card.getBalance());
    }

    @Test
    void testWithdraw() {
        DebitCard card = (DebitCard) BankProductFactory.createProduct(
                BankProductBuilder.builder()
                        .name("Daily Card")
                        .currency("RUB")
                        .balance(500)
                        .type(BankProductBuilder.ProductType.DEBIT_CARD)
                        .build()
        );

        card.withdraw(200);
        log.info("Withdraw: new balance = {}", card.getBalance());
        assertEquals(300, card.getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        DebitCard card = (DebitCard) BankProductFactory.createProduct(
                BankProductBuilder.builder()
                        .name("Limited Card")
                        .currency("RUB")
                        .balance(100)
                        .type(BankProductBuilder.ProductType.DEBIT_CARD)
                        .build()
        );

        log.info("Trying to withdraw more than available balance");
        assertThrows(IllegalArgumentException.class, () -> card.withdraw(200));
    }
}
