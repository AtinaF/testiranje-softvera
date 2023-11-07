package bank;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class BankAccountTest {

    BankAccount bankAccount;

    @BeforeEach
    void setup() {
        bankAccount = new BankAccount(100);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 500, -70})
    void testDeposit(double amount) {
        Assumptions.assumeFalse(amount < 0, "The assumption failed for " + amount);
        bankAccount.deposit(amount);
        Assertions.assertEquals(100+amount, bankAccount.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -100})
    void testDepositWithIllegalArguments(double amount) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(amount),
                "Can not deposite negative amount. ");
    }

    @ParameterizedTest
    @ValueSource(doubles = {100, 30, -70, 300})
    void testWithdraw(double amount) {
        Assumptions.assumeFalse(amount>bankAccount.getBalance(), "The assumption failed for " + amount);
        Assumptions.assumeFalse(amount<0, "The assumption failed for " + amount);
        bankAccount.withdraw(amount);
        Assertions.assertEquals(100.0-amount, bankAccount.getBalance(), "this should work already");
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10, 999})
    void testWithdrawWithIllegalArguments(double amount) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(amount));

    }
}
