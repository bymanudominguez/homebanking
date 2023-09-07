package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/transactions")
    public List<TransactionDTO> getTransaction() {

        return transactionService.getTransaction();
    }

    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> makeTransaction(Authentication authentication, @RequestParam Double amount, @RequestParam String description, @RequestParam String fromAccountNumber, @RequestParam String toAccountNumber) {
        if (amount.isNaN()) {
            return new ResponseEntity<>("You have to put an amount", HttpStatus.FORBIDDEN);
        } else if (amount <= 0) {
            return new ResponseEntity<>("The amount must be at least $1", HttpStatus.FORBIDDEN);
        }

        if (description.isBlank()) {
            return new ResponseEntity<>("You have to put a description", HttpStatus.FORBIDDEN);
        }

        if (fromAccountNumber.isBlank()) {
            return new ResponseEntity<>("You have to choose a origin account", HttpStatus.FORBIDDEN);
        }

        if (toAccountNumber.isBlank()) {
            return new ResponseEntity<>("You have to choose a destination account", HttpStatus.FORBIDDEN);
        }

        Client client = clientRepository.findByEmail(authentication.getName());
        Account originAccount = accountRepository.findByNumber(fromAccountNumber);
        Account destinationAccount = accountRepository.findByNumber(toAccountNumber);

        if (client != null) {

            if (originAccount == null) {
                return new ResponseEntity<>("The account of origin doesn't exist", HttpStatus.FORBIDDEN);
            }

            if (!client.getAccounts().contains(accountRepository.findByNumber(fromAccountNumber))) {
                return new ResponseEntity<>("This account doesn't belong to you", HttpStatus.FORBIDDEN);
            }

            if (fromAccountNumber.equals(toAccountNumber)) {
                return new ResponseEntity<>("The source account is the same as the destination account", HttpStatus.FORBIDDEN);
            }

            if (destinationAccount == null) {
                return new ResponseEntity<>("Target account doesn't exist", HttpStatus.FORBIDDEN);
            }

            if (originAccount.getBalance() < amount) {
                return new ResponseEntity<>("Not enough funds", HttpStatus.FORBIDDEN);
            }


            Transaction originTransaction = new Transaction(TransactionType.DEBIT, (-amount), description + " to " + destinationAccount.getNumber(), LocalDateTime.now());
            originAccount.addTransaction(originTransaction);
            originAccount.setBalance(originAccount.getBalance() - amount);

            Transaction destinationTransaction = new Transaction(TransactionType.CREDIT, amount, description + " from " + originAccount.getNumber(), LocalDateTime.now());
            destinationAccount.addTransaction(destinationTransaction);
            destinationAccount.setBalance(destinationAccount.getBalance() + amount);

            transactionService.saveTransaction(originTransaction);
            transactionService.saveTransaction(destinationTransaction);

            accountService.saveAccount(originAccount);
            accountService.saveAccount(destinationAccount);

            return new ResponseEntity<>("The transaction was successfully completed", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("Login to continue", HttpStatus.FORBIDDEN);
        }
    }
}
