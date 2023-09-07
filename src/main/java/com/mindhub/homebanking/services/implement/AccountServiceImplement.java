package com.mindhub.homebanking.services.implement;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImplement implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAccounts() {

        return accountRepository.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

}
