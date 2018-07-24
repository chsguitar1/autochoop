package br.com.autochoop.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by cristiano on 02/07/18.
 */
@IgnoreExtraProperties
public class Sale {
    Timestamp dateSale;
    String idcard;
    Double value;
    Machine machine;
    int status;


    public Sale() {
    }


    public Sale(Timestamp dateSale, String idcard, Double value, Machine machine, int status) {
        this.dateSale = dateSale;
        this.idcard = idcard;
        this.value = value;
        this.machine = machine;
        this.status = status;
    }

    public Timestamp getDateSale() {
        return dateSale;
    }

    public void setDateSale(Timestamp dateSale) {
        this.dateSale = dateSale;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
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
