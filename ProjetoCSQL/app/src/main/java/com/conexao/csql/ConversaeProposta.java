package com.conexao.csql;

import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.conexao.csql.Biblis.Datas;
import com.conexao.csql.Threads.Tr;
import com.conexao.csql.bdM.Linhas;
import com.conexao.csql.caixasdedialogo.DialogoExibicao;
import com.conexao.csql.caixasdedialogo.DialogoFazProposta;
import com.conexao.csql.caixasdedialogo.DialogoSimNao;
import com.conexao.csql.classesgenericas.Base;
import com.conexao.csql.classesgenericas.DataeHora;

public class ConversaeProposta extends Base {
    public boolean uni=false;
    Linhas l=null;
    public String produtoID=null;
    public int mLayout=0;
    public String compradorS=null;
    public boolean comprador=false;
    public ConversaeProposta(MainActivity pri,
                              int mLayout,
                              String produtoID, String compradorS ) {

        super(pri, mLayout);
        this.produtoID=produtoID;
        this.compradorS=compradorS;
        this.mLayout=mLayout;

        montaTela();



    }

    public void montaTela(){
        chamaTela();
        LinearLayout li=pr.findViewById(R.id.proposta);
        new Tr( pr){
            @Override
            public void Durante(){



                b.setLimit(5);
                l=b.slsW(b.msgproposta_IDproduto.Dado(produtoID));
                this.l=l;
                if(!l.getS(b.msgproposta_usuariovendedor).contentEquals(pr.usuarioLogado)){

                    l=b.slsW(b.msgproposta_IDproduto.Dado(produtoID),b.msgproposta_usuariocomprador.Dado(pr.usuarioLogado));

                    comprador=true;

                }else{

                    l=b.slsW(b.msgproposta_IDproduto.Dado(produtoID),
                            b.msgproposta_usuariovendedor.Dado(pr.usuarioLogado));
                }

                setN1(b.slsW(b.produto_ID.Dado(l.getS(b.msgproposta_IDproduto))).getS(b.produto_produto));
                setN2(b.slsW(b.produto_ID.Dado(l.getS(b.msgproposta_IDproduto))).getS(b.produto_fornecedor));
            }
            @Override
            public void Depois(){

                li.addView(new Proposta(l.getS(b.msgproposta_IDproduto),
                        l.getS(b.msgproposta_IDproduto)
                        , getN1(),
                        getN2(),
                        l.getSN(b.msgproposta_qtdsacas),
                        l.getSN(b.msgproposta_valor),
                        l.getSN(b.msgproposta_estadovendedor),
                        l.getSN(b.msgproposta_estadocomprador),
                        l.getSN(b.msgproposta_assinaturavendedor),
                        l.getSN(b.msgproposta_assinaturacomprador),
                        l.getSN(b.msgproposta_estatus),
                        l.getSN(b.msgproposta_ID)).pv);

            }
        };


        fdBt(R.id.enviar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg=fdEdS(R.id.msg);
                new Tr(pr){
                    @Override
                    public boolean Antes(){

                        setN1(fdEdS(R.id.msg));
                        return true;
                    }

                    @Override
                    public void Durante(){

                        b.insert(b.msgconversa_datetime.Dado(new DataeHora().getDateTime()),
                                b.msgconversa_IDproduto.Dado(produtoID),
                                b.msgconversa_usuarioDE.Dado(pr.usuarioLogado),
                                b.msgconversa_usuarioPARA.Dado(
                                        comprador?    b.slsW(b.produto_ID.Dado(produtoID)).getS(b.produto_usuario):compradorS),
                                b.msgconversa_msg.Dado(getN1()));
                    }

                    @Override
                    public void Depois(){
                        pr.hideKeyboardFrom(pr,pr.telaatual);
                        fdTx(R.id.msg).setText("");
                        carregaMsg();


                    }
                };

            }
        });


        fdImBt(R.id.ativarsom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg=fdEdS(R.id.msg);
                new Tr(pr){
                    @Override
                    public void Durante(){

               b.msgconversa_datetime.Dado(new DataeHora().getDateTime()) ;
                                b.msgconversa_IDproduto.Dado(produtoID) ;
                                b.msgconversa_usuarioDE.Dado(pr.usuarioLogado) ;
                                b.msgconversa_usuarioPARA.Dado(
                                        comprador?    b.slsW(b.produto_ID.Dado(produtoID)).getS(b.produto_usuario):compradorS);



                        Intent intent = new Intent(
                                MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                    pr.    startActivityForResult(intent, 112);

                    }

                    @Override
                    public void Depois(){
                        carregaMsg();

                    }
                };

            }
        });



        fdImBt(R.id.verproduto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new VerProduto(pr,R.layout.verprodutocompleto,produtoID);
            }
        });




new Tr(true,pr,"Aguarde!","Carregando mensagens!"){

    @Override
    public void Durante(){

        carregaMsg();
    }

};


    }


public void carregaNova()
{

    pr.hideKeyboardFrom(pr,pr.telaatual);
    fdTx(R.id.msg).setText("");

    carregaMsg();
}

    public void carregaMsg(){

        final  TableLayout tbl=pr.findViewById(R.id.conversa);

        tbl.removeAllViews();
        new Tr(pr){

            @Override
            public void Durante(){

                b.setOrderAsc();
                b.setOrderEsp(b.msgconversa_datetime);

                Linhas msg=           b.slsWOC(1, b.msgconversa_usuarioDE.Dado(pr.usuarioLogado),
                        b.msgconversa_usuarioPARA.Dado(pr.usuarioLogado),
                        b.msgconversa_IDproduto.Dado(produtoID)
                );
                l=msg;
            }

            @Override
            public void Depois(){
                while(l.next()){
                    TableRow tr=new TableRow(pr);
                    tr.setPadding(5,5,5,5);
                    if(l.getS(b.msgconversa_usuarioDE).contentEquals(pr.usuarioLogado)){

                        tr.setGravity(Gravity.LEFT);
                        TextView tx=new TextView(pr);
                        tx.setTextSize(18);

                        if(!l.getS(b.msgconversa_msg).contains("null"))
                        {
                            tx.setText(l.getS(b.msgconversa_msg));
                        }else{
                final byte[] audio=(byte[] )l.get(b.msgconversa_audio);
                            tx.setText("Audio");
                            tx.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    pr.ExecutaAudio(audio);
                                }
                            });
                        }

                        tx.setBackgroundColor(Color.LTGRAY);
                        tr.addView(tx);


                    }else{

                        tr.setGravity(Gravity.RIGHT);
                        TextView tx=new TextView(pr);
                        tx.setTextSize(18);
                        tx.setText(l.getS(b.msgconversa_msg));
                        tx.setBackgroundColor(Color.GREEN);
                        tr.addView(tx);
                    }
                    tbl.addView(tr);



                }

                ( (ScrollView) pr.findViewById(R.id.scrolll)).scrollTo(0,   tbl.getBottom()+20);
            }

        };









        // ( (ScrollView) pr.findViewById(R.id.scrolll)).fullScroll(View.FOCUS_DOWN);
    }

    public int getmLayout(){


        return mLayout;
    }
    class Proposta{
        public String ID=null;
        View pv=null;
        public Proposta(final String ID, final String codi, final String produto, final String fornecedor, final String
                quantidade, final String valor, String estadoVendedor, String estadoComprador,
                        final String assinaturaVendedor, final String assinaturaComprador, String estado,String IDproposta){

            pv= pr.getLayoutInflater().inflate(R.layout.dialogoprpostaestado, null);

            this.ID=ID;
            fdG(pv,R.id.titulo).setText("Proposta "+ID+" "+estado);

            fdG(pv,R.id.produto).setText(produto);
            fdG(pv,R.id.fornecedor).setText(fornecedor);
            fdG(pv,R.id.quantidade).setText(quantidade);
            fdG(pv,R.id.valor).setText(valor);

            fdG(pv,R.id.produto).setEnabled(false);
            fdG(pv,R.id.fornecedor).setEnabled(false);
            fdG(pv,R.id.quantidade).setEnabled(false);
            fdG(pv,R.id.valor).setEnabled(false);




            fdG(pv,R.id.estadocomprador).setText(estadoComprador);
            fdG(pv,R.id.estadovendedor).setText(estadoVendedor);

            fdG(pv,R.id.assinaturacomprador).setText("Sem assinatura");
            fdG(pv,R.id.assinaturavendedor).setText("Sem assinatura");


            if(comprador){

                fdG(pv,R.id.alterarvendedor).setVisibility(View.GONE);

            }else{
                fdG(pv,R.id.alterarcomprador).setVisibility(View.GONE);
            }


            if(estadoComprador.contentEquals("Aceita")){


                fdG(pv,R.id.estadocomprador).setTextColor(Color.BLUE);
                fdG(pv,R.id.alterarcomprador).setVisibility(View.GONE);

                fdG(pv,R.id.assinaturacomprador).setText("Assinado, clique para ver");
                fdG(pv,R.id.assinaturacomprador).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DialogoExibicao(pr,"Assinatura Comprador",assinaturaComprador);
                    }
                });
                //  fdG(pv,R.id.assinaturavendedor).setText(assinaturaVendedor);

            }else   if(estadoComprador.contentEquals("Recusado")){

                fdG(pv,R.id.estadocomprador).setTextColor(Color.RED);

                fdG(pv,R.id.alterarcomprador).setVisibility(View.GONE);

            }else{


                fdG(pv,R.id.estadocomprador).setTextColor(Color.YELLOW);

            }




            if(estadoVendedor.contentEquals("Aceita")){
                fdG(pv,R.id.alterarvendedor).setVisibility(View.GONE);

                fdG(pv,R.id.estadovendedor).setTextColor(Color.BLUE);

                fdG(pv,R.id.assinaturavendedor).setText("Assinado, clique para ver");
                fdG(pv,R.id.assinaturavendedor).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DialogoExibicao(pr,"Assinatura Vendedor",assinaturaVendedor);
                    }
                });

            }else   if(estadoVendedor.contentEquals("Recusado")){

                fdG(pv,R.id.estadovendedor).setTextColor(Color.RED);
                fdG(pv,R.id.alterarvendedor).setVisibility(View.GONE);
            }else{

                fdG(pv,R.id.estadovendedor).setTextColor(Color.YELLOW);

            }



            if(estadoVendedor.contentEquals("Aceita")&&
                    estadoComprador.contentEquals("Aceita")){

                if(!comprador) {

                        new Tr(pr){
                            @Override
                            public void Durante() {
      if(b.slsW(b.pg_usuario.Dado(pr.usuarioLogado),b.pg_propostaID.Dado(IDproposta)).numlinha()==0){
                                b.insert(b.pg_usuario.Dado(pr.usuarioLogado), b.pg_propostaID.Dado(IDproposta),
                                        b.pg_valorPg.Dado(Float.parseFloat(valor)*0.10),
                                        b.pg_estado.Dado("Pending"),
                                        b.pg_datetime.Dado(new Datas().getDateMysql(null)));
                            }
                            }
                        };

                new Tr(pr){

                    @Override
                    public void Durante() {
                        String estp=b.slsW(b.pg_propostaID.Dado(IDproposta)).getS(b.pg_estado);
                        setN1(estp);

                    }

                    @Override
                    public void Depois(){

                      //  Mensagem(IDproposta+"",""+getN1());
                        if(getN1().contains("null")||getN1().toLowerCase().contains("pendi"))
                            fdG(pv, R.id.fazerpagamento).setVisibility(View.VISIBLE);
                    }
                };

                    fdG(pv, R.id.imprimirpdf).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                          new ExportPDF(pr).geraPDF(IDproposta);

                        }
                    });


                    fdG(pv, R.id.fazerpagamento).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            new ListaPagamentos(pr );
                        }
                    });

                }else{

                    fdG(pv, R.id.estadopagamento).setVisibility(View.VISIBLE);
                    fdG(pv, R.id.estadopagamento).setText("Transação concluída");
                    fdG(pv,R.id.estadopagamento).setTextColor(Color.MAGENTA);



                }
                fdG(pv,R.id.titulo).setText("Proposta "+ID+" Finalizada");
                fdG(pv,R.id.titulo).setTextColor(Color.GREEN);
            }


            fdG(pv,R.id.alterarvendedor).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new DialogoSimNao(pr,"Deseja aceitar a proposta ? " +
                            "Se aceitar, estará assinando o contrato digital(Não poderá ser desfeito)"){

                        @Override
                        public void acaoSim(){
                            new Tr(pr){
                                @Override
                                public void Durante(){

                                    String chave=b.slsW(b.cadastro_usuario.Dado(pr.usuarioLogado)).getS(b.cadastro_assinatura);
                                    b.insertUp(b.ls(b.msgproposta_IDproduto.Dado(produtoID),
                                            b.msgproposta_usuariovendedor.Dado(pr.usuarioLogado)),
                                            b.msgproposta_usuariocomprador.Dado(compradorS),
                                            b.msgproposta_assinaturavendedor.Dado(chave),
                                            b.msgproposta_estadovendedor.Dado("Aceita"));
                                }

                                @Override
                                public void Depois(){

                                    new ConversaeProposta(pr,getmLayout(),ID, compradorS);

                                }

                            };




                        }
                        @Override
                        public void acaoNao(){

                            new Tr(pr){
                                @Override
                                public void Durante(){
                                    b.insertUp(b.ls(b.msgproposta_IDproduto.Dado(produtoID),
                                            b.msgproposta_usuariocomprador.Dado(compradorS),
                                            b.msgproposta_usuariovendedor.Dado(pr.usuarioLogado)),
                                            b.msgproposta_estadovendedor.Dado("Recusado"));

                                }

                                @Override
                                public void Depois(){
                                    new ConversaeProposta(pr,getmLayout(),ID,compradorS);


                                }

                            };




                        }

                    };

                }
            });


            fdG(pv,R.id.alterarcomprador).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new DialogoSimNao(pr,"Deseja aceitar a proposta ? " +
                            "se aceitar será necessário aguardar confirmação do vendedor"){

                        @Override
                        public void acaoSim(){
                            new Tr(pr){

                                @Override
                                public void Durante(){

                                    String chave=b.slsW(b.cadastro_usuario.Dado(pr.usuarioLogado)).getS(b.cadastro_assinatura);
                                    b.insertUp(b.ls(b.msgproposta_IDproduto.Dado(produtoID),
                                            b.msgproposta_usuariocomprador.Dado(pr.usuarioLogado)),
                                            b.msgproposta_assinaturacomprador.Dado(chave),
                                            b.msgproposta_estadocomprador.Dado("Aceita"));

                                    if(estadoVendedor.contains("Aceita")&&b.slsW(b.pg_usuario.Dado(pr.usuarioLogado),b.pg_propostaID.Dado(IDproposta)).numlinha()==0) {
                                        new Tr(pr) {
                                            @Override
                                            public void Durante() {
                                                b.insert(b.pg_usuario.Dado(pr.usuarioLogado), b.pg_propostaID.Dado(IDproposta),
                                                        b.pg_valorPg.Dado(Float.parseFloat(quantidade) * 0.10),
                                                        b.pg_estado.Dado("Sem pagamento"),
                                                        b.pg_datetime.Dado(new Datas().getDateMysql(null)));
                                            }
                                        };

                                    }

                                }


                                @Override
                                public void Depois(){

                                    new ConversaeProposta(pr,getmLayout(),ID,compradorS);


                                }
                            };

                        }
                        @Override
                        public void acaoNao(){

                            new Tr(pr){
                                @Override
                                public void Durante(){

                                    b.insertUp(b.ls(b.msgproposta_IDproduto.Dado(produtoID),
                                            b.msgproposta_usuariocomprador.Dado(pr.usuarioLogado)),
                                            b.msgproposta_estadocomprador.Dado("Recusado"));

                                }

                                @Override
                                public void Depois(){
                                    new ConversaeProposta(pr,getmLayout(),ID,compradorS);

                                }
                            };
                        }
                    }; }
            });


            if(comprador&&(
                    ! (estadoVendedor.contentEquals("Aceita")&&
                            estadoComprador.contentEquals("Aceita"))

            )){
                fdTx(R.id.fazerproposta).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Tr(pr){

                            @Override
                            public void Durante(){
                                new DialogoFazProposta(pr,l.getS(b.msgproposta_IDproduto),
                                        b.slsW(b.produto_ID.Dado(l.getS(b.msgproposta_IDproduto))).getS(b.produto_fornecedor),
                                        b.slsW(b.produto_ID.Dado(l.getS(b.msgproposta_IDproduto))).getS(b.produto_produto),
                                        l.getSN(b.msgproposta_qtdsacas),
                                        l.getSN(b.msgproposta_valor)){

                                    @Override
                                    public void acaoRetorno(String ID, String fornecedor, String produto, String quantidade, String valor){
                                        new Tr(pr){
                                            @Override
                                            public void Durante(){
                                                b.dW(b.msgproposta_IDproduto.Dado(ID),
                                                        b.msgproposta_usuariocomprador.Dado(pr.usuarioLogado));
                                                b.insert(b.msgproposta_IDproduto.Dado(ID),b.msgproposta_data.Dado(new DataeHora().getDateTime()),
                                                        b.msgproposta_usuariocomprador.Dado(pr.usuarioLogado),
                                                        b.msgproposta_usuariovendedor.Dado(b.slsW(b.produto_ID.Dado(ID)).getS(b.produto_usuario)),
                                                        b.msgproposta_estatus.Dado("Aguardando"),
                                                        b.msgproposta_estadocomprador .Dado("Aguardando"),
                                                        b.msgproposta_estadovendedor.Dado("Aguardando"),
                                                        b.msgproposta_qtdsacas.Dado(quantidade),
                                                        b.msgproposta_valor.Dado(valor));

                                            }
                                            @Override
                                            public void Depois(){
                                                new ConversaeProposta(pr,R.layout.msgproduto,ID,pr.usuarioLogado);



                                            }


                                        };



                                    }

                                };

                            }

                        };
                    }
                });


            }else{

                fdTx(R.id.fazerproposta).setVisibility(View.GONE);
            }

        }

    }

}
