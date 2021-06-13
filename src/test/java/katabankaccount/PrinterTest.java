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
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrinterTest {
    Account account;
    Account account2;

    @Mock
    Console console;
    @Before
    public void prepare_data() {

        DateFormat dateformat = new DateFormat();
        Printer printer = new Printer(console);
        account = new Account("1234",2000,dateformat, printer);
        account2 = new Account("4321",1000,dateformat, printer);

    }


    @Test
    public void verify_print_format() throws BalanceInsufficientException {
        LocalDate date_deposit1 = LocalDate.of(2021, 5, 31);
        LocalDate date_withdrawal = LocalDate.of(2021, 5, 29);
        LocalDate date_deposit2 = LocalDate.of(2021, 5, 14);
        LocalDate date_transfer = LocalDate.of(2021, 5, 11);
        LocalDate date_receive = LocalDate.of(2021, 5, 10);

        account.deposit(100, date_deposit1);
        account.withdrawal(100, date_withdrawal);
        account.deposit(100, date_deposit2);
        account.transfer(100,account2,date_transfer);
        account2.transfer(100,account,date_receive);
        account.printStatement(account.getRib());
        verify(console).printLine("deposit | 31/05/2021 | 100 | 2100");
        verify(console).printLine("withdrawal | 29/05/2021 | -100 | 2000");
        verify(console).printLine("deposit | 14/05/2021 | 100 | 2100");
        verify(console).printLine("TRANSFER TO 4321 | 11/05/2021 | 100 | 2000");
        verify(console).printLine("RECEIVE FROM 4321 | 10/05/2021 | 100 | 2100");


    }

}