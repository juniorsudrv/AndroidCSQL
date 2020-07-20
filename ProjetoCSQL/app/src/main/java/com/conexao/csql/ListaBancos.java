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
import com.conexao.csql.caixasdedialogo.DialogoMapa;
import com.conexao.csql.classesgenericas.Base;

public class ListaBancos extends Base {
public DialogoMapa di=null;
BancoLT lt =null;
    public ListaBancos(MainActivity pri, int mLayout ) {
        super(pri, mLayout);
        this.lt =pri.l;
montaTela();



    }
    int cont=0;

 public void montaTela() {
     chamaTela();
     pr.onde=1;
     TableLayout tbl = pr.findViewById(R.id.tabela);
     tbl.removeAllViews();
     montaTitulo(tbl, "BANCOS");
     new Tr(true, pr, "Montando Lista!", "Montando lista de bancos") {


         @Override
         public void Durante() {

             for (String bancostr : pr.cg.getBancos()) {
                 pr.cg.setBanco(bancostr);
                 TableRow tr = new TableRow(pr);
                 final Button banco = new Button(pr);

                 banco.setText(bancostr);


                 banco.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {


                         new Tr(true, pr, "Aguardando conexão!", "Atualizando Puxando Tabelas!") {

                             @Override
                             public void Durante() {
                                 pr.cg.setTabelas(pr.cg.retornaTabelas(banco.getText().toString()));

                                 tempo(900);
                             }

                             @Override
                             public void Depois() {


                                 new ListaTables(pr, R.layout.listageral, banco.getText().toString());
                             }


                         };

                     }
                 });
                 tr.addView(banco);

                 new Tr(pr) {

                     @Override
                     public void Durante(){
                         cont++;

                     }
                     @Override
                     public void Depois() {


                         tbl.addView(tr);

                         cont--;
                     }
                 };
             }

           while(cont>0){

               tempo(500);
           }
         }

         @Override
         public void Depois() {


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
             new ListaConexoes(pr, R.layout.listaconexoes);
         }
     });
 }



}
