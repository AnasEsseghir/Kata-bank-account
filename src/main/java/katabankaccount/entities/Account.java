package katabankaccount.entities;

import katabankaccount.utilities.DateFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private int balance;
    private final DateFormat dateformat;

    private final List<Transaction> transactions = new ArrayList<>();
    private final Printer printer;


    public Account(DateFormat dateformat, Printer printer) {
        this.printer = printer;
        this.dateformat = dateformat;
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

    public void deposit(int amount, LocalDate date){}
    public void withdrawal(int amount, LocalDate date){}
    public void printStatement(){}
}
