package com.conexao.csql.bdL;

import android.widget.TextView;

public
class colunaLT implements Cloneable {

	public String valida=null;
	boolean autoincrement=false;
	public String nomePref="";
	public String pselect="";
	public String pcreate="";
 String coluna="";
 String tabela="";
	 Object dados="";
public String tipo="";
boolean estado=true;
public static int like=0;
public static int and=1;
public static int or=2;
public static int igual=3;
public static  int like1=4;
public static  int maiorigual=5;
public static  int menorigual=6;
public boolean sinc=true; 
public String total="";
public  int mtipo=-1;
int ct=-1;
boolean soma=false;
public boolean natabela=true;
public boolean natabelausuario=true;


public String proximowhere="and";
public String condicao="";

public String setValida(String valida){
	this.valida=valida;
	return this.valida;


}
	public String getValida(){

		return this.valida;


	}

    public String getProximowhere() {
        return proximowhere;
    }

    public colunaLT setProximowhere(String proximowhere) {
        this.proximowhere = proximowhere;
        return this;
    }

    public String getCondicao() {
        return condicao;
    }

    public colunaLT setCondicao(String condicao) {
        this.condicao = condicao;
        return this;
    }

public colunaLT(String coluna, String tabela, String tipo, boolean estado, String nomePref, boolean natabela){
	this.coluna=coluna;
	this.tabela=tabela;
	 this.tipo=tipo;
	 this.natabela=natabela;
	 if(tipo.contentEquals( BancoLT.auto_increment))autoincrement=true;
	 this.estado=estado;
	 coluna=tabela+"."+coluna;
	 this.nomePref=nomePref;
	 if (estado)
     {
         boolean b = true;
         for (int cont = 0; cont < BancoL.tabelas.size(); cont++)
         {

             if (BancoL.tabelas.get(cont).C().contentEquals(C())&&
            		 BancoL.tabelas.get(cont).T().contentEquals(T()
                 ))
             {

                 b = false;
             }

         }
         if (b)
             BancoL.tabelas.add(this);
     }
	 
	
	 

}





public colunaLT(String coluna, String tabela, String tipo, boolean estado, String nomePref){
	this.coluna=coluna;
	this.tabela=tabela;
	 this.tipo=tipo;
	 if(tipo.contentEquals( BancoLT.auto_increment))autoincrement=true;
	 this.estado=estado;
	 coluna=tabela+"."+coluna;
	 this.nomePref=nomePref;
	 if (estado)
     {
         boolean b = true;
         for (int cont = 0; cont < BancoL.tabelas.size(); cont++)
         {

             if (BancoL.tabelas.get(cont).C().contentEquals(C())&&
            		 BancoL.tabelas.get(cont).T().contentEquals(T()
                 ))
             {

                 b = false;
             }

         }
         if (b)
             BancoL.tabelas.add(this);
     }
	 
	
	 

}
	public colunaLT(String coluna, String tabela, String tipo, boolean estado){
		this.coluna=coluna;
		this.tabela=tabela;
		 this.tipo=tipo;
		 if(tipo.contentEquals( BancoLT.auto_increment))autoincrement=true;
		 this.estado=estado;
		 coluna=tabela+"."+coluna;
		 this.nomePref=coluna;
		 if (estado)
	     {
	         boolean b = true;
	         for (int cont = 0; cont < BancoL.tabelas.size(); cont++)
	         {

	             if (BancoL.tabelas.get(cont).C().contentEquals(C())&&
	            		 BancoL.tabelas.get(cont).T().contentEquals(T()
	                 ))
	             {

	                 b = false;
	             }

	         }
	         if (b)
	             BancoL.tabelas.add(this);
	     }	 
	}
	
	
	
	public colunaLT(String coluna, String tabela, String tipo, boolean estado, boolean sinc){
		this.coluna=coluna;
		this.tabela=tabela;
		 this.tipo=tipo;
		 this.sinc=sinc;
		 if(tipo.contentEquals( BancoLT.auto_increment))autoincrement=true;
		 this.estado=estado;
		 coluna=tabela+"."+coluna;
		 this.nomePref=coluna;
		 if (estado)
	     {
	         boolean b = true;
	         for (int cont = 0; cont < BancoL.tabelas.size(); cont++)
	         {

	             if (BancoL.tabelas.get(cont).C().contentEquals(C())&&
	            		 BancoL.tabelas.get(cont).T().contentEquals(T()
	                 ))
	             {

	                 b = false;
	             }

	         }
	         if (b)
	             BancoL.tabelas.add(this);
	     } 
	}
	
	
	
	
	
	public void setSoma(boolean soma){
		
		this.soma=soma;
		
	}
	
	public boolean getSoma(){return soma;}
	public colunaLT CS(String coluna){
		this.coluna=coluna;
		return this;
	}
	
	
	
	public void setTotal(String total){
		
		this.total=total;
		
		
	}
	
	
	public String getTotal(){
		
		return this.total;
		
	}
	public Float F(){
		return (Float.parseFloat(S()))  ;
		
	}
	public String S(){
		return (String) D() ;
		
	}
	
	public String DD(){
		 
		if(((String)dados).length()==0)return "-1";
		return ((String)dados).replaceAll(",", ".");
		
	}
	
	public Object D(){
	 
		
		return dados;
		
	}
	public void add(Object ob){
		dados=ob+"";
		
	}
	
	
	public colunaLT SD(String dados, int mtipo, int ct){
		this.dados=dados;
		this.mtipo=mtipo;
		this.ct=ct;
		
		return this;
		
	}
	public colunaLT Dado(Object dados){
		this.dados=dados;
		return this.clone();

	}


	public colunaLT ObI(Object dados){
		this.dados=( (TextView)dados).getText().toString();
		return this.clone();

	}
	public colunaLT Dado(String dados){
		this.dados=dados;
		return this.clone();
		
	}
	public String T(){
		
		return tabela;
		
	}
public String CT(){
		
		return tabela+"."+coluna;
		
	}

public String C(){
	
	return  coluna;
	
}
 

public String CPref(){
	
	return nomePref;
	
}
public String P(){
	
	return tipo;
	
}
	
public boolean E(){
	
	return estado;
	
}

public String pSelect(){
	
	return pselect;
	
}
public String pCreate(){
	
	return pcreate;
	
}

public colunaLT clone()  {
    try {
    	
		return (colunaLT) super.clone();
		
	} catch (CloneNotSupportedException e) {
		 
		e.printStackTrace();
		return null;
	}
}




}
