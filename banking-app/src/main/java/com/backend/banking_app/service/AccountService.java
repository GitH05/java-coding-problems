package com.backend.banking_app.service;

import java.util.List;

import com.backend.banking_app.dto.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long accountId);

    AccountDto deposit(Long accountId, Double balance);

    AccountDto withdraw(Long accountId, Double balance);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long accountId);
}