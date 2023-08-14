package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Integer amount;

    private int payments;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client clients;

    @ManyToOne(fetch = FetchType.EAGER)
    private Loan loans;

    public ClientLoan(){

    }

    public ClientLoan(Integer amount, int payments) {
        this.amount = amount;
        this.payments = payments;
    }

    public Long getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

    public Client getClients() {
        return clients;
    }

    public Loan getLoans() {
        return loans;
    }
}
