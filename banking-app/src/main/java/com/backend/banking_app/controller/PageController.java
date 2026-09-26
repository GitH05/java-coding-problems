package com.backend.banking_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // All Accounts
    @GetMapping("/")
    public String allAccounts() {
        return "allAccounts";
    }

    // Create Account
    @GetMapping("/accounts/create")
    public String createAccount() {
        return "createAccount";
    }

    // Get Account
    @GetMapping("/accounts/search")
    public String getAccount() {
        return "getAccount";
    }

    // Deposit
    @GetMapping("/accounts/deposit")
    public String deposit() {
        return "deposit";
    }

    // Withdraw
    @GetMapping("/accounts/withdraw")
    public String withdraw() {
        return "withdraw";
    }

    // Delete Account
    @GetMapping("/accounts/delete")
    public String deleteAccount() {
        return "deleteAccount";
    }
}