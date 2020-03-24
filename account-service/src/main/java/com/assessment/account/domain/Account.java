package com.assessment.account.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name= "ACCOUNTS")
public class Account {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "USERID")
    private long userId;

    @Column(name = "ACCOUNTNUMBER")
    private long accountNumber;

    @Column(name = "ACCOUNTTYPE")
    private String accountType;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    public Account(long userId, long accountNumber, String accountType) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }

    public Account(long accountNumber, String accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }

    protected Account(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
