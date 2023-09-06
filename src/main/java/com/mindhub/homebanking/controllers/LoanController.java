package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.LoanApplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    public LoanRepository loanRepository;

    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    public ClientLoanRepository clientLoanRepository;

    @RequestMapping("/loans")
    public List<LoanDTO> getLoans() {

        return loanRepository.findAll().stream().map(loan -> new LoanDTO(loan)).collect(Collectors.toList());
    }

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<Object> addLoan(@RequestBody LoanApplicationDTO loan, Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());
        Account account = accountRepository.findByNumber(loan.getNumber());

        if (client != null) {
           /*

           if (loanRepository.findById(loan.getId()) == null) {

                return new ResponseEntity<>("The loan doesn't exist", HttpStatus.FORBIDDEN);
            }

            if (loan.getAmount() <= 0) {

                return new ResponseEntity<>("The requested amount must be at least $1", HttpStatus.FORBIDDEN);
            }

            if (loan.getAmount() > loanRepository.findById(loan.getId()).get().getMaxAmount()) {

                return new ResponseEntity<>("The amount exceeds the maximum that can be requested", HttpStatus.FORBIDDEN);
            }

            if (!loanRepository.findById(loan.getId()).get().getPayments().contains(loan.getPayments())) {

                return new ResponseEntity<>("The amount of payments is not available", HttpStatus.FORBIDDEN);
            }

            if (accountRepository.findByNumber(loan.getNumber()) == null) {

                return new ResponseEntity<>("Destination account doesn't exist", HttpStatus.FORBIDDEN);
            }

            if (!client.getAccounts().contains(accountRepository.findByNumber(loan.getNumber()))) {

                return new ResponseEntity<>("The account doesn't belong to you", HttpStatus.FORBIDDEN);
            }

            */

            Transaction transaction = new Transaction(TransactionType.CREDIT, loan.getAmount() * 0.2, loanRepository.findById(loan.getId()).get().getName() + " loan approved", LocalDateTime.now());
            account.addTransaction(transaction);
            account.setBalance(account.getBalance() + loan.getAmount());

            ClientLoan clientLoan = new ClientLoan();

            transactionRepository.save(transaction);
            accountRepository.save(account);


        } else {

            return new ResponseEntity<>("Login to continue", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("Loan accepted, the money was deposited in your account", HttpStatus.CREATED);
    }
}
