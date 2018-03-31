package br.com.autochoop.model;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by cristiano on 30/03/18.
 */

public class Telemetria extends SugarRecord {
    Date date;
    Integer idproduct;

    public Telemetria(Date date, Integer idProdutct) {
        this.date = date;
        this.idproduct = idProdutct;
    }

    public Telemetria() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Integer idproduct) {
        this.idproduct = idproduct;
    }

    @Override
    public String toString() {
        return "Telemetria{" +
                "date=" + date +
                ", idproduct=" + idproduct +
                '}';
    }
}
