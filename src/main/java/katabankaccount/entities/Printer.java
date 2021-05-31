package katabankaccount.entities;


import katabankaccount.utilities.Console;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Printer {

    private static final String HEADER = "Operation | Date | Amount | Balance ";

    private final Console console;

    public Printer(Console console) {
        this.console = console;
    }
    public void print(List<Transaction> transactions) {
        console.printLine(HEADER);
        transactions.stream().map(this::dataLine)
                .collect(Collectors.toCollection(LinkedList::new)).descendingIterator()
                .forEachRemaining(console::printLine);

    }
    private String dataLine(Transaction transaction) {

        return transaction.getOperationType() + " | " + transaction.getDate() + " | "
                + transaction.getAmount() + " | "
                + transaction.getCurrentBalance();
    }
}
