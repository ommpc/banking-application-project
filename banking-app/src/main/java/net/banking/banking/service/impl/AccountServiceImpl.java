package net.banking.banking.service.impl;

import net.banking.banking.dto.AccountDto;
import net.banking.banking.entity.Account;
import net.banking.banking.mapper.AccountMapper;
import net.banking.banking.repository.AccountRepository;
import net.banking.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
      Account account  =AccountMapper.mapToAccount(accountDto);
        Account savedAccount =accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("account doesnot exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("account doesnot exist"));
       double total= account.getBalance()+amount;
       account.setBalance(total);
   Account savedAccount   = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("account doesnot exist"));
        if(account.getBalance()<amount)
        {
            throw new RuntimeException("insufficient balance");
        }
         double total = account.getBalance()-amount;
        account.setBalance(total);
      Account savedAccount  =accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
       List<Account> accounts =accountRepository.findAll();
      return  accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(()->new RuntimeException("account doesnot exist"));
        accountRepository.deleteById(id);

    }


}

