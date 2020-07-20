package com.conexao.csql;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Switch;


import com.conexao.csql.Threads.Tr;
import com.conexao.csql.caixasdedialogo.DialogoMapa;
import com.conexao.csql.caixasdedialogo.DialogoSimNao;
import com.conexao.csql.classesgenericas.AES;
import com.conexao.csql.classesgenericas.Base;
import com.conexao.csql.classesgenericas.Mask;
import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class VerCadastro extends Base {


    public VerCadastro(MainActivity pri, int mLayout) {
        super(pri, mLayout);
montaTela();



    }

    public void montaTela(){
        chamaTela();



        pr.byteImg=pr.byteImg;
fdBt(R.id.novadata).setOnClickListener(new View.OnClickListener() {
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


                        fdBt(R.id.novadata).setText(dayOfMonth+"/"+
                                (monthOfYear+1)+"/"  +year   );

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }
});



fdBt(R.id.localizacao).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        pr.opMap=0;
        if(pr.di !=null){


           pr. di.show();
            return;
        }
      pr.  di =  new DialogoMapa(pr,"Clique abaixo e selecione seu endereço! "){
            @Override
            public void acaoRetorno(LatLng ln, String end){


                if(pr.opMap==0) {
                    pr.latlng = ln;
                    fdBt(R.id.localizacao).setText(end);

                }else if(pr.opMap==1){

                    pr.latlng = ln;
                    fdBt(R.id.localarmazem).setText(end);

                    b.produto_localarmazem_lat.Dado(ln.latitude);
                    b.produto_localarmazem_long.Dado(ln.longitude);
                }else{

                    pr.latlng=ln;
                }
                }

        };
    }
});


        ((CircleImageView)pr.findViewById(R.id.imagemperfil)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pr.imagemPerfil=true;
                Intent intent=new Intent(Intent.ACTION_PICK);
                // Sets the type as image/*. This ensures only components of type image are selected
                intent.setType("image/*");
                //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
                String[] mimeTypes = {"image/jpeg", "image/png","image/jpg", "image/JPG"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
                // Launching the Intent
                pr.startActivityForResult(intent,pr.PICK_IMAGE);

            }
        });

        ((ImageView)pr.findViewById(R.id.imagemassintura)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pr.imagemPerfil=false;

                new DialogoSimNao(pr,"Você ja possui um arquivo com sua assinatura eletronica (formato PNG/JPG) ?"){

                    @Override
                    public void acaoSim() {
                        Intent intent=new Intent(Intent.ACTION_PICK);
                        // Sets the type as image/*. This ensures only components of type image are selected
                        intent.setType("image/*");
                        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
                        String[] mimeTypes = {"image/jpeg", "image/png","image/jpg", "image/JPG"};
                        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
                        // Launching the Intent
                        pr.startActivityForResult(intent,pr.PICK_IMAGE);
                    }

                    @Override
                    public void acaoNao(){


                        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                        i.setData(Uri.parse("https://play.google.com/store/search?q=assinatura%20eletronica"));
                        pr.startActivity(i);
                    }
                };



            }
        });
        new Tr(pr){


            @Override
            public void Durante(){
                l=b.slsW(b.cadastro_usuario.Dado(pr.usuarioLogado));
                bgeral=(byte[])l.get(b.cadastro_imagemassinatura);
            }

            @Override
            public void Depois(){
                fdEd(R.id.inscricaoestadual).setText(l.getS(b.cadastro_inscricaoestadual));
                fdEd(R.id.inscricaoestadual).setEnabled(false);
                fdEd(R.id.senha).setText(l.getS(b.cadastro_senha));
                fdEd(R.id.repitasenha).setText(l.getS(b.cadastro_senha));
                fdEd(R.id.usuario).setText(l.getS(b.cadastro_usuario));
                fdEd(R.id.usuario).setEnabled(false);
                fdEd(R.id.email).setText(l.getS(b.cadastro_email));
                fdEd(R.id.celular).setText(l.getS(b.cadastro_celular));
                fdBt(R.id.novadata).setText(l.getS(b.cadastro_datanasc));
                fdBt(R.id.localizacao).setText(l.getS(b.cadastro_endereco));

                pr.latlng=new LatLng(l.getD(b.cadastro_latitude),l.getD(b.cadastro_longitude));

                ((Switch)pr.findViewById(R.id.notificacao)).setChecked(l.getB(b.cadastro_notificacoes));
                fdEd(R.id.cpf).setEnabled(false);
                fdEd(R.id.cpf).setText(l.getS(b.cadastro_cpf));
                ((ImageView)pr.findViewById(R.id.imagemassintura)).
                        setImageBitmap(BitmapFactory.decodeByteArray(bgeral,0,bgeral.length));

            }
        };



        pr.byteImg=pr.i;

         if(pr.i!=null)
        ((CircleImageView)pr.findViewById(R.id.imagemperfil)).setImageBitmap(BitmapFactory.decodeByteArray(pr.i,0,pr.i.length));


        fdBt(R.id.salvarcadastro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if(pr.byteImg==null||pr.byteImg.length<10){

    Mensagem("Incompleto","selecine uma foto de perfil!");
    return;
}

                if(pr.byteAssinatura==null||pr.byteAssinatura.length<10){

                    Mensagem("Incompleto","selecine uma imagem de assinatura!");
                    return;
                }
if(fdEdS(R.id.nome).length()<4){

    Mensagem("Incompleto","Digite um nome válido!");
    return;

}

                if(fdEdS(R.id.celular).length()<4){

                    Mensagem("Incompleto","Digite um celular válido!");
                    return;

                }


                if(!Mask.isCPF(fdEdS(R.id.cpf).replaceAll("[.]","")
                        .replace("-",""))){

                    Mensagem("Incompleto","Digite um cpf válido!"+
                            fdEdS(R.id.cpf).replaceAll("[.]",""));
                    return;

                }


                if(fdBtS(R.id.novadata).length()<7){

                    Mensagem("Incompleto","Selecione uma data válida!");
                    return;

                }


                if(fdEdS(R.id.usuario).length()<1   ){

                    Mensagem("Incompleto","Digite um usuário válido!");
                    return;

                }


                if(fdEdS(R.id.senha).length()<1){

                    Mensagem("Incompleto","Digite uma senha válida!");
                    return;

                }


                if(!fdEdS(R.id.repitasenha).contentEquals(fdEdS(R.id.senha))){

                    Mensagem("Erro","Senha não confere!");
                    return;

                }


                if(fdTx(R.id.localizacao).getText().toString().contentEquals("Adiconar Localização")){

                    Mensagem("Incompleto","Selecione um endereço válido!");
                    return;

                }

             /*   if(b.slsWO(b.cadastro_email.Dado(fdEdS(R.id.email)),
                        b.cadastro_usuario.Dado(fdEdS(R.id.usuario))).numlinha()!=0){

                    Mensagem("Existe!","Email ou Usuário já existem no sistema!");
                    return;

                }*/






                new DialogoSimNao(pr,"Ao atualizar seus dados será criada uma assinatura digital, " +
                        "que será utilizada nas compras e vendas para válidar os contratos, " +
                        "a assinatura é criptografada e única, a assinatura também contém todos os dados" +
                        " do seu cadastro em forma criptografada podendo ser utilizada pelo sistema apara análises gerais" +
                        ", você concorda?"){

                    @Override
                    public void acaoSim(){

                        new Tr(true,pr,"Atualizando!","Aguarde um momento!"){

                            @Override
                            public void Durante(){
                            b.dW(b.cadastro_usuario.Dado(fdEdS(R.id.usuario)));
                        String r=  b.insert(b.cadastro_nome.Dado(fdEdS(R.id.nome)),
                                b.cadastro_celular.Dado(fdEdS(R.id.celular)),
                                b.cadastro_cpf.Dado(fdEdS(R.id.cpf)),
                                b.cadastro_datanasc.Dado(fdBtS(R.id.novadata)),
                                b.cadastro_email.Dado(fdEdS(R.id.email)),
                                b.cadastro_endereco.Dado(fdBtS(R.id.localizacao)),
                                b.cadastro_inscricaoestadual.Dado(fdEdS(R.id.inscricaoestadual)),
                                b.cadastro_usuario.Dado(fdEdS(R.id.usuario)),
                                b.cadastro_senha.Dado(fdEdS(R.id.senha)),
                                b.cadastro_latitude.Dado(pr.latlng.latitude+""),
                                b.cadastro_longitude.Dado(pr.latlng.longitude+""),
                                b.cadastro_imagemperfil.Dado(pr.byteImg),
                                b.cadastro_imagemassinatura.Dado(pr.byteAssinatura),
                                b.cadastro_notificacoes.Dado(((Switch)pr.findViewById(R.id.notificacao)).isChecked()));

                      String assinatura=  AES.encrypt(b.slW(b.ls(b.cadastro_usuario.Dado(fdEdS(R.id.usuario))),
                              b.cadastro_usuario,b.cadastro_cpf,b.cadastro_celular,b.cadastro_datanasc,
                              b.cadastro_nome,b.cadastro_email).arC().toString()
                                , pr.ss);

                           b.insertUp(b.ls(b.cadastro_usuario.Dado(fdEdS(R.id.usuario))),b.cadastro_nome.Dado(fdEdS(R.id.nome)),
                                b.cadastro_celular.Dado(fdEdS(R.id.celular)),
                                b.cadastro_cpf.Dado(fdEdS(R.id.cpf)),
                                b.cadastro_datanasc.Dado(fdBtS(R.id.novadata)),
                                b.cadastro_email.Dado(fdEdS(R.id.email)),
                                b.cadastro_endereco.Dado(fdBtS(R.id.localizacao)),
                                b.cadastro_inscricaoestadual.Dado(fdEdS(R.id.inscricaoestadual)),
                                b.cadastro_usuario.Dado(fdEdS(R.id.usuario)),
                                b.cadastro_senha.Dado(fdEdS(R.id.senha)),
                                b.cadastro_latitude.Dado(pr.latlng.latitude+""),
                                b.cadastro_longitude.Dado(pr.latlng.longitude+""),

                                   b.cadastro_assinatura.Dado(assinatura));


                        Mensagem("Salvo","Alterado com sucesso!"+r);
                    }

                    @Override
                            public void Depois(){

                        new Listaprodutos(pr, R.layout.listaprodutos,false);
            }

                };

                    }

                };






            }
        });



    }





}
