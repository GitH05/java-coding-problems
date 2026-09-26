package com.backend.banking_app.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.banking_app.dto.AccountDto;
import com.backend.banking_app.entity.Account;
import com.backend.banking_app.exception.AccountNotFoundException;
import com.backend.banking_app.exception.InSufficientBalanceException;
import com.backend.banking_app.exception.InvalidAccountDataException;
import com.backend.banking_app.mapper.AccountMapper;
import com.backend.banking_app.repository.AccountRepository;
import com.backend.banking_app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        String validationMessage = validate(accountDto);

        if (!validationMessage.equals("VALID")) {
            throw new InvalidAccountDataException(validationMessage);
        }

        Account account = AccountMapper.mapAccountDtoToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapAccountToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with id: " + accountId));

        return AccountMapper.mapAccountToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long accountId, Double balance) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with id: " + accountId));
        ;

        double total = account.getBalance() + balance;
        account.setBalance(total);

        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapAccountToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long accountId, Double balance) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with id: " + accountId));

        if (account.getBalance() < balance) {
            throw new InSufficientBalanceException("Insufficient balance for withdrawal.");
        }

        double total = account.getBalance() - balance;
        account.setBalance(total);

        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapAccountToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> account = accountRepository.findAll();

        return account.stream()
                .map(AccountMapper::mapAccountToAccountDto)
                .toList();
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with id: " + accountId));

        accountRepository.deleteById(accountId);
    }

    public String validate(AccountDto accountDto) {

        if (accountDto == null) {
            return "Account data is required";
        }

        if (accountDto.getAccountHolderName() == null ||
                accountDto.getAccountHolderName().trim().isEmpty()) {
            return "Account holder name is required";
        }

        if (accountDto.getBalance() < 0) {
            return "Initial account balance cannot be less than 0.";
        }

        return "VALID";
    }
}