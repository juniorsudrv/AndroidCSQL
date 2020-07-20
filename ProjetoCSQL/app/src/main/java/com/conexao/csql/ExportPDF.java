package com.conexao.csql;

import android.os.Environment;

import com.conexao.csql.Threads.Tr;
import com.conexao.csql.bdM.BancoT;
import com.conexao.csql.bdM.Linhas;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;


public class ExportPDF {

    MainActivity pri=null;
    BancoT b=null;

    public ExportPDF(MainActivity pr){
        this.pri=pr;
        b=pr.b;


    }


    public void geraPDF(String idContrato)   {
new Tr(true,pri,"Gerando contrato!","Gerando contrato em formato pdf!")
{
    @Override
    public void Durante() {

        Linhas ct=b.slsW(b.msgproposta_ID.Dado(idContrato));
        Linhas uc=b.slsW(b.cadastro_usuario.Dado(ct.getS(b.msgproposta_usuariocomprador)));
        Linhas uv=b.slsW(b.cadastro_usuario.Dado(ct.getS(b.msgproposta_usuariovendedor)));
        Linhas pr=b.slsW(b.produto_ID.Dado(ct.getS(b.msgproposta_IDproduto)));
try {
    Document document = new Document();
    File path = Environment.getDataDirectory();
    if (!path.exists()) path.mkdirs();
    File pdffile = new File(path, "Contrato " + idContrato + " COMPRA E VENDA");


    PdfWriter.getInstance(document, new FileOutputStream(pdffile));
    document.open();

    document.add(new Paragraph("DADOS VENDEDOR "));
    document.add(new Paragraph("NOME :"+uv.getS(b.cadastro_nome)+" CPF :"+uv.getS(b.cadastro_cpf)))  ;
    document.add(new Paragraph("ENDEREÇO :"+uv.getS(b.cadastro_endereco)+" I.E :"+uv.getSN(b.cadastro_inscricaoestadual)))  ;

    document.add(new Paragraph("  "));

    document.add(new Paragraph("DADOS COMPRADOR "));
    document.add(new Paragraph("NOME :"+uc.getS(b.cadastro_nome)+" CPF :"+uc.getS(b.cadastro_cpf)))  ;
    document.add(new Paragraph("ENDEREÇO :"+uc.getS(b.cadastro_endereco)+" I.E :"+uc.getSN(b.cadastro_inscricaoestadual)))  ;

    document.add(new Paragraph("  "));

    document.add(new Paragraph("A SEGUINTE PROPOSTA FEITA PELO COMPRADOR FOI ACEITA E ASSINADA PELO VENDEDOR"));

    document.add(new Paragraph("PRODUTO :"+pr.getS(b.produto_produto)
            +" QUANTIDADE COMPRADA:"+ct.getS(b.msgproposta_qtdsacas)+" VALOR :"+ct.getS(b.msgproposta_valor)));

    document.add(new Paragraph("ASSINATURA VENDEDOR" ));
    Image av = Image.getInstance((byte[]) uv.get(b.cadastro_imagemassinatura));
    document.add(av);
    document.add(new Paragraph("ASSINATURA COMPRADOR" ));
    Image ac = Image.getInstance((byte[]) uc.get(b.cadastro_imagemassinatura));
    document.add(ac);

    document.close();


    pri.Mensagem("Dados Gerados", "Salvo em:"+path+"- Nome arquivo:"+
            "Contrato " + idContrato + " COMPRA E VENDA");;
}catch (FileNotFoundException | DocumentException e){

    pri.Mensagem("Erro", e.toString());;

} catch (MalformedURLException e) {
    pri.Mensagem("Erro", e.toString());;
} catch (IOException e) {
    pri.Mensagem("Erro", e.toString());;
}


    }
}
;
    }



}
