package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/cards")
    public List<CardDTO> getCards() {
        return cardRepository.findAll().stream().map(card -> new CardDTO(card)).collect(Collectors.toList());
    }

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> addCard(Authentication authentication, @RequestParam CardType cardType, @RequestParam CardColor cardColor) {
        if (cardType == null) {

            return new ResponseEntity<>("You must specify the type of card", HttpStatus.FORBIDDEN);
        }

        if (cardColor == null) {

            return new ResponseEntity<>("You must specify the color of card", HttpStatus.FORBIDDEN);
        }

        Client client = clientRepository.findByEmail(authentication.getName());
        if (client != null) {

            Set<Card> sameTypeCards = client.getCards().stream().filter(card -> card.getCardType() == cardType).collect(Collectors.toSet());

            if (sameTypeCards.toArray().length >= 3) {

                return new ResponseEntity<>("The maximum number for this type of cards was reached", HttpStatus.FORBIDDEN);
            } else {
                Card card = new Card(LocalDate.now(), cardType, cardColor);
                card.setThruDate(card.getFromDate().plusYears(5));
                card.setCvv(getRandomNumber(100, 999));
                card.setNumber(getRandomNumber(1000, 9999) + "-" + getRandomNumber(1000, 9999) + "-" + getRandomNumber(1000, 9999) + "-" + getRandomNumber(1000, 9999));
                card.setCardHolder(client.getFirstName() + " " + client.getLastName());
                client.addCard(card);
                cardRepository.save(card);
            }
        }
        return new ResponseEntity<>("The card has been created successfully", HttpStatus.CREATED);
    }

    public int getRandomNumber(int min, int max) {
        int randomNumber;
        do {
            randomNumber = (int) ((Math.random() * (max - min)) + min);
        } while (cardRepository.findByNumber(randomNumber + "-" + randomNumber + "-" + randomNumber + "-" + randomNumber) != null);

        return randomNumber;
    }
}
