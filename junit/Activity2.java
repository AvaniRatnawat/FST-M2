package activities;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Activity2 {

    @Test
    public void notEnoughFunds(){
        BankAccount account = new BankAccount(9);

        //Assertion for exception
        assertThrows(NotEnoughFundsException.class, () -> account.withdraw(10),"Balance must be greater than amount of withdrawal");
    }

    @Test
    public void enoughFunds(){
        BankAccount account = new BankAccount(100);

        //Assertion for no exception
        assertDoesNotThrow(() -> account.withdraw(100));

    }

}
