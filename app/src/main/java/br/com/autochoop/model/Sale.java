package br.com.autochoop.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by cristiano on 02/07/18.
 */
@IgnoreExtraProperties
public class Sale {
    Date dateSale;
    Cards cards;
    Double value;
    Machine machine;
    int status;


    public Sale() {
    }

    public Sale(Date dateSale, Cards cards, Double value, Machine machine, int status) {
        this.dateSale = dateSale;
        this.cards = cards;
        this.value = value;
        this.machine = machine;
        this.status = status;
    }

    public Date getDateSale() {
        return dateSale;
    }

    public void setDateSale(Date dateSale) {
        this.dateSale = dateSale;
    }

    public Cards getCards() {
        return cards;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
