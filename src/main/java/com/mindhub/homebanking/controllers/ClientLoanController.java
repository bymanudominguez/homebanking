package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientLoanDTO;
import com.mindhub.homebanking.services.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientLoanController {

    @Autowired
    private ClientLoanService clientLoanService;

    @RequestMapping("/clientLoans")
    public List<ClientLoanDTO> getClientLoans() {

        return clientLoanService.getClientLoans();
    }
}
