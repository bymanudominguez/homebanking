package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.ClientLoan;

public class ClientLoanDTO {
    private final Long id;
    private final Long loanId;
    private final String name;
    private final double amount;
    private final int payments;

    public ClientLoanDTO(ClientLoan clientLoan) {
        id = clientLoan.getId();
        loanId = clientLoan.getLoan().getId();
        name = clientLoan.getLoan().getName();
        amount = clientLoan.getAmount();
        payments = clientLoan.getPayments();
    }

    public Long getId() {
        return id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public int getPayments() {
        return payments;
    }
}
