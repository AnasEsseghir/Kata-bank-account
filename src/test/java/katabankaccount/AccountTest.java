package katabankaccount;

import katabankaccount.entities.Account;
import katabankaccount.entities.Printer;
import katabankaccount.entities.Transaction;
import katabankaccount.exceptions.BalanceInsufficientException;
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
    private Account account2;
    private DateFormat dateformat;
    @Mock
    private Printer printer;
    @Before
    public void setUp(){
        dateformat = new DateFormat();
        account = new Account("1234",500,dateformat, printer);
        account2 = new Account("4321",400,dateformat, printer);

    }

    @Test
    public void deposit_success() {
        LocalDate date_deposit = LocalDate.of(2020, 6, 29);
        account.deposit(50, date_deposit);
        List<Transaction> transactions = account.getTransactions();
        assertEquals(transactions.size(), 1);
        assertEquals(transactions.get(0), (new Transaction(account.getRib(), dateformat.dateToString(date_deposit), 50, "deposit", 550)));
    }

    @Test
    public void withdrawal() throws BalanceInsufficientException {
        LocalDate date_withdrawal = LocalDate.of(2020, 6, 24);
        account.withdrawal(50, date_withdrawal);
        List<Transaction> transactions = account.getTransactions();
        assertEquals(transactions.size(), 1);
        assertEquals(transactions.get(0), (new Transaction(account.getRib(),dateformat.dateToString(date_withdrawal), -50, "withdrawal", 450)));
    }
    @Test
    public void transfer(){
        LocalDate date_transfer= LocalDate.of(2020, 6, 24);
        account.transfer(50,account2,date_transfer);
        List<Transaction> transactions = account.getTransactions();
        assertEquals(transactions.size(), 2);
        assertEquals(transactions.get(0), (new Transaction(account.getRib(),dateformat.dateToString(date_transfer), 50, "TRANSFER TO "+account2.getRib(), 450)));
        assertEquals(transactions.get(1), (new Transaction(account2.getRib(),dateformat.dateToString(date_transfer), 50, "RECEIVE FROM "+ account.getRib(), 450)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deposit_account_negative_amount() {
        account.setBalance(500);
        LocalDate date_withdrawal = LocalDate.of(2020, 6, 24);
        account.deposit(-500, date_withdrawal);

    }

    @Test(expected = IllegalArgumentException.class)
    public void withdraw_account_negative_amount() throws BalanceInsufficientException {
        account.setBalance(500);
        LocalDate date_withdrawal = LocalDate.of(2020, 6, 24);
        account.withdrawal(-600, date_withdrawal);
    }
    @Test
    public void printStatement() {
        account.setBalance(500);
        account.printStatement(account.getRib());
        verify(printer).print(account.getTransactions(),account.getRib());
    }
}