package com.conexao.csql;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.conexao.csql.Banco.ConexaoG;
import com.conexao.csql.Banco.ConexaoMysql5;
import com.conexao.csql.Banco.ConexaoPostGree;
import com.conexao.csql.MenuFloat.FloatingActionButton;
import com.conexao.csql.Threads.Tr;
import com.conexao.csql.bdL.BancoLT;
import com.conexao.csql.bdL.LinhasL;
import com.conexao.csql.caixasdedialogo.DialogoMapa;
import com.conexao.csql.classesgenericas.Base;

public class ListaConexoes extends Base {
public DialogoMapa di=null;
BancoLT lt =null;
public String id="";

public boolean direto=false;
    public ListaConexoes(MainActivity pri, int mLayout   ) {
        super(pri, mLayout);
        this.lt =pri.l;
pr.onde=0;
        montaTela();


    }

    public void montaTela(){


pr.setContentView(R.layout.listaconexoes);


        LinhasL r= lt.sls(lt.banco);
        if(pr.cg!=null)
        pr.cg.getBancos().removeAll(pr.cg.getBancos());

     TableLayout tbl = pr.findViewById(R.id.tabela);
        tbl.removeAllViews();
        {
            TableRow tr = new TableRow(pr);
            TextView id = new TextView(pr);
            id.setText("ID     ");
            id.setTextSize(14);
            tr.addView(id);
            TextView ft = new TextView(pr);
            ft.setText("TIPO      ");
            ;
            ft.setTextSize(14);
            tr.addView(ft);
            TextView host = new TextView(pr);
            host.setText("HOST     ");
            ;
            host.setTextSize(14);

            tr.addView(host);
            TextView banco = new TextView(pr);
            banco.setText("BANCO     ");
            ;
            banco.setTextSize(14);

            tr.addView(banco);
            TextView remover = new TextView(pr);
            remover.setText("AÇÔES");
            remover.setTextSize(14);

            tr.addView(remover);


            tbl.addView(tr);
        }
 while (r.next()){

     TableRow tr=new TableRow(pr);
     TextView id=new TextView(pr);
     id.setText(r.getS(lt.banco_id));
     tr.addView(id);

     Button host=new Button(pr);
     host.setText(r.getS(lt.banco_host));;
     tr.addView(host);
     TextView banco=new TextView(pr);
     banco.setText(r.getS(lt.banco_nomebanco));;
     tr.addView(banco);
     TextView ft=new TextView(pr);
     ft.setText(r.getS(lt.banco_fonte));;
     tr.addView(ft);
     Button remover=new Button(pr);
      remover.setText("Remover");
     tr.addView(remover);


     host.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(ft.getText().toString().contentEquals(ConexaoG.fontMysql)){

                 click(new ConexaoMysql5(), id.getText().toString());

             }
             if(ft.getText().toString().contentEquals(ConexaoG.fontPostGreeSql)){
                 click(new ConexaoPostGree(),id.getText().toString());

             }
         }
     });

     remover.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             lt.dW(lt.banco_id.Dado(id.getText().toString()));
             tbl.removeView(tr);
         }
     });
tbl.addView(tr);
}

        pr.fabButton3=null;

        FloatingActionButton  fabButton3 = new FloatingActionButton.Builder(pr)
                .withDrawable(pr.getResources()
                        .getDrawable(R.drawable.addnew))
                .withButtonColor(Color.WHITE)
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(5, 0, 5, 8)
                .create();

 fabButton3.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {

         pr.iniLogin("-1",false);
     }
 });

    }


    public    void click(ConexaoG cgl, String id) {

 LinhasL r=lt.slsW(lt.banco_id.Dado(id));


        new Tr(true,pr,"Iniciando conexão!","Estabelecendo conexão, atualizando  lista de bancos!"){
            @Override
            public void Durante(){

                setUsoGeralB(pr.ConectaG(cgl,r.getS(lt.banco_nomebanco),
                        r.getS(lt.banco_host),  r.getS(lt.banco_porta),
                        r.getS(lt.banco_usuario),
                        r.getS(lt.banco_senha)))  ;
                if(getUsoGeralB())
                {

                    pr.cg.setBancos(r.getS(lt.banco_nomebanco));
                }
            }
            @Override
            public void Depois(){



                if(getUsoGeralB())
                { pr.setContentView(R.layout.listageral);
                    new ListaBancos(pr, R.layout.listageral);
                }

            }

        };
    }
}
