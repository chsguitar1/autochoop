package br.com.autochoop.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by cristiano on 11/02/17.
 */
@IgnoreExtraProperties
public class Products  {
    Long idProdutct;
    String nameProduct;
    Double valueProduct;


    public Products() {
    }

    public Products(Long idProdutct, String nameProduct, Double valueProduct) {
        this.idProdutct = idProdutct;
        this.nameProduct = nameProduct;
        this.valueProduct = valueProduct;
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
}
