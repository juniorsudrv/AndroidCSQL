package com.conexao.csql;

import android.view.View;
import android.widget.LinearLayout;

import com.conexao.csql.Threads.Tr;
import com.conexao.csql.classesgenericas.Base;

public class ListaPropMsg extends Base {
    public boolean uni=false;

public int mLayout=0;
public boolean comprador=false;
public String produtoID=null;
    public ListaPropMsg(MainActivity pri,
                        int mLayout  , String produtoID) {

        super(pri, mLayout);
this.produtoID=produtoID;
       this.mLayout=mLayout;

        montaTela();



    }

    public void montaTela(){
        chamaTela();



   LinearLayout li=pr.findViewById(R.id.conversa);
new Tr(pr){
  @Override
  public void Durante(){

        l=produtoID==null?b.slsWO(b.msgproposta_usuariocomprador.Dado(pr.usuarioLogado),
              b.msgproposta_usuariovendedor.Dado(pr.usuarioLogado)):
              b.slsWOC(1,b.msgproposta_usuariocomprador.Dado(pr.usuarioLogado),
                      b.msgproposta_usuariovendedor.Dado(pr.usuarioLogado),
                      b.msgproposta_IDproduto.Dado(produtoID));
   }
   @Override
    public void Depois(){

       while(l.next()) {
           li.addView(new Msg(l.getS(b.msgproposta_IDproduto),
                   l.getS(b.msgproposta_usuariocomprador)).pv);
       }
   }

};



    }


public int getmLayout(){


        return mLayout;
}
class Msg{
public String ID=null;
      View pv=null;
      public Msg(final String ID,final String comprador){

          pv= pr.getLayoutInflater().inflate(R.layout.msgdalista, null);

          fdG(pv,R.id.codigo).setText(ID);
          fdG(pv,R.id.comprador).setText(comprador);

          fdG(pv,R.id.codigo).setEnabled(false);
          fdG(pv,R.id.comprador).setEnabled(false);
          fdGIv(pv,R.id.vermsg).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
             pr.cepr=     new ConversaeProposta(pr,R.layout.msgproduto,ID,comprador);
              }
          });
  }

}

}
