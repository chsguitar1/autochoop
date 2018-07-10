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



    public Machine() {
    }

    public Machine(String idEmploy, String idmachine, boolean status, String extractvalue) {
        this.idEmploy = idEmploy;
        this.idmachine = idmachine;
        this.status = status;
    }

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
}
