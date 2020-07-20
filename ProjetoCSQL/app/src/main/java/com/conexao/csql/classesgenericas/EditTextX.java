package com.conexao.csql.classesgenericas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.ArrayList;

@SuppressLint("AppCompatCustomView")
public class EditTextX extends EditText {


    ArrayList<EditTextX> linhas=new ArrayList<EditTextX>();
    public String valorStr="";

    public EditTextX(Context context) {
        super(context);
    }

    public EditTextX(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextX(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EditTextX(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public ArrayList<EditTextX> getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList<EditTextX> linhas) {
        this.linhas = linhas;
    }

    public String getValorStr() {
        return valorStr;
    }

    public void setValorStr(String valorStr) {
        this.valorStr = valorStr;
    }
}
