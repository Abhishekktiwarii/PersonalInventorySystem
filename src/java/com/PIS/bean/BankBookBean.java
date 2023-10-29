package com.PIS.bean;

public class BankBookBean {
    private int acid;
    private double amount;
    private int userid;
    private String operation;
    private String account;
    private String transaction_date;

    public BankBookBean() {
    }

    public BankBookBean(int acid, double amount, int userid, String operation, String account, String transaction_date) {
        this.acid = acid;
        this.amount = amount;
        this.userid = userid;
        this.operation = operation;
        this.account = account;
        this.transaction_date = transaction_date;
    }

    public int getAcid() {
        return acid;
    }

    public void setAcid(int acid) {
        this.acid = acid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    
}
