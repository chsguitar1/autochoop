package br.com.autochoop.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.orm.SugarRecord;

/**
 * Created by cristiano on 11/02/17.
 */
@IgnoreExtraProperties
public class Products  extends SugarRecord {
    Long idProdutct;
    String nameProduct;
    Double valueProduct;
    String idEmploy;


    public Products() {
    }

    public Products(Long idProdutct, String nameProduct, Double valueProduct, String idEmploy) {
        this.idProdutct = idProdutct;
        this.nameProduct = nameProduct;
        this.valueProduct = valueProduct;
        this.idEmploy = idEmploy;
    }

    public Long getIdProdutct() {
        return idProdutct;
    }

    public void setIdProdutct(Long idProdutct) {
        this.idProdutct = idProdutct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getValueProduct() {
        return valueProduct;
    }

    public void setValueProduct(Double valueProduct) {
        this.valueProduct = valueProduct;
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
                "idProdutct=" + idProdutct +
                ", nameProduct='" + nameProduct + '\'' +
                ", valueProduct=" + valueProduct +
                ", idEmploy='" + idEmploy + '\'' +
                '}';
    }
}
