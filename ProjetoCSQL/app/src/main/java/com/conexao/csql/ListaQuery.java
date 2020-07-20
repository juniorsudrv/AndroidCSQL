package com.conexao.csql;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.conexao.csql.MenuFloat.FloatingActionButton;
import com.conexao.csql.Threads.Tr;
import com.conexao.csql.bdL.BancoLT;
import com.conexao.csql.caixasdedialogo.DialogoEntradaTextoIndex;
import com.conexao.csql.caixasdedialogo.DialogoMapa;
import com.conexao.csql.classesgenericas.Base;
import com.conexao.csql.classesgenericas.EditTextX;

import java.util.ArrayList;

public class ListaQuery extends Base {
public DialogoMapa di=null;
BancoLT lt =null;
String banco;
String table;
    ArrayList<Object[]> query;
    public ListaQuery(MainActivity pri, int mLayout,String banco,String table, ArrayList<Object[]> query) {
        super(pri, mLayout);
        this.lt =pri.l;
        this.query=query;
        this.banco=banco;
        this.table=table;
        pr.onde=3;
        montaTela();
pr.cg.setTable(table);


    }
    int inicioC=0;
 public void montaTela(){
 chamaTela();

     TableLayout tbl=pr.findViewById(R.id.tabela);
    tbl.removeAllViews();
     montaTitulo(tbl,"CONSULTA "+table);

    TableRow dr=new TableRow(pr);
    Button bt=new Button(pr);
    bt.setText("Adicionar Registro");
    bt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
/*
* INSERT INTO table_name (column1, column2, column3, ...)
VALUES (value1, value2, value3, ...);
* */
String colun="";
String[] dados=new String[pr.cg.getColunas().size()] ;

for(int cont = 0; cont<pr.cg.getColunas().size(); cont++){

    colun+=pr.cg.getColunas().get(cont)+",";

    final int pc=pr.cg.getColunas().size()-1-cont;
    inicioC++;
    new DialogoEntradaTextoIndex(pr,"Preencha o valor para coluna "+pr.cg.getColunas().get(pr.cg.getColunas().size()-1-cont),
            pc){

        @Override
        public void acaoRetorno(String ret, int index) {
             dados[index]=ret;
             inicioC--;
        }
    };

}
new Tr(pr, colun){
    @Override
    public void Durante(){
        while(inicioC!=0){

            tempo(200);
        }
        StringBuffer sql=new StringBuffer();
        for(String v:dados){

            sql.append("'"+v+"',");

        }
        setN1(sql.toString());
        setN2(paramet[0]+"");
    }


    @Override
    public void Depois(){


        new Tr(true,pr,"Inserindo dados!","Aguarde!","insert into "
                     +table+" ("+getN2().substring(0,getN2().length()-1)+") " +
                " values ("+getN1().substring(0,getN1().length()-1)+")"){
            @Override
            public void Durante(){

                setUsoGeralB( pr.cg.executeUpdateL(paramet[2    ]+""));
            }
            @Override
            public void Depois(){
                if(getUsoGeralB()==false){
                    pr.Mensagem("Não é possivel adicionar linhas a esta tabela!",
                            "Exige insert com regras (AutoIncremente por exemplo)!");
                    return;
                }

if(query.size()==0){
    new Tr(pr){
        @Override
        public void Durante(){
            setObjetosd( pr.cg.retornaTodaSql(banco,table));


        }
        @Override
        public void Depois(){

            new ListaQuery(pr,R.layout.listageral,banco,table,getObjetosd());
        }



    };


    return;
}
                for (int c=0;c<query.size();c++) {

                    System.gc();
                    ArrayList<EditTextX> linhas=new ArrayList<EditTextX>();
                    TableRow tr = new TableRow(pr);
                    for (int cont = 0; cont < query.get(c).length; cont++) {

                        EditTextX tx =  new EditTextX(pr);
                        linhas.add(tx);

                        tx.setText(dados[cont] );
                        tx.setValorStr(dados[cont]);
                        tx.setLinhas(linhas);
                        tx.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {


                                StringBuffer stb=new StringBuffer();
                                String set="";
                                for(int cont=0;cont<tx.getLinhas().size();cont++){


                                    if(!tx.getLinhas().get(cont).getValorStr().contentEquals("null"))
                                    {
                                        stb.append(pr.cg.getColunas().get(cont)+" = '"+tx.getLinhas().get(cont).getValorStr()+"'  and " );
                                    }

                                    if(tx.getLinhas().get(cont).equals(tx))
                                    {
                                        set=" SET "+pr.cg.getColunas().get(cont)+" = '"+tx.getText().toString()+"' ";
                                        tx.setValorStr(tx.getText().toString());
                                    }

                                }



                                new Tr(pr,set){
                                    @Override
                                    public void Durante() {
                                                /*pr.Mensagem("","UPDATE " +banco+"."+table+
                                                        " " +paramet[0]+
                                                        ( (stb.toString().length()>4)?    " WHERE "+stb.toString().substring(0,stb.toString().length()-4):""));
                                               */
                                        try {
                                            pr.cg.executeUpdateL("UPDATE " + banco + "." + table +
                                                    " " + paramet[0] +
                                                    ((stb.toString().length() > 4) ? " WHERE " + stb.toString().substring(0, stb.toString().length() - 4) : ""));

                                        }catch (Exception e){}
                                    }
                                };




                            }
                        });

                        tr.addView(tx);


                    }
                    if(c==0) {
                        Button delete = new Button(pr);
                        delete.setText("Remover");
                        tr.addView(delete);
                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                StringBuffer stb = new StringBuffer();
                                String set = "";
                                for (int cont = 0; cont < linhas.size(); cont++) {


                                    if (!linhas.get(cont).getValorStr().contentEquals("null")) {
                                        stb.append(pr.cg.getColunas().get(cont) + " = '" + linhas.get(cont).getValorStr() + "'  and ");
                                    }


                                }

                                new Tr(pr) {
                                    @Override
                                    public void Durante() {
                                        try {
                                           setN1( pr.cg.executeUpdateS("DELETE FROM "   + table +
                                                    ((stb.toString().length() > 4) ? " WHERE " +
                                                            stb.toString().substring(0, stb.toString().length() - 4) : "")));
                                        }catch (Exception e){}

                                    }

                                    @Override
                                    public void Depois() {

                                        tbl.removeView(tr);

                                    }
                                };

                            }
                        });
                    }else{

                        TextView delete = new TextView(pr);
                        delete.setText("Ações");
                        tr.addView(delete);
                    }
                    new Tr(pr){
                        @Override
                        public void Depois() {
                            System.gc();
                            tbl.addView(tr,3);
                        }
                    };
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    break;
                }



            }
        };

    }

};

//pr.a4.executeUpdateL(" insert into "
//+banco+"."+table+" ("+colun+") values ("+dados+") ;");

        }
    });

     dr.addView(bt);
     tbl.addView(dr);












    //Sesão normal
                new Tr(pr){
                    @Override
                    public void Durante() {

                        for (int c=0;c<query.size();c++) {

                            System.gc();
                                ArrayList<EditTextX> linhas=new ArrayList<EditTextX>();
                            TableRow tr = new TableRow(pr);
                            for (int cont = 0; cont < query.get(c).length; cont++) {

                                 EditTextX tx =  new EditTextX(pr);

                                if(c==0){


tx.setEnabled(false);
tx.setTextSize(20);
                                }
                                linhas.add(tx);

                                tx.setText((query.get(c)[cont] + "").contentEquals("null ")?"":(query.get(c)[cont] + ""));
                                tx.setValorStr((query.get(c)[cont] + "").substring(0,(query.get(c)[cont] + "").length()-1));
                                tx.setLinhas(linhas);
                                tx.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {


                                        StringBuffer stb=new StringBuffer();
                                        String set="";
                                        for(int cont=0;cont<tx.getLinhas().size();cont++){


                                            if(!tx.getLinhas().get(cont).getValorStr().contentEquals("null"))
                                            {
                                                stb.append(pr.cg.getColunas().get(cont)+" = '"+tx.getLinhas().get(cont).getValorStr()+"'  and " );
                                            }

                                            if(tx.getLinhas().get(cont).equals(tx))
                                            {
                                                set=" SET "+pr.cg.getColunas().get(cont)+" = '"+tx.getText().toString()+"' ";
                                                tx.setValorStr(tx.getText().toString());
                                            }

                                        }



                                      new Tr(pr,set){
                                            @Override
                                            public void Durante() {
                                                /*pr.Mensagem("","UPDATE " +banco+"."+table+
                                                        " " +paramet[0]+
                                                        ( (stb.toString().length()>4)?    " WHERE "+stb.toString().substring(0,stb.toString().length()-4):""));
                                               */
                                                        if((stb.toString().length()>4)) {
                                                            try {
                                                                pr.cg.executeUpdateL("UPDATE " + table +
                                                                        " " + paramet[0] +
                                                                        ((stb.toString().length() > 4) ? " WHERE " + stb.toString().substring(0, stb.toString().length() - 4) : ""));
                                                            }catch (Exception e){}
                                                        }
                                                        }
                                        };




                                    }
                                });

                                tr.addView(tx);


                            }
                            if(c!=0) {
                                Button delete = new Button(pr);
                                delete.setText("Remover");
                                tr.addView(delete);
                                delete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        StringBuffer stb = new StringBuffer();
                                        String set = "";
                                        for (int cont = 0; cont < linhas.size(); cont++) {


                                            if (!linhas.get(cont).getValorStr().contentEquals("null")) {
                                                stb.append(pr.cg.getColunas().get(cont) + " = '" + linhas.get(cont).getValorStr() + "'  and ");
                                            }


                                        }

                                        new Tr(pr) {
                                            @Override
                                            public void Durante() {
                                                if ((stb.toString().length() > 4)) {
                                                try {
                                                    pr.cg.executeUpdateL("DELETE FROM "   + table +
                                                            ((stb.toString().length() > 4) ? " WHERE " + stb.toString().substring(0, stb.toString().length() - 4) : ""));

                                                }catch (Exception e){}


                                                }
                                            }
                                            @Override
                                            public void Depois() {

                                                tbl.removeView(tr);

                                            }
                                        };

                                    }
                                });
                            }else{

                                TextView delete = new TextView(pr);
                                delete.setText("Ações");

                                delete.setTextSize(20);
                                tr.addView(delete);
                            }
                         new Tr(pr){
                             @Override
                             public void Depois() {
                                 System.gc();
                                 tbl.addView(tr);
                             }
                         };
                            try {
                                Thread.sleep(80);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                };



     if (pr.fabButton3 == null)
     {  pr.fabButton3 = new FloatingActionButton.Builder(pr)
             .withDrawable(pr.getResources()
                     .getDrawable(R.mipmap.voltar))
             .withButtonColor(Color.WHITE)
             .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
             .withMargins(5, 0, 5, 8)
             .create();

     }


     pr.fabButton3.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             new ListaTables(pr, R.layout.listageral,banco);
         }
     });
    }




    }




