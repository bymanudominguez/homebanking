package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/accounts")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id) {

        return new AccountDTO(accountRepository.findById(id).orElse(null));
    }

    @RequestMapping("/clients/current/accounts")
    public List<AccountDTO> getAccounts(Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());

        return client.getAccounts().stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
    }

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> addAccount(Authentication authentication) {
        if (authentication != null) {
            Client client = clientRepository.findByEmail(authentication.getName());
            if (client != null) {
                if (client.getAccounts().toArray().length >= 3) {

                    return new ResponseEntity<>("The maximum number of accounts was reached", HttpStatus.FORBIDDEN);
                } else {
                    Account account = new Account("VIN-" + getRandomNumber(10000000, 99999999), LocalDateTime.now());
                    account.setBalance(0.00);
                    client.addAccount(account);
                    accountService.saveAccount(account);
                }
            }
        } else {

            return new ResponseEntity<>("Login to continue", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
    }

    public int getRandomNumber(int min, int max) {
        int randomNumber;
        do {
            randomNumber = (int) ((Math.random() * (max - min)) + min);
        } while (accountRepository.findByNumber("VIN-" + randomNumber) != null);

        return randomNumber;
    }
}
