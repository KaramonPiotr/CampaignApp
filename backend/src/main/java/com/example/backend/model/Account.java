package com.example.backend.model;

import org.springframework.stereotype.Component;

@Component
public class Account {
    private static Account instance;
    private double balance;

    private Account() {
        this.balance = 10000.0;
    }

    public static Account getInstance() {
        if (instance == null) {
            instance = new Account();
        }
        return instance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
