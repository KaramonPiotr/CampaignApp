package com.example.backend.controller;


import com.example.backend.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@CrossOrigin("http://localhost:3000/")
public class AccountController {

    @Autowired
    private Account account;

    @GetMapping("/balance")
    public double getAccountBalance() {
        return account.getBalance();
    }
}