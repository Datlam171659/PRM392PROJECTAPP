package com.example.prm392project.model;

public class PaymentModel {
    private String bankName;
    private String accountNumber;
    private String accountHolder;
    private String amount;
    private String content;

    public PaymentModel(String bankName, String accountNumber, String accountHolder, String amount, String content) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.amount = amount;
        this.content = content;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAmount() {
        return amount;
    }

    public String getContent() {
        return content;
    }
}
