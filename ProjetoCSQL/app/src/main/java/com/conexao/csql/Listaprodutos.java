package com.conexao.csql;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;


import com.conexao.csql.Threads.Tr;
import com.conexao.csql.caixasdedialogo.DialogoFazProposta;
import com.conexao.csql.caixasdedialogo.DialogoMapa;
import com.conexao.csql.caixasdedialogo.DialogoSimNao;
import com.conexao.csql.classesgenericas.Base;
import com.conexao.csql.classesgenericas.DataeHora;
import com.google.android.gms.maps.model.LatLng;

public class Listaprodutos extends Base {
    public boolean uni=false;

    public Listaprodutos(MainActivity pri,
                         int mLayout,
                         boolean uni) {

        super(pri, mLayout);
this.uni=uni;

        montaTela();



    }

    public void montaTela(){
        chamaTela();



        LinearLayout li=pr.findViewById(R.id.layout);
   new Tr(pr){
     @Override
     public void Durante(){

           l=!uni?b.sls(b.produto):b.slsW(b.produto_usuario.Dado(pr.usuarioLogado));

     }

    @Override
    public void Depois(){

        while(l.next()) {
        li.addView(new Produto(l.getS(b.produto_ID),
                l.getS(b.produto_ID),l.getS(b.produto_usuario),l.getS(b.produto_produto),
                l.getS(b.produto_fornecedor),l.getS(b.produto_qtdsacas),
                l.getS(b.produto_vlrporsaca),l.getS(b.produto_localtexto).contains("CIF"),
                l.getS(b.produto_localarmazem),
                l.getD(b.produto_localarmazem_lat),
                l.getD(b.produto_localarmazem_long)).pv);
    }

    }








   };





    }



class Produto{
public String ID=null;
      View pv=null;
      public Produto(final String ID,final String codi,final String usuario,final String produto,final String fornecedor,final String
                     quantidade,final String valor, boolean cif,
                  final   String endretirada, final double lat, final double log){
          pv= pr.getLayoutInflater().inflate(R.layout.produto, null);

this.ID=ID;
          if(!uni)
          {
              pv.findViewById (R.id.remover).setVisibility(View.GONE);


          }else{

              ((ImageButton)pv.findViewById (R.id.remover)).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      new DialogoSimNao(pr,"Deseja realmente remover? (Não poderá ser desfeito)"){

                          @Override
                          public void acaoSim(){

                              new Tr(pr){
                                @Override
                                public void Durante(){
                                    b.dW(b.produto_usuario.Dado(pr.usuarioLogado),
                                            b.produto_ID.Dado( ID));
   }
   @Override
    public void Depois(){
       Mensagem("Remoção","Item removido com sucesso!");
       new Listaprodutos(pr,R.layout.listaprodutos,true);

   }

                              };
      }
                      };
                  }
              });
          }

          fdG(pv,R.id.codigo).setText("Nr "+codi);
    if(lat!=0&&log!=0)
    {


        fdGIb(pv,R.id.exibemapa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               pr.opMap=3;
                pr.uL=new LatLng(lat, log);
                if(pr.di!=null){

pr.di.updateView("Local "+endretirada);
                  pr.  di.show();
                    return;
                }
               pr. di=  new DialogoMapa(pr,"Local "+endretirada){
                    @Override
                    public void acaoRetorno(LatLng ln, String end){
                        pr.latlng=ln;

                    }

                };
            }
        });
    }else{

      //  fdGIb(pv,R.id.exibemapa).setVisibility(View.GONE);

    }
          fdG(pv,R.id.produto).setText(produto);
          fdG(pv,R.id.fornecedor).setText(fornecedor);
          fdG(pv,R.id.quantidade).setText(quantidade);
          fdG(pv,R.id.valor).setText(valor);
          fdG(pv,R.id.enderecoescrito).setText(endretirada);

          if(cif){

              fdG(pv,R.id.ciffob).setText("CIF");
              fdG(pv,R.id.ciffob).setBackgroundColor(Color.YELLOW);

          }else{

              fdG(pv,R.id.ciffob).setText("FOB");
              fdG(pv,R.id.ciffob).setBackgroundColor(Color.BLUE);
              fdG(pv,R.id.endereco).setText("Endereço de entrega");
          }

          fdGIb(pv,R.id.compartilhar).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent sendIntent = new Intent();
                  sendIntent.setAction(Intent.ACTION_SEND);
                  String texto = "PROJETO SCAS\n" +
                         "Produto : " +produto+"\n"+
                   "Fornecedor : "+fornecedor+"\n"+
                  "Quantidade : "+quantidade+"\n"+
                  "Valor : "+valor+"\n"
                  +"End. Retirada : "+endretirada+"\n Para saber mais acesse : www";
                  sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
                  sendIntent.setType("text/plain");
                 pr. startActivity(sendIntent);
              }
          });

          fdGIb(pv,R.id.vertudo).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {


                  new VerProduto(pr,R.layout.verprodutocompleto,ID);
              }
          });

          fdGIb(pv,R.id.mensagens).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  new Tr(pr){
                      @Override
                      public void Durante(){
setUsoGeralB(b.slsW(b.msgproposta_IDproduto.Dado(ID),
        b.msgproposta_usuariocomprador.Dado(pr.usuarioLogado)).numlinha()==0&&
        ! usuario.contentEquals(pr.usuarioLogado));
                  }

                  @Override
                      public void Depois(){

                      if(getUsoGeralB()) {



                          new DialogoFazProposta(pr,ID,fornecedor,produto,quantidade,valor){

                              @Override
                              public void acaoRetorno(String ID, String fornecedor, String produto, String quantidade, String valor){
new Tr(pr){

    @Override
    public void Durante(){

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
                      }else{

                          new ListaPropMsg(pr,R.layout.listamsgs,ID);
                      }

                  }
};

              }
          });

  }

}

}
