package com.conexao.csql.classesgenericas;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.conexao.csql.MainActivity;
import com.conexao.csql.bdM.BancoT;


import java.util.InputMismatchException;

public class Base {
public ProgressDialog ag=null;
public BancoT     b=null;

    public MainActivity pr=null;
    public int mLayout=0;
    public Base(MainActivity pr, int mLayout){
        ag=new ProgressDialog(pr);
        ag.setCancelable(false);

        this.mLayout=mLayout;
        this.pr=pr;
        b=pr.b;
    }

public void chamaTela(){

 pr.novaTela(mLayout);
}

public void montaTitulo(TableLayout tb,String titulo){
    TableRow tr=new TableRow(pr);
    TextView tx=new TextView(pr);
    tx.setTextSize(20);
    tx.setText(titulo);
    tr.addView(tx);
        tb.addView(tr);

}
public Object fdById(int id){

        return pr.findViewById(id);
}
    public Object fdId(int id){

        return pr.findViewById(id);
    }
    public ImageView fdGIv(View v, int id){

        return v.findViewById(id);
    }
    public ImageButton fdGIb(View v,int id){

        return v.findViewById(id);
    }
    public TextView fdG(View v,int id){

        return v.findViewById(id);
    }
    public Button fdBt(int id){

        return pr.findViewById(id);
    }
    public RadioButton fdRd(int id){

        return pr.findViewById(id);
    }
    public ImageButton fdImBt(int id){

        return pr.findViewById(id);
    }

    public CheckBox fdCx(int id){

        return pr.findViewById(id);
    }
    public EditText fdEd(int id){

        return pr.findViewById(id);
    }
    public String fdBtS(int id){

        return ((Button)pr.findViewById(id)).getText().toString();
    }
    public String fdEdS(int id){

        return ((EditText)pr.findViewById(id)).getText().toString();
    }
    public TextView fdTx(int id){

        return pr.findViewById(id);
    }

    public String fdTxS(int id){

        return ((TextView)pr.findViewById(id)).getText().toString();
    }

  public  class Acao{

        public Acao(int bt){
            fdBt(bt).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }
        public void acao(){



        }

    }

    public void Mensagem(final String titulo,final String texto){
        pr.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                AlertDialog.Builder mensagem=new AlertDialog.Builder(pr);
                mensagem.setTitle(titulo);
                mensagem.setMessage(texto);
                mensagem.setNeutralButton("OK", null);
                mensagem.show();
            }
        });


    }
    public   boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }


}
