package br.com.autochoop.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by cristiano on 11/02/17.
 */
@IgnoreExtraProperties
public class Cards {
    String idEmploy;
    String idcard;
    Double valuecredcard;



    public String getIdEmploy() {
        return idEmploy;
    }

    public void setIdEmploy(String idEmploy) {
        this.idEmploy = idEmploy;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Double getValuecredcard() {
        return valuecredcard;
    }

    public void setValuecredcard(Double valuecredcard) {
        this.valuecredcard = valuecredcard;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "idEmploy='" + idEmploy + '\'' +
                ", idcard='" + idcard + '\'' +
                ", valuecredcard=" + valuecredcard +
                '}';
    }
}
