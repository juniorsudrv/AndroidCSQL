package com.conexao.csql;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.conexao.csql.MenuFloat.FloatingActionButton;
import com.conexao.csql.Threads.Tr;
import com.conexao.csql.bdL.BancoLT;
import com.conexao.csql.caixasdedialogo.DialogoEntradaTexto;
import com.conexao.csql.caixasdedialogo.DialogoMapa;
import com.conexao.csql.classesgenericas.Base;

public class ListaTables extends Base {
public DialogoMapa di=null;
BancoLT lt =null;
String banco;
    public ListaTables(MainActivity pri, int mLayout,String banco ) {
        super(pri, mLayout);
        this.lt =pri.l;
        this.banco=banco;
        montaTela();
        pr.onde=2;


    }

 public void montaTela(){
 chamaTela();

     TableLayout tbl=pr.findViewById(R.id.tabela);

    tbl.removeAllViews();
TableRow ta=new TableRow(pr);
Button ex=new Button(pr);

ex.setText("Inserir Comando (Exceto consulta)!");
ex.setTextSize(12);
ex.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        new DialogoEntradaTexto(pr,"Digite seu comando!"){
            @Override
            public void acaoRetorno(String ret) {
                new Tr(true,pr,"Executando comando!","Aguarde!"){
                    @Override
                    public void Durante(){

                        setUsoGeralB( pr.cg.executeUpdateL(ret));;
                        if(getUsoGeralB()&&ret.contains("create"))
                        pr.cg.setTabelas(pr.cg.retornaTabelas(banco));
                    }

                    @Override
                    public  void Depois(){

                        if(!getUsoGeralB()){
                            pr.Mensagem("Erro!",pr.cg.getErro());
                        }else{
if(ret.contains("create")){

    new ListaTables(pr,R.layout.listageral,banco);
    return;
}

                        }
                    }

                };
            }
        };

    }
});
ta.addView(ex);
tbl.addView(ta);
     montaTitulo(tbl,"TABELAS");


     new Tr(false,pr,"Aguarde a conexão!","Puxando lista de tabelas do banco "+banco+"!"){



         @Override
         public void Durante(){

             for(String tabelas:pr.cg.getTabelas()){

                 TableRow tr=new TableRow(pr);
               final  Button table=new Button(pr);

                 table.setText(tabelas);

                 table.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {



                         new Tr(true,pr,"Aguardando dados da conexão!","Fazendo consulta!"){

                             @Override
                             public void Durante(){
                                 setObjetosd( pr.cg.retornaTodaSql(banco,table.getText().toString()));
                             }
                             @Override
                             public void Depois(){

                                 new ListaQuery(pr,R.layout.listageral,banco,table.getText().toString(),getObjetosd());
                             }


                         };


                     }
                 });

                 Button remover=new Button(pr);
                 remover.setText("Remover");
                 remover.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                         new Tr(true,pr,"Executando comando!","Aguarde!"){
                             @Override
                             public void Durante(){

                           setUsoGeralB( pr.cg.executeUpdateL("drop table "+table.getText()));;
                             }

                             @Override
                             public  void Depois(){

                               if(!getUsoGeralB()){
                                 pr.Mensagem("Erro!",pr.cg.getErro());
                            }else{
                                   tbl.removeView(tr);

                               }
                             }

                         };
                     }
                 });
                 tr.addView(table);
                 tr.addView(remover);
new Tr(pr){

    @Override
    public void Depois(){


        tbl.addView(tr);
    }

};

             tempo(80);
             }



         }

         @Override
         public void Depois(){
tempo(800);
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
             new ListaBancos(pr,R.layout.listageral);
         }
     });
    }



}
