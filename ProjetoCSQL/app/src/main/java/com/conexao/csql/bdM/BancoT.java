package com.conexao.csql.bdM;

import com.conexao.csql.MainActivity;

public class BancoT extends Banco implements Cloneable {

    public static String getvarchar(int num) {

        return " varchar(" + num + ")";

    }

    public BancoT( MainActivity pri,boolean exe) {
        super(pri,exe);
    }

//    public BancoT clone() {
//
//        return (BancoT) super.clone()   ;
//
//    }
    public static String blob = "longblob";
    public static String varchar = "varchar(250)";
    public static String integer = "integer";
    public static String numero = "float";
    public static String real = "float ";
    public static String auto_increment = "INTEGER PRIMARY KEY AUTO_INCREMENT";
    public static String date = "date";
    public static String datetime = "datetime";
    public static String text = "text";
    //---------


    public static String msgproposta="msgproposta";
    public static colunaT msgproposta_ID=new colunaT("msgproposta_ID",
            msgproposta,auto_increment,true,"Valor ");
    public static colunaT msgproposta_usuariovendedor=new colunaT("msgproposta_usuariovendedor",
            msgproposta,varchar,true,"Msg vendedor ");
    public static colunaT msgproposta_usuariocomprador=new colunaT("msgproposta_usuariocomprador",
            msgproposta,varchar,true,"Msg comprador ");
    public static colunaT msgproposta_IDproduto=new colunaT("msgproposta_IDproduto",
            msgproposta,varchar,true,"ID produto ");
    public static colunaT msgproposta_data=new colunaT("msgproposta_data",
            msgproposta,datetime,true,"Date e Hora ");
    public static colunaT msgproposta_estadocomprador=new colunaT("msgproposta_estadocomprador",
            msgproposta,varchar,true,"Estado comprador ");
    public static colunaT msgproposta_estadovendedor=new colunaT("msgproposta_estadovendedor",
            msgproposta,varchar,true,"Estado vendedor ");
    public static colunaT msgproposta_assinaturacomprador=new colunaT("msgproposta_assinaturacomprador",
            msgproposta,text,true,"Assinatura comprador ");
    public static colunaT msgproposta_assinaturavendedor=new colunaT("msgproposta_assinaturavendedor",
            msgproposta,text,true,"Assinatura vendedor ");
    public static colunaT msgproposta_estatus=new colunaT("msgproposta_estatus",
            msgproposta,varchar,true,"Estado ");
    public static colunaT msgproposta_qtdsacas=new colunaT("msgproposta_qtdsacas",
            msgproposta,varchar,true,"Qtd Sacas ");
    public static colunaT msgproposta_valor=new colunaT("msgproposta_valor",
            msgproposta,varchar,true,"Valor ");



    public static String msgconversa="msgconversa";
    public static colunaT msgconversa_usuarioDE=new colunaT("msgconversa_usuarioDE",
            msgconversa,varchar,true,"Usuário ");
    public static colunaT msgconversa_usuarioPARA=new colunaT("msgconversa_usuarioPARA",
            msgconversa,varchar,true,"De ");
    public static colunaT msgconversa_IDproduto=new colunaT("msgconversa_IDproduto",
            msgconversa,varchar,true,"ID produto ");
    public static colunaT msgconversa_datetime=new colunaT("msgconversa_datetime",
            msgconversa,datetime,true,"Date e Hora ");
    public static colunaT msgconversa_msg=new colunaT("msgconversa_msg",
            msgconversa,text,true,"Mensagem ");
    public static colunaT msgconversa_audio=new colunaT("msgconversa_audio",
            msgconversa,blob,true,"Audio ");
    public static colunaT msgconversa_file=new colunaT("msgconversa_file",
            msgconversa,blob,true,"Arquivo ");


    public static String acesso="acesso";
    public static colunaT acesso_usuario=new colunaT("acesso_usuario",
            acesso,varchar,true,"Usuário ");
    public static colunaT acesso_senha=new colunaT("acesso_senha",
            acesso,varchar,true,"Senha ");

    public static String cadastro="cadastro";
    public static colunaT cadastro_notificacoes=new colunaT("cadastro_notificacoes",
            cadastro,varchar,true,"Permitir Notificações ");
    public static colunaT cadastro_imagemperfil=new colunaT("cadastro_imagemperfil",
            cadastro,blob,true,"Imagem perfil ");
    public static colunaT cadastro_imagemassinatura=new colunaT("cadastro_imagemassinatura",
            cadastro,blob,true,"Imagem Assinatura ");
       public static colunaT cadastro_nome=new colunaT("cadastro_nome",
            cadastro,varchar,true,"Nome ");
    public static colunaT cadastro_datanasc=new colunaT("cadastro_datanasc",
            cadastro,date,true,"Data nascimento ");
    public static colunaT cadastro_cpf=new colunaT("cadastro_cpf",
            cadastro,varchar,true,"CPF ");
    public static colunaT cadastro_email=new colunaT("cadastro_email",
            cadastro,varchar,true,"Ema  il ");
    public static colunaT cadastro_usuario=new colunaT("cadastro_usuario",
            cadastro,varchar,true,"Usuário ");
    public static colunaT cadastro_senha=new colunaT("cadastro_senha",
            cadastro,varchar,true,"Senha ");
    public static colunaT cadastro_celular=new colunaT("cadastro_celular",
            cadastro,varchar,true,"Celular ");
    public static colunaT cadastro_telefone=new colunaT("cadastro_telefone",
            cadastro,varchar,true,"Telefone ");
    public static colunaT cadastro_inscricaoestadual=new colunaT("cadastro_inscricaoestadual",
            cadastro,varchar,true,"Inscrição estadual ");
    public static colunaT cadastro_endereco=new colunaT("cadastro_endereco",
            cadastro,varchar,true,"Endereço ");
    public static colunaT cadastro_latitude=new colunaT("cadastro_latitude",
            cadastro,varchar,true,"Latitude ");
    public static colunaT cadastro_longitude=new colunaT("cadastro_longitude",
            cadastro,varchar,true,"Longitude ");
    public static colunaT cadastro_assinatura=new colunaT("cadastro_assinatura",
            cadastro,text,true,"Assinatura digital ");


    public static String produto="produto";
    public static colunaT produto_ID=new colunaT("produto_ID",
            produto,varchar,true,"ID ");
    public static colunaT produto_produto=new colunaT("produto_produto",
            produto,varchar,true,"Produto ");
    public static colunaT produto_usuario=new colunaT("produto_usuario",
            produto,varchar,true,"Usuário ");
    public static colunaT produto_fornecedor=new colunaT("produto_fornecedor",
            produto,varchar,true,"Fornecedor ");
    public static colunaT produto_tipotratamento=new colunaT("produto_tipotratamento",
            produto,varchar,true,"Tipo de tratamento produto ");
    public static colunaT produto_qtdsacas=new colunaT("produto_qtdsacas",
            produto,varchar,true,"Quantidade de sacas ");
    public static colunaT produto_dataentregaeretirada =new colunaT("produto_dataentregaeretirada",
            produto,date,true,"Data entrega/retirada ");
      public static colunaT produto_dataidealpagamento=new colunaT("produto_dataidealpagamento",
            produto,date,true,"Data ideal pagamento ");
    public static colunaT produto_dadosrecolhimento=new colunaT("produto_dadosrecolhimentotxt",
            produto,text,true,"Dados de recolhimento ");
    public static colunaT produto_vlrporsaca=new colunaT("produto_vlrporsaca",
            produto,varchar,true,"Valor por saca ");
    public static colunaT produto_localtexto=new colunaT("produto_localtexto",
            produto,varchar,true,"Armazem Texto ");
    public static colunaT produto_localarmazem=new colunaT("produto_localarmazem",
            produto,varchar,true,"Armazem ");
    public static colunaT produto_localarmazem_lat=new colunaT("produto_localarmazem_lat",
            produto,varchar,true,"produto_localarmazem_lat ");
    public static colunaT produto_localarmazem_long=new colunaT("produto_localarmazem_long",
            produto,varchar,true,"produto_localarmazem_long ");
    public static colunaT produto_quempodever=new colunaT("produto_quempodever",
            produto,text,true,"Quem pode ver? ");




    public static String checaCon="checaCon";
    public static colunaT checaCon_dado=new colunaT("checaCon_dado",
            checaCon,varchar,true,"ChecaCon ");





    public static String pg="pg";
    public static colunaT pg_propostaID=new colunaT("pg_propostaID",
            pg,text,true,"Proposta ID ");
    public static colunaT pg_valorPg=new colunaT("pg_valorPg",
            pg,varchar,true,"Valor a ser Pago ");
    public static colunaT pg_estado=new colunaT("pg_estado",
            pg,varchar,true,"Estado ");
    public static colunaT pg_usuario=new colunaT("pg_usuario",
            pg,varchar,true,"Estado ");
    public static colunaT pg_datetime=new colunaT("pg_datetime",
            pg,datetime,true,"Estado ");
    public static colunaT pg_preferenceID=new colunaT("pg_preferenceID",
            pg,text,true,"Estado ");
    public static colunaT pg_pgid=new colunaT("pg_pgid",
            pg,text,true,"PgId ");

}
