package com.conexao.csql;

import android.view.View;
import android.widget.ImageButton;

import com.conexao.csql.Threads.Tr;
import com.conexao.csql.bdM.Linhas;
import com.conexao.csql.caixasdedialogo.DialogoExibicao;
import com.conexao.csql.caixasdedialogo.DialogoMapa;
import com.conexao.csql.caixasdedialogo.DialogoSimNao;
import com.conexao.csql.classesgenericas.Base;
import com.google.android.gms.maps.model.LatLng;

public class VerProduto extends Base {

public String textoCifFob="FOB";

public String ID="";
    public VerProduto(MainActivity pri, int mLayout,String ID) {
        super(pri, mLayout);
        this.ID=ID;
montaTela();



    }

    public void montaTela(){
        chamaTela();



        new Tr(pr){
          @Override
          public void Durante(){
l=b.slsW(b.cadastro_usuario.Dado(pr.usuarioLogado)) ;

          }
            @Override
            public void Depois(){

                fdEd(R.id.fornecedor).setText(l.getS(b.cadastro_nome));

            }

        };
        new Tr(pr){
            @Override
            public void Durante(){
                l=b.slsW(b.produto_ID.Dado(ID));

            }

            @Override
            public void Depois(){




                textoCifFob=l.getS(b.produto_localtexto);
                if(textoCifFob.contains("CIF")) {
                    fdTx(R.id.textolocal).setVisibility(View.GONE);
                    fdTx(R.id.localarmazem).setVisibility(View.GONE);
                }
                //CIF ou FOB

                fdRd(R.id.cif).setChecked(textoCifFob.contains("CIF"));
                fdRd(R.id.cif).setEnabled(false);
                fdRd(R.id.fob).setChecked(textoCifFob.contains("FOB"));
                fdRd(R.id.fob).setEnabled(false);
                fdTx(R.id.dataretiradaentrega).setText((textoCifFob.contains("FOB"))?"Data de retirada":"Data de entrega");


//CIF vai entregar FOB com google maps
//Selecionar produto
                fdBt(R.id.selecionarproduto).setText(l.getS(b.produto_produto));


//setar Fornecedor


                fdEd(R.id.fornecedor).setEnabled(false);


//Selecionar tipo tratamento
                fdBt(R.id.tipotratamento).setText(l.getS(b.produto_tipotratamento));

                //Data entrega
                fdBt(R.id.dataentrega).setText(l.getS(b.produto_dataentregaeretirada));



//Data ideal pagamento
                fdBt(R.id.dataidealpagamento).setText(l.getS(b.produto_dataidealpagamento));


//Selecionar dados de recolhimento
                if(l.getS(b.produto_dadosrecolhimento).contains("Opção 1"))
                    fdBt(R.id.dadosrecolhimento).setText("Opção 1");
                else
                    fdBt(R.id.dadosrecolhimento).setText("Opção 2");

                final Linhas fp=l;
                fdBt(R.id.dadosrecolhimento).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new DialogoExibicao(pr, fdBt(R.id.dadosrecolhimento).getText().toString(),
                                fp.getS(b.produto_dadosrecolhimento)){




                        };

                    }
                });


//Selecionar dados de recolhimento
                if(textoCifFob.contains("FOB")) {

                    fdBt(R.id.localarmazem).setText(l.getS(b.produto_localarmazem));
                    fdBt(R.id.localarmazem).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {



                            pr.opMap=3;
                            pr.uL=new LatLng(fp.getD(b.produto_localarmazem_lat),
                                    fp.getD(b.produto_localarmazem_long));
                            if(pr.di!=null){

                                pr.di.updateView("Local "+fp.getS(b.produto_localarmazem));
                                pr.  di.show();
                                return;
                            }
                            pr. di=  new DialogoMapa(pr,"Local "+fp.getS(b.produto_localarmazem)){
                                @Override
                                public void acaoRetorno(LatLng ln, String end){
                                    pr.latlng=ln;

                                }

                            };

                        }
                    });

                }else{
                    fdBt(R.id.localarmazem).setVisibility(View.GONE);



                }
                //Quem pode ver


                fdBt(R.id.quempodever).setText(l.getS(b.produto_quempodever));


                fdEd(R.id.fornecedor).setText(pr.usuarioLogado);



                if(pr.usuarioLogado.contentEquals(l.getS(b.produto_usuario))) {
                    ((ImageButton) pr.findViewById(R.id.remover)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            new DialogoSimNao(pr, "Deseja realmente remover? (Não poderá ser desfeito)") {

                                @Override
                                public void acaoSim() {


                                    new Tr(pr){
                                        @Override
                                        public void Durante(){
                                            b.dW(b.produto_usuario.Dado(pr.usuarioLogado),
                                                    b.produto_ID.Dado(ID));

                                            Mensagem("Remoção", "Item removido com sucesso!");

                                        }

                                        @Override
                                        public void Depois(){
                                            new Listaprodutos(pr, R.layout.listaprodutos, true);

                                        }

                                    };


                                }


                            };
                        }
                    });

                }else{

                    ((ImageButton) pr.findViewById(R.id.remover)).setVisibility(View.GONE);

                }

            }

        };


    }





}
