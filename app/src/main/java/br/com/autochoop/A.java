package br.com.autochoop;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;


import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.autochoop.model.Machine;


/**
 * Created by cristiano on 09/09/15.
 */
public abstract class A {
    public  static  String IDEMPLOY;
    public  static Machine IDMACHINE;

    public static String formatarValor(BigDecimal numero) {
        String retorno = "";
        DecimalFormat formatter = new DecimalFormat("#0.00");
        formatter.setMinimumFractionDigits(2);

        try {
            retorno = formatter.format(numero).replace(",", ".");
        } catch (Exception var5) {
            var5.printStackTrace();
            Log.e("afaLog", var5.getMessage());
        }

        return retorno;
    }public static String formatarValor(double numero) {
        String retorno = "";
        DecimalFormat formatter = new DecimalFormat("#0.00");
        formatter.setMinimumFractionDigits(2);

        try {
            retorno = formatter.format(numero).replace(",", ".");
        } catch (Exception var5) {
            var5.printStackTrace();
            Log.e("afaLog", var5.getMessage());
        }

        return retorno;
    }
    public static String dataParaString(Date data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(data);
        } catch (Exception e) {
            Log.e("afaLog","Houve um problema ao obter uma data (data para string)"+e.getMessage());
            return null;
        }
    }
    public static Date stringParaDataTraco(String sData) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(sData);
        } catch (Exception e) {
            Log.e("afaLog","Houve um problema ao obter uma data (String para data)"+e.getMessage());
            return null;
        }
    }

    public  static String getHoraString(){
        int minuto = new Date().getMinutes();
        int hora = new Date().getHours();
        String horaMenor = "";
        String minutoMenor = "";
        if(hora < 10){
            horaMenor = "0"+ String.valueOf(hora);
        }else{
            horaMenor = String.valueOf(hora);
        }
        if(minuto < 10){
            minutoMenor = "0"+ String.valueOf(minuto);
        }else{
            minutoMenor = String.valueOf(minuto);
        }
        return  horaMenor+":"+minutoMenor;
    }
    public static void showMsg(String msg, Context ctx){
        new LovelyInfoDialog(ctx)
                .setTopColorRes(R.color.colorPrimary)
                .setTitle(R.string.app_name)
                .setMessage(msg)
                .show();

    }
    public static boolean verificaConexao(Context context) {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }
    public static LovelyProgressDialog progressDialog(Context ctx, String msg){
        LovelyProgressDialog customProgressDialog =new LovelyProgressDialog(ctx)

                .setMessage(msg)
                .setTitle(R.string.app_name)
                .setCancelable(false)
                .setTopColor(R.color.colorPrimary);
        return  customProgressDialog;
    }
    public static void logar(String texto) {
        Log.i(String.valueOf(R.string.app_name), texto);
    }


}



