package katabankaccount;

import katabankaccount.entities.Account;
import katabankaccount.utilities.Console;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class KataBankAccountApplicationTest {
    Account account;
    Console console;
    @Test
    public void verify_print_format() {

        account.deposit();
        account.withdrawal();
        account.deposit();
        account.printStatement();
        verify(console).printLine("Operation | Date | Amount | Balance ");
        verify(console).printLine("deposit | 31/05/2021 | 2000 | 4000");
        verify(console).printLine("withdrawal | 29/05/2021 | -800 | 2000");
        verify(console).printLine("deposit | 14/05/2021 | 2800 | 2800");
    }

}