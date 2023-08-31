package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("/clients")
    public List<ClientDTO> getClients() {

        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {

        if (firstName.isBlank()) {

            return new ResponseEntity<>("You need to put your name", HttpStatus.FORBIDDEN);
        } else if (lastName.isBlank()) {

            return new ResponseEntity<>("You need to enter your last name", HttpStatus.FORBIDDEN);
        } else if (email.isBlank()) {

            return new ResponseEntity<>("You need to put an email", HttpStatus.FORBIDDEN);
        } else if (password.isBlank()) {

            return new ResponseEntity<>("You need to set a password", HttpStatus.FORBIDDEN);
        } else if (clientRepository.findByEmail(email) != null) {

            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        } else {
            Client client = clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));
            Account account = new Account("VIN-" + getRandomNumber(10000000, 99999999), LocalDateTime.now());
            account.setBalance(0.00);
            client.addAccount(account);
            accountRepository.save(account);

            return new ResponseEntity<>("User successfully created", HttpStatus.CREATED);
        }
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {

        return new ClientDTO(Objects.requireNonNull(clientRepository.findById(id).orElse(null)));
    }

    @GetMapping("/clients/online")
    public ResponseEntity<String> connection(Authentication authentication) {
        if (authentication != null) {

            return new ResponseEntity<>("Connected", HttpStatus.ACCEPTED);
        } else {

            return new ResponseEntity<>("Disconnected", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/clients/current")
    public ClientDTO getCurrent(Authentication authentication) {
        return new ClientDTO(clientRepository.findByEmail(authentication.getName()));
    }

    public int getRandomNumber(int min, int max) {
        int randomNumber;
        do {
            randomNumber = (int) ((Math.random() * (max - min)) + min);
        } while (accountRepository.findByNumber("VIN-" + randomNumber) != null);

        return randomNumber;
    }
}
