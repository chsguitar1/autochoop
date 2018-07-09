package br.com.autochoop.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.orm.SugarRecord;

/**
 * Created by cristiano on 11/02/17.
 */
@IgnoreExtraProperties
public class Products  extends SugarRecord {
    String idproduct;
    String nameproduct;
    String valueproduct;
    String idEmploy;
    String description;


    public Products() {
    }

    public Products(String idproduct, String nameproduct, String valueproduct, String idEmploy, String description) {
        this.idproduct = idproduct;
        this.nameproduct = nameproduct;
        this.valueproduct = valueproduct;
        this.idEmploy = idEmploy;
        this.description = description;
    }

    public String getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(String idproduct) {
        this.idproduct = idproduct;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public String getValueproduct() {
        return valueproduct;
    }

    public void setValueproduct(String valueproduct) {
        this.valueproduct = valueproduct;
    }

    public String getIdEmploy() {
        return idEmploy;
    }

    public void setIdEmploy(String idEmploy) {
        this.idEmploy = idEmploy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
