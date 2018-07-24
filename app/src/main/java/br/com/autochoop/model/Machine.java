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
    Products product;
    boolean sale;
    Double extractvalue;
    boolean online;
    String idcard;
    Cards cards;
    Double valuecredcard;


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

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public Double getExtractvalue() {
        return extractvalue;
    }

    public void setExtractvalue(Double extractvalue) {
        this.extractvalue = extractvalue;
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

    public Double getValuecredcard() {
        return valuecredcard;
    }

    public void setValuecredcard(Double valuecredcard) {
        this.valuecredcard = valuecredcard;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "idEmploy='" + idEmploy + '\'' +
                ", idmachine='" + idmachine + '\'' +
                ", status=" + status +
                ", product=" + product +
                ", sale=" + sale +
                ", extractvalue=" + extractvalue +
                ", online=" + online +
                ", idcard='" + idcard + '\'' +
                ", cards=" + cards +
                ", valuecredcard=" + valuecredcard +
                '}';
    }
}


