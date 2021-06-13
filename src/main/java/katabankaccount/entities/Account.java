package katabankaccount.entities;

import katabankaccount.exceptions.BalanceInsufficientException;
import katabankaccount.utilities.DateFormat;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Account {
    private String rib;
    private int balance;
    private final DateFormat dateformat;

    private static List<Transaction> transactions = new ArrayList<>();
    private final Printer printer;


    public Account(String rib,int balance,DateFormat dateformat, Printer printer) {
        this.rib=rib;
        this.balance=balance;
        this.printer = printer;
        this.dateformat = dateformat;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(int amount, LocalDate date){
        if (amount < 0) throw new IllegalArgumentException("amount must be Positive on deposit action");
        else {
            balance = balance + amount;
            Transaction transaction = new Transaction(rib,dateformat.dateToString(date), amount, "deposit", balance);
            transactions.add(transaction);
        }
    }
    public void withdrawal(int amount, LocalDate date) throws BalanceInsufficientException {
        if (amount < 0) throw new IllegalArgumentException("amount must be Positive on withdraw action");
        else if (amount > balance) throw new BalanceInsufficientException("Withdrawal ERROR : insufficient balance");
        else {
            balance = balance - amount;
            Transaction transaction = new Transaction(rib,dateformat.dateToString(date), -amount, "withdrawal", balance);
            transactions.add(transaction);
        }
    }
    public void transfer(int amount,Account to, LocalDate date){
        this.balance = balance - amount;
        to.balance=to.balance +amount;
        Transaction transactionFrom = new Transaction(rib,dateformat.dateToString(date), amount, "TRANSFER TO "+to.getRib(), balance);
        Transaction transactionTo = new Transaction(to.getRib(),dateformat.dateToString(date), amount, "RECEIVE FROM "+this.getRib(),to.balance);
        transactions.add(transactionFrom);
        transactions.add(transactionTo);
    }
    public void printStatement(String account){
        printer.print(transactions,account);
    }
}