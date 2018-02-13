package br.com.autochoop.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by cristiano on 11/02/17.
 */
@IgnoreExtraProperties
public class CardsEmploy {
    Long idCardsEmploy;
    List<Cards> cards;
    Employ employ;

    public CardsEmploy(Long idCardsEmploy, List<Cards> cards, Employ employ) {
        this.idCardsEmploy = idCardsEmploy;
        this.cards = cards;
        this.employ = employ;
    }

    public Long getIdCardsEmploy() {
        return idCardsEmploy;
    }

    public void setIdCardsEmploy(Long idCardsEmploy) {
        this.idCardsEmploy = idCardsEmploy;
    }

    public List<Cards> getCards() {
        return cards;
    }

    public void setCards(List<Cards> cards) {
        this.cards = cards;
    }

    public Employ getEmploy() {
        return employ;
    }

    public void setEmploy(Employ employ) {
        this.employ = employ;
    }
}
