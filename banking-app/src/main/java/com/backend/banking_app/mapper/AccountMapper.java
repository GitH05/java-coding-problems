package com.backend.banking_app.mapper;

import com.backend.banking_app.dto.AccountDto;
import com.backend.banking_app.entity.Account;

public class AccountMapper {

    public static Account mapAccountDtoToAccount(AccountDto accountDto) {

        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance());
        return account;
    }

    public static AccountDto mapAccountToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance());
        return accountDto;
    }

}
