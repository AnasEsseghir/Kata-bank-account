package katabankaccount;

import katabankaccount.entities.Account;
import katabankaccount.entities.Printer;
import katabankaccount.entities.Transaction;
import katabankaccount.utilities.DateFormat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
    private Account account;
    private DateFormat dateformat;
    @Mock
    private Printer printer;
    @Before
    public void setUp(){
        dateformat = new DateFormat();
        account = new Account(dateformat, printer);
    }

    @Test
    public void deposit_success() {
        LocalDate date_deposit = LocalDate.of(2020, 6, 29);
        account.setBalance(500);
        account.deposit(50, date_deposit);
        List<Transaction> transactions = account.getTransactions();
        assertEquals(transactions.size(), 1);
        assertEquals(transactions.get(0), (new Transaction(dateformat.dateToString(date_deposit), 50, "deposit", 550)));
    }

    @Test
    public void withdrawal() {
        account.setBalance(500);
        LocalDate date_withdrawal = LocalDate.of(2020, 6, 24);
        account.withdrawal(50, date_withdrawal);
        List<Transaction> transactions = account.getTransactions();
        assertEquals(transactions.size(), 1);
        assertEquals(transactions.get(0), (new Transaction(dateformat.dateToString(date_withdrawal), -50, "withdrawal", 450)));
    }

    @Test
    public void printStatement() {
        account.setBalance(500);
        account.printStatement();
        verify(printer).print(account.getTransactions());
    }
}