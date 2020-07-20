package com.conexao.csql;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.conexao.csql.Threads.Externo;
import com.conexao.csql.Threads.Tr;
import com.conexao.csql.caixasdedialogo.DialogoSimNao;
import com.conexao.csql.classesgenericas.Base;

public class ListaPagamentos extends Base {



    public ListaPagamentos(MainActivity pr) {
        super(pr, R.layout.pagamentos);
        montaTela();
    }






    public void montaTela(){
      chamaTela();
new Tr(pr){
    @Override
    public void Durante() {

        b.setOrderEsp();
        b.setOrderEsp(b.pg_datetime);
        this. l=b.slsW(b.pg_usuario.Dado(pr.usuarioLogado));

    }

    @Override
    public void Depois() {

        TableLayout tbl=(TableLayout)pr.findViewById(R.id.tabela);
tbl.removeAllViews();
        TableRow trG=new TableRow(pr);
        trG.setGravity(Gravity.CENTER);
        TextView propostaG=new TextView(pr);
        propostaG.setText("IDProposta");
        trG.addView(propostaG);
        TextView valorG=new TextView(pr);
        valorG.setText("Valor");
        trG.addView(valorG);
        TextView estadoG=new TextView(pr);
        estadoG.setText("Situação");
        trG.addView(estadoG);
        TextView fazerpagamentoG=new TextView(pr);
          fazerpagamentoG.setText( "Opções");
        trG.addView(fazerpagamentoG);
        tbl.addView(trG);


        while(this.l.next()){

            TableRow tr=new TableRow(pr);
            int pd=5;
            tr.setPadding(pd,pd,pd,pd);
            tr.setGravity(Gravity.CENTER);
          final  Button proposta=new Button(pr);
            proposta.setPadding(pd,pd,pd,pd);
            proposta.setText(l.getS(b.pg_propostaID));
            tr.addView(proposta);
           final TextView valor=new TextView(pr);
            valor.setPadding(pd,pd,pd,pd);
            valor.setText(l.getS(b.pg_valorPg));
            tr.addView(valor);
            TextView estado=new TextView(pr);
            estado.setPadding(pd,pd,pd,pd);
            estado.setText(l.getS(b.pg_estado));
            tr.addView(estado);
            Button fazerpagamento=new Button(pr);
            fazerpagamento.setPadding(pd,pd,pd,pd);
            boolean est=estado.getText().toString().toLowerCase().contains("pendi")||estado.getText().toString().contains("null");
            fazerpagamento.setText(est?"Pagar":"Pagamento concluído");
            if(est)
            tr.addView(fazerpagamento);

            tbl.addView(tr);

            final String idPf=l.getS(b.pg_preferenceID);


            fazerpagamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(idPf.contains("null")){

                        new Tr(true,pr,"Gerando seu boleto!","Acessando servidor aguarde um momento!"){

                            @Override
                            public void Durante(){

                       String email=b.slsW(b.cadastro_usuario.Dado(pr.usuarioLogado)).getS(b.cadastro_email);
                       String pfId=   new Externo().geraIdPf(email,valor.getText().toString(),
                                        "Pagamento referente ao contrato "+proposta.getText() );

                             if(pfId==null){
                                 pr.Mensagem("Erro na conexão","Estamos com problemas para acessar o servidor, tente novmante mais tarde");

                             } else{

                                 pr.propostaIDC=proposta.getText().toString();

                  b.insertUp(b.ls(b.pg_propostaID.Dado(proposta.getText() )),
                                     b.pg_preferenceID.Dado(pfId));

                    setN1(pfId);

                             }
                            }
                            @Override
                            public void finalTr(){
                            //  Mensagem("","'"+getN1()+"'");
                               pr.cobrarPagamento(getN1());

                            }

                        };


                    }else{

                        new DialogoSimNao(pr,"Já existe uma cobrança, deseja gerar um nova apagando a anterior? ")

                        {
                            @Override
                            public void acaoSim(){
                                new Tr(true,pr,"Gerando seu boleto!","Acessando servidor aguarde um momento!"){

                                    @Override
                                    public void Durante(){

                                        String email=b.slsW(b.cadastro_usuario.Dado(pr.usuarioLogado)).getS(b.cadastro_email);
                                        String pfId=   new Externo().geraIdPf(email,valor.getText().toString(),
                                                "Pagamento referente ao contrato "+proposta.getText() );

                                        if(pfId==null){
                                            pr.Mensagem("Erro na conexão","Estamos com problemas para acessar o servidor, tente novmante mais tarde");

                                        } else{

                                            pr.propostaIDC=proposta.getText().toString();

                                            b.insertUp(b.ls(b.pg_propostaID.Dado(proposta.getText() )),
                                                    b.pg_preferenceID.Dado(pfId));

                                            setN1(pfId);

                                        }
                                    }
                                    @Override
                                    public void finalTr(){
                                        pr.cobrarPagamento(getN1());

                                    }

                                };

                            }

                            @Override
                            public void acaoNao(){


                                pr.cobrarPagamento(idPf);

                            }

                        };


                    }


                }
            });

        }
    }
};






    }
}
