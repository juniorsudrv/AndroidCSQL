package com.conexao.csql;

import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.widget.ImageView;
import android.widget.Switch;

import com.conexao.csql.MenuFloat.menuFloatV;
import com.conexao.csql.Threads.Tr;
import com.conexao.csql.caixasdedialogo.DialogoSimNao;
import com.conexao.csql.classesgenericas.AES;
import com.conexao.csql.classesgenericas.Base;
import com.conexao.csql.classesgenericas.Mask;
import com.conexao.csql.caixasdedialogo.DialogoMapa;
import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Cadastro extends Base {


    public Cadastro(MainActivity pri, int mLayout) {
        super(pri, mLayout);
montaTela();



    }

    public void montaTela(){
        chamaTela();




        new menuFloatV(   pr, false, Gravity.BOTTOM | Gravity.RIGHT, 75,
                new int[]{R.mipmap.voltar}


        ) {

            @Override
            public void click(int coluna, int bt) {


                if (coluna == 0 && bt == 0) {

                       }
            }

        };






        pr.byteImg=null;
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

        fdEd(R.id.cpf).addTextChangedListener(Mask.mask(fdEd(R.id.cpf), Mask.FORMAT_CPF));


    //Salvar abaixo

        fdBt(R.id.salvarcadastro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            new Tr(true,pr,"Salvando aguarde!","Acessando servidor!"){


                @Override
                public void Durante(){
                    setUsoGeralB(true);

                    if(pr.byteImg==null||pr.byteImg.length<10){

                        Mensagem("Incompleto","selecione uma foto de perfil!");
                        setUsoGeralB(false);
                    }

                    if(pr.byteAssinatura==null||pr.byteAssinatura.length<10){

                        Mensagem("Incompleto","selecine uma imagem de assinatura!");
                        setUsoGeralB(false);
                    }
                    if(fdEdS(R.id.nome).length()<4){

                        Mensagem("Incompleto","Digite um nome válido!");
                        setUsoGeralB(false);

                    }

                    if(fdEdS(R.id.celular).length()<4){

                        Mensagem("Incompleto","Digite um celular válido!");
                        setUsoGeralB(false);

                    }


                    if(false&&!Mask.isCPF(fdEdS(R.id.cpf).replaceAll("[.]","")
                            .replace("-",""))){

                        Mensagem("Incompleto","Digite um cpf válido!"+
                                fdEdS(R.id.cpf).replaceAll("[.]",""));
                        setUsoGeralB(false);

                    }


                    if(fdBtS(R.id.novadata).length()<7){

                        Mensagem("Incompleto","Selecione uma data válida!");
                        setUsoGeralB(false);

                    }


                    if(fdEdS(R.id.usuario).length()<1   ){

                        Mensagem("Incompleto","Digite um usuário válido!");
                        setUsoGeralB(false);

                    }


                    if(fdEdS(R.id.senha).length()<1){

                        Mensagem("Incompleto","Digite uma senha válida!");
                        setUsoGeralB(false);

                    }


                    if(!fdEdS(R.id.repitasenha).contentEquals(fdEdS(R.id.senha))){

                        Mensagem("Erro","Senha não confere!");
                        setUsoGeralB(false);

                    }


                    if(fdTx(R.id.localizacao).getText().toString().contentEquals("Adiconar Localização")){

                        Mensagem("Incompleto","Selecione um endereço válido!");
                        setUsoGeralB(false);

                    }
                    if(b.slsWO(b.cadastro_email.Dado(fdEdS(R.id.email)),
                            b.cadastro_usuario.Dado(fdEdS(R.id.usuario))).numlinha()!=0){

                        Mensagem("Existe!","Email ou Usuário já existem no sistema!");
                        setUsoGeralB(false);

                    }

                }


                @Override
                public void Depois(){


                    if(!getUsoGeralB())return;


                    new DialogoSimNao(pr,"Ao cadastrar será criada uma assinatura digital, " +
                            "que será utilizada nas compras e vendas para válidar os contratos, " +
                            "a assinatura é criptografada e única, a assinatura também contém todos os dados" +
                            " do seu cadastro em forma criptografada podendo ser utilizada pelo sistema apara análises gerais" +
                            ", você concorda?"){

                        @Override
                        public void acaoSim(){



                            new Tr(true,pr,"Criando assinatura!","Aguarde!"){

   @Override
                                public void Durante(){

       String r=  b.insert(b.cadastro_nome.Dado(fdEdS(R.id.nome)),
               b.cadastro_celular.Dado(fdEdS(R.id.celular)),
               b.cadastro_telefone.Dado(fdEdS(R.id.telefone)),
               b.cadastro_cpf.Dado(fdEdS(R.id.cpf)),
               b.cadastro_datanasc.DataM(fdBtS(R.id.novadata)),
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
               b.cadastro_datanasc.DataM(fdBtS(R.id.novadata)),
               b.cadastro_email.Dado(fdEdS(R.id.email)),
               b.cadastro_endereco.Dado(fdBtS(R.id.localizacao)),
               b.cadastro_inscricaoestadual.Dado(fdEdS(R.id.inscricaoestadual)),
               b.cadastro_usuario.Dado(fdEdS(R.id.usuario)),
               b.cadastro_senha.Dado(fdEdS(R.id.senha)),
               b.cadastro_latitude.Dado(pr.latlng.latitude+""),
               b.cadastro_longitude.Dado(pr.latlng.longitude+""),

               b.cadastro_assinatura.Dado(assinatura));


       setN1(r);

   }


   @Override
                                public void Depois(){

pr.       Mensagem("Salvo","Salvo com sucesso!"+getN1());


   }
                           };






                        }

                    };

                }
            }   ;

     }
        });



    }





}
