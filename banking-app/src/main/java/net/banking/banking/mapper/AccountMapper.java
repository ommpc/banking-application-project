package net.banking.banking.mapper;

import net.banking.banking.dto.AccountDto;
import net.banking.banking.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto) {
        Account account = new Account(accountDto.getId(), accountDto.getAccountHoldername(), accountDto.getBalance());

        return account;

    }

    public static AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = new AccountDto(account.getId(), account.getAccountHoldername(), account.getBalance() );
  return accountDto;
    }
}
