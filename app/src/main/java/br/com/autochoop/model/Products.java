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


    public Products() {
    }

    public Products(String idProdutct, String nameProduct, String valueProduct, String idEmploy) {
        this.idproduct = idProdutct;
        this.nameproduct = nameProduct;
        this.valueproduct = valueProduct;
        this.idEmploy = idEmploy;
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

    @Override
    public String toString() {
        return "Products{" +
                "idproduct=" + idproduct +
                ", nameproduct='" + nameproduct + '\'' +
                ", valueproduct=" + valueproduct +
                ", idEmploy='" + idEmploy + '\'' +
                '}';
    }
}
