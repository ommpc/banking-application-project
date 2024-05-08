package net.banking.banking.service;

import net.banking.banking.dto.AccountDto;
import net.banking.banking.entity.Account;

import java.util.List;

public interface AccountService {
   AccountDto createAccount(AccountDto accountDto);
            AccountDto   getAccountById(Long id);
            AccountDto deposit(long id, double amount);
            AccountDto withdraw(Long id, double amount);
              List<AccountDto> getAllAccounts();
              void deleteAccount(Long id);
}
