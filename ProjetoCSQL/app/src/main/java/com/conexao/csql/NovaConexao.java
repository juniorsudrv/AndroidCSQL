package com.conexao.csql;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;

import com.conexao.csql.Banco.ConexaoG;
import com.conexao.csql.Banco.ConexaoMysql5;

import com.conexao.csql.Banco.ConexaoPostGree;
import com.conexao.csql.MenuFloat.FloatingActionButton;
import com.conexao.csql.MenuFloat.menuFloatV;
import com.conexao.csql.Threads.Externo;
import com.conexao.csql.Threads.Tr;
import com.conexao.csql.bdL.BancoLT;
import com.conexao.csql.bdL.LinhasL;
import com.conexao.csql.caixasdedialogo.DialogoMapa;
import com.conexao.csql.classesgenericas.Base;

import java.util.ArrayList;

public class NovaConexao extends Base {
public DialogoMapa di=null;
BancoLT lt =null;
public String id="";

public boolean direto=false;
    public NovaConexao(MainActivity pri, int mLayout, String id, boolean direto) {
        super(pri, mLayout);
        this.lt =pri.l;
this.id=id;
this.direto=direto;
        montaTela();
        pr.onde=-1;

    }

    public void montaTela(){


chamaTela();
        LinhasL r= lt.slsW(lt.banco_id.Dado(id));
 while(r.next()){



    fdEd(R.id.host).setText(r.getS(lt.banco_host));
    fdEd(R.id.nomebanco).setText(r.getS(lt.banco_nomebanco));
    fdEd(R.id.usuario).setText(r.getS(lt.banco_usuario));
    fdEd(R.id.senha).setText(r.getS(lt.banco_senha));
    fdEd(R.id.porta).setText(r.getS(lt.banco_porta));



}
    fdBt(R.id.logarmysql5).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          click(new ConexaoMysql5());

        }
    });


        fdBt(R.id.logarpostgree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(new ConexaoPostGree());

            }
        });





if(direto){

    if(r.getS(lt.banco_fonte).contentEquals(ConexaoG.fontMysql)){

        click(new ConexaoMysql5());

    }
    if(r.getS(lt.banco_fonte).contentEquals(ConexaoG.fontPostGreeSql)){
        click(new ConexaoPostGree());

    }
}



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

public    void click(ConexaoG cgl) {


    new Tr(true,pr,"Iniciando conexão!","Estabelecendo conexão, atualizando  lista de bancos!"){
        @Override
        public void Durante(){

            setUsoGeralB(pr.ConectaG(cgl,fdEd(R.id.nomebanco).getText().toString(),
                    fdEd(R.id.host).getText().toString(),  fdEd(R.id.porta).getText().toString(),
                    fdEd(R.id.usuario).getText().toString(),
                    fdEd(R.id.senha).getText().toString()))  ;
            if(getUsoGeralB())
            {

                pr.cg.getBancos().removeAll(pr.cg.getBancos());
                pr.cg.setBancos(fdEd(R.id.nomebanco).getText().toString());

            }
        }
        @Override
        public void Depois(){



            if(getUsoGeralB())
            {


                if(!direto) {
                    lt.insert(lt.banco_host.Dado(  fdEd(R.id.host).getText()),
                            lt.banco_nomebanco.Dado(   fdEd(R.id.nomebanco).getText()),
                            lt.banco_usuario.Dado(    fdEd(R.id.usuario).getText()),
                            lt.banco_senha.Dado(  fdEd(R.id.senha).getText()),
                            lt.banco_porta.Dado(  fdEd(R.id.porta).getText()),
                            lt.banco_fonte.Dado(cgl.getFonte()));
                }
                new ListaBancos(pr, R.layout.listageral);
            }

        }

    };
}

}
