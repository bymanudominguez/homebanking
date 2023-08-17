package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;

import java.time.LocalDate;

public class CardDTO {
    private Long id;
    private String cardHolder;
    private Integer number;
    private int cvv;
    private LocalDate fromDate;
    private LocalDate thruDate;
    private CardType cardType;
    private CardColor cardColor;

    public CardDTO(Card card){
        id = card.getId();
        cardHolder = card.getCardHolder();
        number = card.getNumber();
        cvv = card.getCvv();
        fromDate = card.getFromDate();
        thruDate = card.getThruDate();
        cardType = card.getCardType();
        cardColor = card.getCardColor();
    }

    public Long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public int getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public CardType getType() {
        return cardType;
    }

    public CardColor getColor() {
        return cardColor;
    }
}
