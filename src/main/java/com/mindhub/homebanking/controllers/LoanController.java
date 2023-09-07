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
    public ResponseEntity<Object> addLoan(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication) {
        if (authentication != null) {
            Client client = clientRepository.findByEmail(authentication.getName());
            Loan loan = loanRepository.findById(loanApplicationDTO.getLoanId()).orElse(null);
            Account account = accountRepository.findByNumber(loanApplicationDTO.getToAccountNumber());

            if (loan == null) {

                return new ResponseEntity<>("The loan doesn't exist", HttpStatus.FORBIDDEN);
            } else if (!client.getAccounts().contains(accountRepository.findByNumber(loanApplicationDTO.getToAccountNumber()))) {

                return new ResponseEntity<>("Destination account doesn't exist", HttpStatus.FORBIDDEN);
            } else if (accountRepository.findByNumber(loanApplicationDTO.getToAccountNumber()).getClient() != client) {

                return new ResponseEntity<>("The account doesn't belong to you", HttpStatus.FORBIDDEN);
            } else if (loanApplicationDTO.getAmount() <= 0) {

                return new ResponseEntity<>("The requested amount must be at least $1", HttpStatus.FORBIDDEN);
            } else if (loanApplicationDTO.getAmount() > loan.getMaxAmount()) {

                return new ResponseEntity<>("The amount exceeds the maximum that can be requested", HttpStatus.FORBIDDEN);
            } else if (!loan.getPayments().contains(loanApplicationDTO.getPayments())) {

                return new ResponseEntity<>("The amount of payments is not available", HttpStatus.FORBIDDEN);
            } else {
                Transaction transaction = new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), loan.getName() + " loan approved", LocalDateTime.now());
                ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount() * 0.2, loanApplicationDTO.getPayments());
                client.addClientLoan(clientLoan);
                loan.addClientLoan(clientLoan);
                account.addTransaction(transaction);
                account.setBalance(account.getBalance() + loanApplicationDTO.getAmount());

                clientLoanRepository.save(clientLoan);
                transactionRepository.save(transaction);
                accountRepository.save(account);

                return new ResponseEntity<>("Loan accepted, the money was deposited in your account", HttpStatus.CREATED);
            }
        } else {

            return new ResponseEntity<>("Login to continue", HttpStatus.FORBIDDEN);
        }
    }
}
