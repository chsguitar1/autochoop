package br.com.autochoop.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by cristiano on 11/02/17.
 */
@IgnoreExtraProperties
public class Cards {
    Long idCard;
    String codCard;
    Double valueCreditCard;


    public Cards(Long idCard, String codCard, Double valueCreditCard) {
        this.idCard = idCard;
        this.codCard = codCard;
        this.valueCreditCard = valueCreditCard;
    }

    public Long getIdCard() {
        return idCard;
    }

    public void setIdCard(Long idCard) {
        this.idCard = idCard;
    }

    public String getCodCard() {
        return codCard;
    }

    public void setCodCard(String codCard) {
        this.codCard = codCard;
    }

    public Double getValueCreditCard() {
        return valueCreditCard;
    }

    public void setValueCreditCard(Double valueCreditCard) {
        this.valueCreditCard = valueCreditCard;
    }
}
