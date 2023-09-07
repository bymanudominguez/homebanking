package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getClients();
}
