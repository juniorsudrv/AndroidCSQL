package com.conexao.csql.bdL;

import android.content.Context;
import android.util.Log;


public class BancoLT extends BancoL implements Cloneable {
    public static String getvarchar(int num){

        return " varchar("+num+")";

    }


    public BancoLT clone(){

        return (BancoLT)super.clone();

    }

    public static String blob="longblob";
    public static String varchar="varchar(250)";
    public static String integer="integer";
    public static String numero="float ";
    public static String real="float";
    public static String auto_increment="INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL";
    public static String date="date";
    public static String time="time";
    public static String text="text";
    public static String datetime="datetime";
    public BancoLT(Context pri) {
        super(pri);
        Log.d("BancoT", "BancoT "+tabelas.size()+" "+tabelasN.size());
    }

    public BancoLT(Context pri, String nome) {
        super(pri,nome);
        Log.d("BancoT", "BancoT "+tabelas.size()+" "+tabelasN.size());
    }


    public static String banco="banco2";
    public static colunaLT banco_host=new colunaLT("banco_host",
            banco,varchar,true,"Usuário ");
    public static colunaLT banco_nomebanco=new colunaLT("banco_nomebanco",
            banco,varchar,true,"Senha ");
    public static colunaLT banco_usuario=new colunaLT("banco_usuario",
            banco,varchar,true,"Senha ");
    public static colunaLT banco_senha=new colunaLT("banco_senha",
            banco,varchar,true,"Senha ");
    public static colunaLT banco_porta=new colunaLT("banco_porta",
            banco,varchar,true,"Senha ");
    public static colunaLT banco_fonte=new colunaLT("banco_fonte",
            banco,varchar,true,"Senha ");
    public static colunaLT banco_id=new colunaLT("banco_id",
            banco,auto_increment,true,"ID ");

}