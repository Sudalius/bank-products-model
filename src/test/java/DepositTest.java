import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import ru.sber.products.builders.BankProductBuilder;
import ru.sber.products.builders.BankProductFactory;
import ru.sber.products.model.Deposit;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class DepositTest {

    @Test
    void testTopUpAndBalance() {
        Deposit deposit = (Deposit) BankProductFactory.createProduct(
                BankProductBuilder.builder()
                        .name("Savings")
                        .currency("EUR")
                        .balance(1000)
                        .type(BankProductBuilder.ProductType.DEPOSIT)
                        .build()
        );

        deposit.topUp(500);
        log.info("Top-up deposit: balance = {}", deposit.getBalance());
        assertEquals(1500, deposit.getBalance());
    }

    @Test
    void testCloseDeposit() {
        Deposit deposit = (Deposit) BankProductFactory.createProduct(
                BankProductBuilder.builder()
                        .name("Term Deposit")
                        .currency("EUR")
                        .balance(3000)
                        .type(BankProductBuilder.ProductType.DEPOSIT)
                        .build()
        );

        deposit.close();
        log.info("Deposit closed: isClosed = {}", deposit.isClosed());
        assertTrue(deposit.isClosed());
    }

    @Test
    void testTopUpAfterCloseThrowsException() {
        Deposit deposit = (Deposit) BankProductFactory.createProduct(
                BankProductBuilder.builder()
                        .name("Closed Deposit")
                        .currency("EUR")
                        .balance(1000)
                        .type(BankProductBuilder.ProductType.DEPOSIT)
                        .build()
        );

        deposit.close();
        log.info("Trying to top-up after deposit is closed...");
        assertThrows(IllegalStateException.class, () -> deposit.topUp(200));
    }
}