package katabankaccount;

import katabankaccount.entities.Account;
import katabankaccount.entities.Printer;
import katabankaccount.exceptions.BalanceInsufficientException;
import katabankaccount.utilities.Console;
import katabankaccount.utilities.DateFormat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrinterTest {
    Account account;

    @Mock
    Console console;
    @Before
    public void prepare_data() {

        DateFormat dateformat = new DateFormat();
        Printer printer = new Printer(console);
        account = new Account(dateformat, printer);
    }


    @Test
    public void verify_print_format() throws BalanceInsufficientException {
        LocalDate date_deposit1 = LocalDate.of(2021, 5, 31);
        LocalDate date_withdrawal = LocalDate.of(2021, 5, 29);
        LocalDate date_deposit2 = LocalDate.of(2021, 5, 14);
        account.deposit(2800, date_deposit2);
        account.withdrawal(800, date_withdrawal);
        account.deposit(2000, date_deposit1);
        account.printStatement();
        verify(console).printLine("Operation | Date | Amount | Balance ");
        verify(console).printLine("deposit | 31/05/2021 | 2000 | 4000");
        verify(console).printLine("withdrawal | 29/05/2021 | -800 | 2000");
        verify(console).printLine("deposit | 14/05/2021 | 2800 | 2800");
    }

}