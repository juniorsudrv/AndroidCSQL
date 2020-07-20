package com.conexao.csql;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;

import com.conexao.csql.Threads.Tr;
import com.conexao.csql.caixasdedialogo.DialogoMapa;
import com.conexao.csql.caixasdedialogo.DialogoOpcoesLabels;
import com.conexao.csql.caixasdedialogo.DialogoOpcoesLabelsMulti;
import com.conexao.csql.classesgenericas.Base;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LancarProdutos extends Base {

public String textoCifFob="FOB";
      String emp[];
    public LancarProdutos(MainActivity pri, int mLayout) {
        super(pri, mLayout);
montaTela();



    }

    public void montaTela(){
        chamaTela();


        b.lNull(b.produto);

        fdTx(R.id.textolocal).setVisibility(View.GONE);


   //CIF ou FOB
        fdRd(R.id.cif).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
textoCifFob=(isChecked)?"CIF":"FOB";
                fdTx(R.id.dataretiradaentrega).setText((isChecked)?"Data de retirada":"Data de entrega");

                fdTx(R.id.textolocal).setText((!isChecked)?"Local de retirada":"Local de entrega");

            }
        });
        fdRd(R.id.fob).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                textoCifFob=(!isChecked)?"CIF":"FOB";
               fdTx(R.id.dataretiradaentrega).setText((isChecked)?"Data de retirada":"Data de entrega");
                fdTx(R.id.textolocal).setText((isChecked)?"Local de retirada":"Local de entrega");




            }
        });


//CIF vai entregar FOB com google maps
//Selecionar produto
fdBt(R.id.selecionarproduto).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        new DialogoOpcoesLabels(pr,"Selecione seu produto",new String[]{"Soja","Milho","Feijão","Girassol", "Sorgo"}){

            @Override
            public void acaoRetorno(String r){


                b.produto_produto.Dado(r);
                fdBt(R.id.selecionarproduto).setText(r);
            }


        };

    }
});




//setar Fornecedor
        new Tr(pr){
            @Override
            public void Durante(){
             l=   b.slsW(b.cadastro_usuario.Dado(pr.usuarioLogado));

            }
            @Override
            public void Depois(){
                fdEd(R.id.fornecedor).setText(l.getS(b.cadastro_nome));


            }

        };


        b.produto_fornecedor.Dado(fdEdS(R.id.fornecedor));
        fdEd(R.id.fornecedor).setEnabled(false);


//Selecionar tipo tratamento
        fdBt(R.id.tipotratamento).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DialogoOpcoesLabels(pr,"Selecione o tipo de tratamento",new String[]{"Convencional NGMO",
                        "Transgenico RR1","Intacta RR2"}){

                    @Override
                    public void acaoRetorno(String r){


                        b.produto_tipotratamento.Dado(r);

                        fdBt(R.id.tipotratamento).setText(r);
                    }


                };

            }
        });

 //Data entrega
      fdBt(R.id.dataentrega).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                final int  mYear = c.get(Calendar.YEAR);
                final int  mMonth = c.get(Calendar.MONTH);
                final int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(pr,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                fdBt(R.id.dataentrega).setText(dayOfMonth+"/"+
                                        (monthOfYear+1)+"/"  +year   );


                                b.produto_dataentregaeretirada.Dado(dayOfMonth+"/"+
                                        (monthOfYear+1)+"/"  +year);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });



//Data ideal pagamento
        fdBt(R.id.dataidealpagamento).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                final int  mYear = c.get(Calendar.YEAR);
                final int  mMonth = c.get(Calendar.MONTH);
                final int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(pr,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                fdBt(R.id.dataidealpagamento).setText(dayOfMonth+"/"+
                                        (monthOfYear+1)+"/"  +year   );

                                b.produto_dataidealpagamento.Dado(dayOfMonth+"/"+
                                        (monthOfYear+1)+"/"  +year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });





//Selecionar dados de recolhimento

        fdBt(R.id.dadosrecolhimento).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DialogoOpcoesLabels(pr,"Selecione recolhimento  ",new String[]{"Opção 1 - Declarar a opção em recolher a contribuição previdenciária sobre a folha de salários, nos \n" +
                        "termos dos incisos I e II do artigo 22 da Lei 8.212/91. Por consequência, essa empresa fica exonerada \n" +
                        "de sua responsabilidade de retenção e recolhimento da contribuição. De igual maneira, como forma de \n" +
                        "comprovar a opção acima destacada, me comprometo a entregar, uma cópia da guia de recolhimento da \n" +
                        "respectiva contribuição, referente ao mês de janeiro de 2019, indicando o montante recolhido no código \n" +
                        "de receita devido.",
                        "Opção 2 - Declarar a opção em prosseguir com o recolhimento da contribuição previdenciária \n" +
                                "incidente sobre a receita bruta da comercialização das mercadorias, mediante a retenção por essa \n" +
                                "empresa do montante devido e o consequente recolhimento aos cofres públicos;" }){

                    @Override
                    public void acaoRetorno(String r){

                        b.produto_dadosrecolhimento.Dado(r);

                        fdBt(R.id.dadosrecolhimento).setText(r);
                    }


                };

            }
        });


//Selecionar dados de recolhimento
        fdBt(R.id.localarmazem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pr.opMap=1;

                if(pr.di!=null){


                  pr.  di.show();
                    return;
                }
              pr.  di=  new DialogoMapa(pr," Local "){
                    @Override
                    public void acaoRetorno(LatLng ln, String end){
                        if(pr.opMap==0) {
                            pr.latlng = ln;
                            fdBt(R.id.selecionarproduto).setText(end);
                        }else if(pr.opMap==1){

                            pr.latlng = ln;
                            fdBt(R.id.localarmazem).setText(end);
                            b.produto_localarmazem.Dado(end);
                            b.produto_localarmazem_lat.Dado(ln.latitude);
                            b.produto_localarmazem_long.Dado(ln.longitude);
                        }else{

                            pr.latlng=ln;
                        }


                    }

                };
            }
        });


        //Quem pode ver
b.setDistinct();

        new Tr(pr){
            @Override
            public void Durante(){
                emp=b.sl((b.produto_fornecedor)).av(b.produto_fornecedor);

            }

        };

        fdBt(R.id.quempodever).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DialogoOpcoesLabelsMulti(pr,"Selecione quem pode ver  ",emp){

                    @Override
                    public void acaoRetorno(ArrayList<String> r){

      b.produto_quempodever.Dado(r.toString());

      fdBt(R.id.quempodever).setText(r.toString());

            }
     };

            }
        });


        fdEd(R.id.fornecedor).setText(pr.usuarioLogado);
        fdBt(R.id.salvarcadastro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String result=null;
                if( (result=b.checaValores("Preencha corretamente o campo ",
                        b.produto_dadosrecolhimento.ObI(fdId(R.id.dadosrecolhimento) ),
                        b.produto_dataentregaeretirada.ObI(fdId(R.id.dataentrega)),
                        b.produto_dataidealpagamento.ObI(fdId(R.id.dataidealpagamento)),
                        b.produto_localarmazem.ObI(fdId(R.id.localarmazem)),
                        b.produto_localarmazem_lat.Dado(pr.latlng.latitude),
                        b.produto_localarmazem_long.Dado(pr.latlng.longitude),
                        b.produto_produto.ObI(fdId(R.id.selecionarproduto)),
                        b.produto_localtexto.Dado( (textoCifFob)),
                        b.produto_qtdsacas.ObI(fdId(R.id.quantidadesacas)),
                        b.produto_quempodever.ObI(fdId(R.id.quempodever)),
                        b.produto_tipotratamento.ObI(fdId(R.id.tipotratamento)),
                        b.produto_vlrporsaca.ObI(fdId(R.id.valorporsaca))))!=null){

                    Mensagem("Valor inválido", result);
                    return;
                }
                new Tr(pr){
                    @Override
                    public void Durante(){

                String r=null;

                r=  b.insert(
                      b.produto_ID.Dado(new Date().getTime()),
                      b.produto_usuario.Dado(pr.usuarioLogado),
                      b.produto_fornecedor.Dado(fdEdS(R.id.fornecedor)),
                      b.produto_dadosrecolhimento.ObI(fdId(R.id.dadosrecolhimento) ),
                      b.produto_dataentregaeretirada.DataM(fdBtS(R.id.dataentrega)),
                      b.produto_dataidealpagamento.DataM(fdBtS(R.id.dataidealpagamento)),
                      b.produto_localarmazem.ObI(fdId(R.id.localarmazem)),
                      b.produto_localarmazem_lat.Dado(pr.latlng.latitude),
                      b.produto_localarmazem_long.Dado(pr.latlng.longitude),
                      b.produto_produto.ObI(fdId(R.id.selecionarproduto)),
                      b.produto_localtexto.Dado( (textoCifFob)),
                      b.produto_qtdsacas.ObI(fdId(R.id.quantidadesacas)),
                      b.produto_quempodever.ObI(fdId(R.id.quempodever)),
                      b.produto_tipotratamento.ObI(fdId(R.id.tipotratamento)),
                      b.produto_vlrporsaca.ObI(fdId(R.id.valorporsaca))) ;

                Mensagem("Publicado","Publicado com sucesso!"+r);
                    }

                };
            }
        });

    }
 }
