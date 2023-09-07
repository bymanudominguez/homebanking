package com.mindhub.homebanking.dtos;


public class LoanApplicationDTO {
    private final Double amount;
    private final Long loanId;
    private final Integer payments;
    private final String toAccountNumber;

    public LoanApplicationDTO(Double amount, Long id, Integer payments, String number) {
        this.amount = amount;
        this.loanId = id;
        this.payments = payments;
        this.toAccountNumber = number;
    }

    public Double getAmount() {
        return amount;
    }

    public Long getLoanId() {
        return loanId;
    }

    public Integer getPayments() {
        return payments;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }
}
