package katabankaccount.entities;

import java.util.Objects;

public class Transaction {
    private final String account;
    private final String date;
    private final int amount;
    private final String operationType;
    private final int currentBalance;

    public Transaction(String account, String date, int amount, String operationType, int currentBalance) {
        this.account = account;
        this.date = date;
        this.amount = amount;
        this.operationType = operationType;
        this.currentBalance = currentBalance;
    }

    public String getAccount() {
        return account;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public String getOperationType() {
        return operationType;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return amount == that.amount &&
                currentBalance == that.currentBalance &&
                Objects.equals(account, that.account) &&
                Objects.equals(date, that.date) &&
                Objects.equals(operationType, that.operationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, date, amount, operationType, currentBalance);
    }

}