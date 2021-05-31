package katabankaccount.entities;

public class Transaction {
    private final String date;
    private final int amount;
    private final String operationType;
    private final int currentBalance;

    public Transaction(String date, int amount, String operationType, int currentBalance) {
        this.date = date;
        this.amount = amount;
        this.operationType = operationType;
        this.currentBalance = currentBalance;
    }
}
