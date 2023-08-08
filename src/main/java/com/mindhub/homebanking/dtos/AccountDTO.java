package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AccountDTO {
    private Long id;
    private String number;
    private LocalDate creationDate;
    private LocalDateTime localDateTime;
    private Double balance;

    public AccountDTO(Account account){
        id = account.getId();
        number = account.getNumber();
        creationDate = account.getCreationDate();
        localDateTime = account.getLocalDateTime();
        balance = account.getBalance();
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Double getBalance() {
        return balance;
    }
}
