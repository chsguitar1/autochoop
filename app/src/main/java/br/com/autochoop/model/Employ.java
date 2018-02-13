package br.com.autochoop.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cristiano on 11/02/17.
 */
@IgnoreExtraProperties
public class Employ implements Serializable {
    Long idEmploy;
    String nameEmploy;
    List<Products> productsList;
    List<Cards> cardsList;

    public Employ(Long idEmploy, String nameEmploy, List<Products> productsList, List<Cards> cardsList) {
        this.idEmploy = idEmploy;
        this.nameEmploy = nameEmploy;
        this.productsList = productsList;
        this.cardsList = cardsList;
    }

    public List<Cards> getCardsList() {
        return cardsList;
    }

    public void setCardsList(List<Cards> cardsList) {
        this.cardsList = cardsList;
    }

    public Long getIdEmploy() {
        return idEmploy;
    }

    public void setIdEmploy(Long idEmploy) {
        this.idEmploy = idEmploy;
    }

    public String getNameEmploy() {
        return nameEmploy;
    }

    public void setNameEmploy(String nameEmploy) {
        this.nameEmploy = nameEmploy;
    }

    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }
}
