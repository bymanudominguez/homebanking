package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAccounts();

    void saveAccount(Account account);
}
