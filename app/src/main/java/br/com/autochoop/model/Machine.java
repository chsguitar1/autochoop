package br.com.autochoop.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by cristiano on 01/07/18.
 */
@IgnoreExtraProperties
public class Machine {
    String idEmploy;
    String idmachine;
    boolean status;
    Products products;
    boolean sale;
    Double extactvalue;
    boolean online;
    String idcard;
    Cards cards;


    public String getIdEmploy() {
        return idEmploy;
    }

    public void setIdEmploy(String idEmploy) {
        this.idEmploy = idEmploy;
    }

    public String getIdmachine() {
        return idmachine;
    }

    public void setIdmachine(String idmachine) {
        this.idmachine = idmachine;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public Double getExtactvalue() {
        return extactvalue;
    }

    public void setExtactvalue(Double extactvalue) {
        this.extactvalue = extactvalue;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Cards getCards() {
        return cards;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "idEmploy='" + idEmploy + '\'' +
                ", idmachine='" + idmachine + '\'' +
                ", status=" + status +
                ", products=" + products +
                ", sale=" + sale +
                ", extactvalue=" + extactvalue +
                ", online=" + online +
                ", idcard='" + idcard + '\'' +
                ", cards=" + cards +
                '}';
    }
}


