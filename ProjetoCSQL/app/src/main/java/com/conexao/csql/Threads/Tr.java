package com.conexao.csql.Threads;

import android.app.ProgressDialog;

import com.conexao.csql.MainActivity;
import com.conexao.csql.bdM.BancoT;
import com.conexao.csql.bdM.Linhas;

import java.util.ArrayList;

public class Tr  {
public Linhas l=null;
    ProgressDialog di=null;
    public boolean usoGeral=false;
    public String n1="", n2="", n3="";
    public String getN1() {
        return n1;
    }
    public ArrayList<Object[]> objetosd;
    public void setN1(String n1) {
        this.n1 = n1;
    }

    public ArrayList<Object[]> getObjetosd() {
        return objetosd;
    }

    public void setObjetosd(ArrayList<Object[]> objetosd) {
        this.objetosd = objetosd;
    }

    public String getN2() {
        return n2;
    }

    public void setN2(String n2) {
        this.n2 = n2;
    }

    public String getN3() {
        return n3;
    }

    public void setN3(String n3) {
        this.n3 = n3;
    }
public Object paramet[]=null;

    public Object[] getParamet() {
        return paramet;
    }

    public void setParamet(Object[] paramet) {
        this.paramet = paramet;
    }

    MainActivity pr=null;
    BancoT b=null;
    public byte bgeral[]=null;
    public Tr(MainActivity pr) {
        this.pr = pr;
        this.b=pr.b;
try {
        pr.runOnUiThread(new Runnable() {
            @Override
            public void run() {


                if (Antes()) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            Durante();

                            pr.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Depois();
                                }
                            });


                        }
                    }).start();

                }
            }
        });

    } catch (Exception e) {
    ErroGeral(e);
}


    }

    public Tr(MainActivity pr, Object ...parmet){
        this.pr=pr;
        this.b=pr.b;
        this.paramet=parmet;
try{
        pr.runOnUiThread(new Runnable() {
            @Override
            public void run() {



        if(Antes()){

            new Thread(new Runnable() {
                @Override
                public void run() {

                    Durante();

                    pr.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Depois();
                        }
                    });


                }
            }).start();

        }
            }
        });


} catch (Exception e) {
    ErroGeral(e);
}

    }


    public Tr(boolean dialogo,MainActivity pr, Object ...parmet){
        this.pr=pr;
        this.b=pr.b;
        try{
        if(dialogo)
        {

            this.di=new ProgressDialog(pr);
            this.di.setCancelable(false);
            this.di.setTitle(parmet[0]+"");
            this.di.setMessage(parmet[1]+"");

        }
        this.paramet=parmet;

        pr.runOnUiThread(new Runnable() {
            @Override
            public void run() {



                if(Antes()){

                    if(di!=null)di.show();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            Durante();

                            pr.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Depois();
                                    if(di!=null)  di.dismiss();
                                    finalTr();
                                }
                            });


                        }
                    }).start();

                }
            }
        });


        } catch (Exception e) {
            ErroGeral(e);
        }

    }




public void finalTr(){


}

public  boolean setUsoGeralB(boolean usoGeral){
           this.usoGeral=usoGeral;

   return  usoGeral;
    }
    public boolean getUsoGeralB( ){

return usoGeral;
    }





    public boolean Antes(){



        return true;
    }

    public void Durante(){


    }
    public void Depois(){


    }



    public void ErroDurante(Exception e){


    }
    public void ErroDepois(Exception e){


    }



    public void tempo(long t){

        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void ErroGeral(Exception e){

pr.Mensagem(" erro ",e.toString());
    }
}
