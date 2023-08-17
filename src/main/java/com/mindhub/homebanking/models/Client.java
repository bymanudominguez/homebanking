package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private Set<ClientLoan> clientLoans = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private Set<Card> cards = new HashSet<>();

    public Client() {}

    public Client(String first, String last, String mail){
        this.firstName = first;
        this.lastName = last;
        this.email = mail;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Set<Account> getAccounts() {
        return accounts;
    }

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void addAccount(Account account) {
        account.setClient(this);
        this.accounts.add(account);
    }

    public void addClientLoan(ClientLoan clientLoan){
        clientLoan.setClient(this);
        this.clientLoans.add(clientLoan);
    }

    public void addCard(Card card) {
        card.setClient(this);
        this.cards.add(card);
    }

    public List<Loan> getLoans(){
        return clientLoans.stream().map(clientLoan -> clientLoan.getLoan()).collect(Collectors.toList());
    }
}
