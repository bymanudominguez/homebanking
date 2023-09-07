package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.ClientLoanDTO;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.repositories.ClientLoanRepository;

import java.util.List;

public interface ClientLoanService {
    List<ClientLoanDTO> getClientLoans();

    void saveClientLoan(ClientLoan clientLoan);
}
