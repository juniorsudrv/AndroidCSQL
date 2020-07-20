package com.conexao.csql.bdL;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Base64;
import android.util.Log;

import com.conexao.csql.classesgenericas.DataeHora;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;




public class BancoL implements Cloneable {
	public SQLiteDatabase banco=null;
    public String bdNome="ProjetoSca";
  	//public static String bdNome="coisadefamiliaecaminhaoTESTE";
	public static String div=";q~p~;";
	public static Context pri=null;
	public String groupBY="";
	public boolean sum=false;
	public String tabsJOIN="";
	public String LIMIT="";
	public String Where="";
  String Distinct="";
  String asdes=" desc";
  
	StringBuffer li=new StringBuffer();
	StringBuffer wi=new StringBuffer();
  boolean wiE=false;
  boolean ordemespecifica=false;
  colunaLT colunasOrdem[]=null;
  
  public BancoL(Context pri, String nome){
	  bdNome=nome;
		this.pri=pri;
		abreBanco();
	new montaTabela();
	
	} 
	public BancoL(Context pri){
		this.pri=pri;
		abreBanco();
	new montaTabela();
	
	} 
	
	public synchronized void fechaBanco(){
		
		banco.close();
		
	}
	
	public synchronized void abreBanco(){
		
		
		try{
	 		
			 
			banco = pri.openOrCreateDatabase(bdNome,0 ,null);		
			        
			 }catch(Exception erro){
					Log.d("Erro no Banco","Erro para Criar ou Acessar o Banco");
	}
		
	}
	
	public static ArrayList<colunaLT> tabelas=new ArrayList<colunaLT>();
	public static ArrayList<String> tabelasN=new ArrayList<String>();

	public colunaLT getColuna(String ct, colunaLT... colunas){
		
		
		for(int cont=0;cont<colunas.length;cont++){
			if(ct.contentEquals(colunas[cont].CT())){
				
				 return colunas[cont];
				
				 
			}
			
			
		}
		
		return null;
	}
	public String tornaString(colunaLT...colunas)
	{
        StringBuffer stb=new StringBuffer();
		
		for(int cont=0;cont<colunas.length;cont++){
			stb.append(colunas[cont].C()+" "+colunas[cont].DD()+"\n");
			
		}
		return stb.toString();
	}
	public colunaLT setValor(colunaLT coluna, String valor, colunaLT... colunas){
		
		
		for(int cont=0;cont<colunas.length;cont++){
			if(coluna.CT().contentEquals(colunas[cont].CT())){
				
				if(valor==null)return colunas[cont];
				
				return colunas[cont].Dado(valor);
			}
			
			
		}
		
		return null;
	}
	
	 public String getNomePref(String tabela, String coluna){
 			
			for(int cont=0;cont<tabelas.size();cont++){
				
				if( tabelas.get(cont).T().contentEquals(tabela)&&
						tabelas.get(cont).C().contentEquals(coluna))
				{
			 		return tabelas.get(cont).nomePref;
			 		}
		 	 }
			
 			 return coluna;
			 
		 }
			 	
	
	
 public String geraGetSet(String tabela){
	String acum="\n";
	

	
	for(int cont=0;cont<tabelas.size();cont++){
		
		if(!tabelas.get(cont).T().contentEquals(tabela))
			continue;
			
		 acum+="public string "+
		 tabelas.get(cont).C()
 +" { get; set; }\n";
		 
		 
	 }
	
	String resto="   Tabela."+tabela+"V.Add(new Objetos."+tabela+"{";
	
	for(int cont=0;cont<tabelas.size();cont++){
		

		if(!tabelas.get(cont).T().contentEquals(tabela))
			continue;
		
		resto+=tabelas.get(cont).C()+"=(o."+tabelas.get(cont).C()+"==null)?\"Null\":"
		 		+ "o."+tabelas.get(cont).C()+".ToString()\n,";
 	  	 
	 }
	resto=resto.substring(0, resto.length()-2)+"}); ";
	
	

	
	
	String dados2=" public string Busca"+tabela+"()\n" +
			"        {\n" +
			"            var Tabela = new "+tabela+"C(Filial);\n" +
			"\n" +
			"            using (BDBONZAY bd = new BDBONZAY())\n" +
			"            {\n" +
			"\n" +
			"                var c0 = bd."+tabela+"Collection.GetAll();\n" +
			"\n" +
			"\n" +
			"                foreach (var o in c0)\n" +
			"                {\n" +
			"\n" +resto+
				"\n" +
			"\n" +
			"                }\n" +
			"            }\n" +
			"\n" +
			"            return ageitaJSON(Tabela.ObjectToJson());\n" +
			"\n" +
			"        }";
	String dados="using Newtonsoft.Json;\n" +
"using System;\n" +
"using System.Collections.Generic;\n" +
"using System.Linq;\n" +
"using System.Web;\n" +
"using Bonzay.BD;\n" +
"using BonzayBO;\n" +
"using BonzayBO.Fachada;\n" +
"using BonzayBO.Fachada.Gerencial;\n" +
"using BonzayBO.Tesouraria;\n" +
"using System;\n" +
"using System.Collections.Generic;\n" +
"using System.Configuration;\n" +
"using System.Data;\n" +
"using System.IO;\n" +
"using System.Linq;\n" +
"using System.Text;\n" +
"using System.Web;\n" +
"using System.Web.Script.Serialization;\n" +
"using System.Web.Script.Services;\n" +
"using System.Web.Services;\n" +
"using ClassesBanco.Objetos;\n" +
"\n" +
"namespace ClassesBanco.Objetos\nnamespace WebServiceConsultaFinanceira.Objetos\n\n{\n" +
			"    [Serializable]\n" +
			"    public class "+tabela+"C\n" +
			"    {String Filial=\"\"; \n" +
			"        [JsonProperty(\""+tabela+"\")]\n" +
			"        public List<"+tabela+"> "+tabela+"V   { get; set; }\n" +
			"\n" +
			"        public "+tabela+"C(String Filial)\n" +
			"        { this.Filial=Filial;\n" +
			"            "+tabela+"V = new List<"+tabela+">();\n" +
			"        }\n" +dados2+
			"      public string ageitaJSON(string json)"+
           " {"+
             "   return json.Remove(json.Length - 1).Substring(1);"+
           " }}\n" +
			"    [Serializable]\n" +
			"    public class "+tabela+"\n" +
			"\n" +
			"    {\n" +acum+
			
			"\n" +
			"\n" +
			"\n" +
			"    }\n" +
			"}"
			+ ""
			+ "\n\n"
			+ ""
			+ "" ;
	 return tabela+div+dados;
	 
 }
	
	
	public String JSONLinhaIdem(LinhasL l){
     JSONObject jt=new JSONObject();
		 
     JSONObject jt2=new JSONObject();
     
     JSONObject jt3=new JSONObject();

      
while(l.next()){
	  
	  for(int cont=0;cont<l.numcoluna();cont++){
		  
		  try {
			jt.put(l.getC(cont), l.get(l.colunasrealT[cont] ));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	  }
	  
	   try {
		jt2.accumulate(l.table, jt);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
}


try {
	jt2.accumulate(l.table, jt3);
} catch (JSONException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


		return jt2.toString();
	}
	
 
 
	
	public Object JSONLinha(LinhasL l){
        JSONObject jt=null;
		 
        JSONObject jt2=new JSONObject();
        
        JSONObject jt3=new JSONObject();
 
         
  while(l.next()){
	  jt=new JSONObject();
	  for(int cont=0;cont<l.numcoluna();cont++){
		  
		  try {
		  	String val= l.getS(l.colunasrealT[cont]);

		  	if(l.colunasrealT[cont].tipo.contentEquals(BancoLT.blob)&&
					null!=l.get(l.colunasrealT[cont])){

				byte[] im=(byte[]) l.get(l.colunasrealT[cont]);
				  val=
						Base64.encodeToString(im,
								Base64.NO_WRAP);

				//pr.i=Base64.decode(val,Base64.NO_WRAP);



			}

			jt.put(l.getC(cont),val);
			//Log.d("JSONLINHA", "JSONLINHA "+l.getC(cont)+" "+l.getS(l.getC(cont)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	  }
	  
	   try {
		jt2.accumulate(l.table, jt);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
  }
  

  
  
 		return jt2;
	}
	

	
	public JSONObject JSONTabela(String tabela){
        JSONObject jt=new JSONObject();
		 
        JSONObject jt2=new JSONObject();
        
        
  LinhasL l=  sls(tabela);
         
  while(l.next()){
	  
	  for(int cont=0;cont<l.numcoluna();cont++){
		  
		  try {
			jt.put(l.getC(cont), l.getS(l.colunasrealT[cont]));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	  }
	  
	   try {
		jt2.accumulate(tabela, jt);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
  }
  
  return jt2;
	}
	
	
	
	public synchronized void inseridadosTudo(String value, String data){
		
	for(int tc=0;tc<tabelasN.size();tc++){
		clear(tabelasN.get(tc));
		for(int cont = 0; cont< BancoL.tabelas.size(); cont++){
			
			if(BancoL.tabelas.get(cont).T().contentEquals(tabelasN.get(tc))){
			
			if(BancoL.tabelas.get(cont).tipo.contentEquals(BancoLT.date))
			{
				BancoL.tabelas.get(cont).Dado(data);
			}else
			BancoL.tabelas.get(cont).Dado(value);
				
			}
			
		}
	 
		insert(lI(tabelasN.get(tc)));
		
	}
		
	}
	
	public synchronized void inseridadosTudoB(String value){
		
	for(int tc=0;tc<tabelasN.size();tc++){
		clear(tabelasN.get(tc));
		for(int cont = 0; cont< BancoL.tabelas.size(); cont++){
			
			if(BancoL.tabelas.get(cont).T().contentEquals(tabelasN.get(tc))){
			
				if(BancoL.tabelas.get(cont).tipo.contentEquals(BancoLT.date))
				{
					BancoL.tabelas.get(cont).Dado(new DataeHora().data());
				}else
				BancoL.tabelas.get(cont).Dado(value);
					
			}
			
		}
		dW(lI(tabelasN.get(tc)));
		insert(lI(tabelasN.get(tc)));
		
	}
		
	}
	
	
/*
 * select * from MyTable 
where mydate >= Datetime('2000-01-01 00:00:00') 
and mydate <= Datetime('2050-01-01 23:00:59')
 * */



	public synchronized String checaValores(String msg, colunaLT...colunas){

		String acum="";
		for(int cont=0;cont<colunas.length;cont++){

			if(colunas[cont].D()==null){


				return msg + colunas[cont].CPref();
			}


		}



		return null;


	}

	public synchronized LinhasL slWMD(int v, colunaLT...colunas){

		 String acum="";
		for(int cont=0;cont<v;cont++){
			
			acum+=" "+colunas[cont].CT()+"  >   Datetime('"+colunas[cont].D()+"') "
	                        + "   "+colunas[cont].getProximowhere();
			
		}	
		Where="where "+acum.substring(0, acum.length()-3);

		return sl(l(colunas[0].T()));


		} 
	 
	
public synchronized LinhasL slWM(int v, colunaLT...colunas){

	 String acum="";
	for(int cont=0;cont<v;cont++){
		
		acum+=" "+colunas[cont].CT()+"  "+colunas[cont].getCondicao()+"  '"+colunas[cont].D()+"' "
                        + "   "+colunas[cont].getProximowhere();
		
	}	
	Where="where "+acum.substring(0, acum.length()-3);

	return sl(l(colunas[0].T()));


	} 
 
public synchronized LinhasL slWC(colunaLT...colunas){

	 String acum="";
	for(int cont=0;cont<colunas.length;cont++){
		
		acum+=" "+colunas[cont].CT()+"  "+colunas[cont].getCondicao()+"  '"+colunas[cont].D()+"' "
                       + "   "+colunas[cont].getProximowhere();
		
	}	
	Where="where "+acum.substring(0, acum.length()-3);

	return sl(l(colunas[0].T()));


	} 
	public synchronized void LimpaBanco(){
		String acum="";
	 
	for(int tc=0;tc<tabelasN.size();tc++){
		 
	 d(l(tabelasN.get(tc)));
		
			
		}
		 
		  
		
	}
	
	
	public String ExibeSize(){
		String acum="";
		 ArrayList<String> li=new ArrayList<String>(tabelasN);
		 
		 Collections.sort(li);
	for(int tc=0;tc<li.size();tc++){
		 
		 acum+=li.get(tc)+": "+sls(li.get(tc)).numlinha()+"\n";
			
		}
		 
		
	 return acum;
		
	}
	
	//date(dateColumn)
	
	public synchronized void setOrderEspDate(colunaLT...colunas){
		ordemespecifica=true;
colunaLT colunasq[]=colunas.clone();
		for(int cont=0;cont<colunasq.length;cont++){
			colunasq[cont].coluna="date("+colunasq[cont].coluna+")";
			
		}
		colunasOrdem=colunasq;
 	}
	
	public synchronized void setOrderEsp(colunaLT...colunas){
		ordemespecifica=true;
		colunasOrdem=colunas;
 	}
	
	public String getOrdem(){
		ordemespecifica=false;
		StringBuffer ordem=new StringBuffer();
		for(int cont=0;cont<colunasOrdem.length;cont++){
			
			if(cont+1==colunasOrdem.length)
				ordem.append(colunasOrdem[cont].C()+" "+asdes+" ");

			else
			ordem.append(colunasOrdem[cont].C()+" "+asdes+" ,");
			
		}
		
		return ordem.toString();
	}	
	public synchronized void inseridadosTudoB(String tabela, String value){
		
	for(int tc=0;tc<tabelasN.size();tc++){
		clear(tabelasN.get(tc));
		for(int cont = 0; cont< BancoL.tabelas.size(); cont++){
			
			if(tabela.contentEquals(BancoL.tabelas.get(cont).T())&& BancoL.tabelas.get(cont).T().contentEquals(tabelasN.get(tc))){
			
				if(BancoL.tabelas.get(cont).tipo.contentEquals(BancoLT.date))
				{
					BancoL.tabelas.get(cont).Dado(new DataeHora().data());
				}else
				BancoL.tabelas.get(cont).Dado(value);
					
			}
			
		}
		dW(lI(tabelasN.get(tc)));
		insert(lI(tabelasN.get(tc)));
		
	}
		
	}
	

public double Max(colunaLT colunas){
	setOrderAsc();
	LinhasL linha=sl(colunas);
    double max=0;
    
    while(linha.next()){
    	
    	if(linha.getD(0)>max)max=linha.getD(0);
    	
    } 
	
	
	
	return max;
	
	
} 
	
	

	 public synchronized void clear(String tabela){
		
		
		ArrayList<colunaLT> ar=new ArrayList<colunaLT>();
		for(int cont = 0; cont< BancoL.tabelas.size(); cont++){
			
			if(BancoL.tabelas.get(cont).T().contentEquals(tabela)){
				
				BancoL.tabelas.get(cont).Dado("");
				}
				}
			}
	
	
		public synchronized void insertCI(String tabela){
			insert(lI(tabela));
			
			colunaLT[] colunas=l(tabela);
			
			
		 	clear(colunas[0].tabela);
			
		} 
	 
	public synchronized void insertC(String tabela){
		colunaLT[] colunas=l(tabela);
		
		insert(colunas);
		clear(colunas[0].tabela);
		
	} 
	
	
	
	public synchronized void insertDW(colunaLT...colunas){
		 
		dW(colunas);
		insert(colunas);
	}
	
	public  synchronized void insertDelete(colunaLT...colunas){
		 
		d(colunas);
		insert(colunas);
	}
	public  synchronized void insertd(colunaLT...colunas){
		 
		d(colunas);
		insert(colunas);
	}
	public synchronized void insertD(colunaLT...colunas){
		 
		if(slWU(colunas).size()==0)
		insert(colunas);
	}
	
	
	 public  synchronized void Upupdate(  colunaLT...colunas)
	    {
	 



	        String query = "update  " + colunas[0].T() + " set ";
	        
	        for (int cont = 0; cont < colunas.length; cont++)
	        {
	          
	            query += colunas[cont].C() + "=" + " ? ,";

	        }
	        query = query.substring(0, query.length() - 1) + " " + Where;

	  //pri.Mensagem("", query);
	        SQLiteStatement stmt = banco.compileStatement(query);
	       
	       // pri.escreveTexto2(query);
	        for (int cont = 0; cont < colunas.length
	        		; cont++)
	        {

	    		stmt.bindString(cont+1,colunas[cont].D()+"");

	        }
	        Where="";
	        stmt.execute();
	    	stmt.close();
	   
	        //banco.execSQL(query);

	    }
	 
	 
		public synchronized void dWNot(colunaLT...colunas){

			 String acum="";
			for(int cont=0;cont<colunas.length;cont++){
				
				acum+=" "+colunas[cont].CT()+" != '"+colunas[cont].D()+"' and";
				
			}	
			Where="where "+acum.substring(0, acum.length()-3);

			
			  d(colunas);


			} 
    public  synchronized void insertUp(List<colunaLT> l, colunaLT...colunas)
    {
    	
  
        String acum = "";
        for (int cont = 0; l!=null&&cont < l.size(); cont++)
        {
          
            acum += " " + l.get(cont).C() + "= '" +l.get(cont).D() + "' and";
           
        }
        if(l!=null)
        {
        	Where = " where " + acum.substring(0, acum.length() - 3);
        }



        String query = " update  " + colunas[0].T() + " set ";
        
        for (int cont = 0; cont < colunas.length; cont++)
        {
          
            query += colunas[cont].C() + "=" + " ? ,";

        }
        query = query.substring(0, query.length() - 1) + " " + Where;

  //pri.Mensagem("", query);
        SQLiteStatement stmt = banco.compileStatement(query);
       
       // pri.escreveTexto2(query);
        for (int cont = 0; cont < colunas.length
        		; cont++)
        {

    		stmt.bindString(cont+1,colunas[cont].D()+"");

        }
        Where="";
        stmt.execute();
    	stmt.close();
        //banco.execSQL(query);

    }
	public  synchronized String insert(colunaLT...colunas){
		try {
			String query = "insert into " + colunas[0].T() + " (";
			for (int cont = 0; cont < colunas.length; cont++) {

				query += colunas[cont].C() + ",";

			}
			query = query.substring(0, query.length() - 1) + ") values (";

			for (int cont = 0; cont < colunas.length; cont++) {

				query += "?,";

			}

			query = query.substring(0, query.length() - 1) + ") ";
			SQLiteStatement stmt = banco.compileStatement(query);
			for (int cont = 0; cont < colunas.length; cont++) {

				if (colunas[cont].tipo.contentEquals(BancoLT.real)) {


					stmt.bindDouble(cont + 1, Double.parseDouble(colunas[cont].DD()));
				} else if (colunas[cont].tipo.contentEquals(BancoLT.blob))
				{
					stmt.bindBlob(cont + 1, (byte[]) colunas[cont].D());
				}
				else
					stmt.bindString(cont + 1, colunas[cont].D() + "");

			}

			stmt.execute();
			stmt.close();
		}catch (Exception e){


			return e.toString();
		}
		return null;
//banco.execSQL(query);

	}
	public  synchronized String valida(colunaLT...colunas){

String msgErro=null;
		for(colunaLT c:colunas){

			if(c.S().contentEquals(c.getValida())){
				msgErro="Preencha corretamente o campo "+c.nomePref;
				return msgErro;

			}

		}


		return null;
//banco.execSQL(query);
	
}
	
	
	class montaJOIN{
		
		
		StringBuffer li=new StringBuffer();
		
		public String getJOIN(){
			
			return li.toString();
		}
		 
		
		public synchronized void setJOIN(colunaLT...colunas){
		  
			
			
		    String INNERJOINN="";
			  if(colunas.length%2!=0)return;
			  
			  INNERJOINN ="INNER JOIN "+colunas[1].T()+" ON (";
			if(!tabsJOIN.contains(colunas[0].T()))
			  tabsJOIN+=colunas[0].T()+" ";
			String acum="";
			  for(int cont=0;cont<colunas.length;cont+=2){
				  
	acum+="  "+colunas[cont].CT()+" = "+colunas[cont+1].CT()+" and";
				  
			  }
			  
			  
			  INNERJOINN+=acum.substring(0,acum.length()-3)+") \n";
		  li.append(INNERJOINN);
	  }
  }
	
	
	public synchronized void setJOIN(colunaLT...colunas){
		  
		
		
	    String INNERJOINN="";
		  if(colunas.length%2!=0)return;
		  
		  INNERJOINN ="INNER JOIN "+colunas[1].T()+" ON (";
		  if(!tabsJOIN.contains(colunas[0].T()))
		  tabsJOIN+=colunas[0].T()+" ";
		String acum="";
		  for(int cont=0;cont<colunas.length;cont+=2){
			  
acum+="  "+colunas[cont].CT()+" = "+colunas[cont+1].CT()+" and";
			  
		  }
		  
		  
		  INNERJOINN+=acum.substring(0,acum.length()-3)+") \n";
	  li.append(INNERJOINN);
  }
	public synchronized void setLimit(int limit){
		LIMIT=" LIMIT "+limit;
		
	}
	

	public synchronized void setGroupBY(colunaLT...colunas){
		  
		
		 
		  for(int cont=0;cont<colunas.length;cont+=2){
			  
groupBY+="  "+colunas[cont].CT()+",";
			  
		  }
		  groupBY="group by "+groupBY.substring(0,groupBY.length()-1);
	 
  }
	 
	
	
	public synchronized void setWR(colunaLT...colunas){
		   
		  if(colunas.length%2!=0)return;
		  
		 	String acum="";
		  for(int cont=0;cont<colunas.length;cont+=2){
			  
acum+=" ( "+colunas[cont].CT()+" = "+colunas[cont+1].CT()+") and";
			  
		  }
		  
		 wi.append(acum.substring(0,acum.length()-3));
		 wiE=true;
  }
	
public synchronized void Distinct(){
	Distinct=" distinct ";
	
}	
	
public synchronized void setDistinct(){
	Distinct=" distinct ";
	
}	
public synchronized void setOrderAsc(){
	
	asdes=" asc";
	
}
public synchronized void setOrderDes(){
	
	asdes=" desc";
	
}

public synchronized LinhasL slsWUnico(colunaLT...colunas){
	String acum="";
	for(int cont=0;cont<colunas.length;cont++){
		
		acum+=" "+colunas[cont].C()+"= '"+colunas[cont].D()+"' and";
		
	}	
	Where="where "+acum.substring(0, acum.length()-3);
	
	
LinhasL l=sl(l(colunas[0].T()));
insertUp(ls(colunas[0]), l(colunas[0].T()));
return l;
}
public synchronized LinhasL slsW(colunaLT...colunas){
	String acum="";
	for(int cont=0;cont<colunas.length;cont++){
		
		acum+=" "+colunas[cont].C()+"= '"+colunas[cont].D()+"' and";
		
	}	
	Where="where "+acum.substring(0, acum.length()-3);
	return sl(l(colunas[0].T()));
	
}

	public synchronized LinhasL slsWOC(int c, colunaLT...colunas){
		String acum=" (";
		for(int cont=0;cont<colunas.length;cont++){

			acum+=" "+colunas[cont].C()+"= '"+colunas[cont].D()+"'  "+(cont<c?" or ":cont==c?" ) and":" and");

		}

		Where="where "+acum.substring(0, acum.length()-3);
		//Log.d("tetessMeu","tetessMeu "+Where);
		return sl(l(colunas[0].T()));

	}
public synchronized LinhasL slsWO(colunaLT...colunas){
	String acum="";
	for(int cont=0;cont<colunas.length;cont++){
		
		acum+=" "+colunas[cont].C()+"= '"+colunas[cont].D()+"'  or";
		
	}	
	Where="where "+acum.substring(0, acum.length()-3);
	return sl(l(colunas[0].T()));
	
}
public synchronized LinhasL slWG(colunaLT...colunas){
	 setGroupBY(colunas);
	return slWU( colunas[0]);
	
}
public synchronized LinhasL slG(colunaLT...colunas){
	 setGroupBY(colunas);
	return sl( colunas[0]);
	
}
public synchronized LinhasL slGW1(colunaLT w, colunaLT...colunas){
	 setGroupBY(colunas);
	 
	return slW(ls(w),  colunas[0]);
	
}
public synchronized LinhasL slsG(colunaLT...colunas){
	 setGroupBY(colunas);
	return sl(l(colunas[0].T()));
	
}



public synchronized LinhasL sls(String tabela){
	
	return sl(l(tabela));
	
}

public synchronized LinhasL sl(String query, String tabs){
	LinhasL linha=new LinhasL();
	Cursor cursor=banco.rawQuery(query, null);
  //  pri.startManagingCursor(cursor);
	linha.add(pri,cursor,this,tabs);
	return linha;
	}



public synchronized void setSum(){
	sum=true;
	
}

public synchronized LinhasL sl(colunaLT...colunas){
	LinhasL linha=new LinhasL();
 
	String query=" ";
	String order="";
 	String tabs="";
  	boolean tb1=true;
  	for(int cont=0;cont<colunas.length;cont++){
	String tabsv[]=tabs.split(",");
	boolean vt=true;
  		for(int t=0;t<tabsv.length;t++){
  			if(tabsv[t].contentEquals(colunas[cont].T()))
  			{
  				vt=false;
  			}  			
  			}
  		 if(vt){
  		 if(tb1){
  			 tb1=false;
  			 tabs+=""+colunas[cont].T();
  		 }else{
  			 tabs+=","+colunas[cont].T();
  		 }
  		 }
  		
  		if(sum&&colunas[cont].tipo.contentEquals(BancoLT.real)){
  			
  			query+="sum("+colunas[cont].CT()+") AS "+
  					colunas[cont].T()+colunas[cont].C()+" ,";
  			colunas[cont].setSoma(true);
  			
  		}else
		  
			query+=colunas[cont].CT()+",";

		
 	}
  	
  	
  	
  	 
  	
  	String mQ=query.substring(0,query.length()-1);
  	String mQO=mQ.replaceAll(",", asdes+",")+asdes;
  	String consultaPL="select "+Distinct
 			+" "+mQ+" from "+((li.length()>0)?tabsJOIN:tabs)+" "+li.toString()+" "+Where+" "+
 			((wiE&&Where.length()==0)?"where ":"")+wi.toString()+" "+groupBY+" "+div+" "+LIMIT;
 	
  	
  	query="select "+Distinct
 			+" "+mQ+" from "+((li.length()>0)?tabsJOIN:tabs)+" "+li.toString()+" "+Where+" "+
 			((wiE&&Where.length()==0)?"where ":"")+wi.toString()+" "+groupBY+" order by "
 			+
 			((ordemespecifica)?getOrdem():mQO)
 			+
 			"  "+LIMIT;
	//pri.escreveTexto2(query);
 	Where="";
 	ordemespecifica=false;
	Distinct="";
	LIMIT="";
	tabsJOIN="";
	groupBY="";
	sum=false;
	wiE=false;
	li.delete(0, li.length());
	wi.delete(0, wi.length());
	//li.add("");
	setOrderDes(); 
	
	/*
	Cursor cursorOB=Banco.pri.banco.rawQuery("Select count("+colunas[0].C()+") from "+colunas[0].T()+"", null);
	if(cursorOB.getCount()>0){
		cursorOB.moveToFirst();
		ob=new Object[(int) cursorOB.getDouble(0)];
		
	}
	cursorOB.close();*/
	//pri.escreveTexto2(query);
	//Log.d("Errata ","Errata "+query);
	  
 	Cursor cursor=banco.rawQuery(query, null);
   /* pri.startManagingCursor(cursor);
     Cursor cursor2=banco.rawQuery(query, null);
    pri.startManagingCursor(cursor2);*/
	linha.add(pri,cursor,this,tabs,colunas.clone(),consultaPL);
	 
	return linha;
	
	
} 
 


public synchronized LinhasL slW(List<colunaLT> l, colunaLT...colunas){

	 String acum="";
	for(int cont=0;cont<l.size();cont++){
		
		acum+=" "+l.get(cont).CT()+"= '"+l.get(cont).D()+"' and";
		
	}	
	Where="where "+acum.substring(0, acum.length()-3);

	return sl(colunas);


	} 


public synchronized LinhasL slW(String tabela, colunaLT...colunas){

	 String acum="";
	for(int cont=0;cont<colunas.length;cont++){
		
		acum+=" "+colunas[cont].CT()+"= '"+colunas[cont].D()+"' and";
		
	}	
	Where="where "+acum.substring(0, acum.length()-3);

	return sl(l(tabela));


	} 

public synchronized LinhasL slWI(List<colunaLT> l, montaJOIN mj, colunaLT...colunas){

	 String acum="";
	for(int cont=0;cont<l.size();cont++){
		
		acum+=" "+l.get(cont).T()+"."+l.get(cont).CT()+"='"+l.get(cont).D()+"' and";
		
	}	
	Where=mj.getJOIN()+" where "+acum.substring(0, acum.length()-3);

	return sl(colunas);


	} 


public synchronized LinhasL slT(List<colunaLT> l, colunaLT...colunas){
//Data Inicial vem primeiro
	boolean inicio=true;
	boolean edata=false;
	String btw="";
	 String acum="";
	for(int cont=0;cont<l.size();cont++){
		
		String cd="=";
		String ct="and";
		String cp1="";
		String cp2="";
		
		if(inicio&&l.get(cont).tipo.contentEquals(BancoLT.date)){
			
			btw="BETWEEN";
			inicio=false;
		}else if(l.get(cont).tipo.contentEquals(BancoLT.date)){
			edata=true;
			
		}
		
		if(l.get(cont).mtipo==l.get(cont).like){
			cd=" like ";
			cp1="%";
			cp2="%";
		}else if(l.get(cont).mtipo==l.get(cont).like1){
			
			cd=" like ";
		 
			cp2="%";
			
			
		}else if(l.get(cont).mtipo==l.get(cont).maiorigual){
			
			cd=" >= ";
		 
			 
			
			
}else if(l.get(cont).mtipo==l.get(cont).menorigual){
			
			cd=" <= ";
		 
			 
			
			
		}
		
      if(l.get(cont).ct==l.get(cont).or){
			
		 ct="or";
			
			
		}
		
      
      if(edata){
  		acum+=" '"+cp1+l.get(cont).D()+cp2+"' "+ct+"";
 
      }else
		acum+=" "+l.get(cont).CT()+" "+((btw.length()>0)?btw:cd)+" '"+
      cp1+l.get(cont).D()+cp2+"' "+ct+"";
		
		btw="";
		edata=false;
	}	
	Where="where "+acum.substring(0, acum.length()-3);
 //pri.Mensagem("", Where);
 //pri.escreveTexto2(Where);
	return sl(colunas);


	} 



public synchronized LinhasL slWUU(colunaLT...colunas){

	 String acum="";
	for(int cont=0;cont<colunas.length;cont++){
		
		acum+=" "+colunas[cont].CT()+"= '"+colunas[cont].D()+"' and";
		break;
	}	
	Where="where "+acum.substring(0, acum.length()-3);

	return sl(colunas);


	} 

public synchronized LinhasL slWUN(int n, colunaLT...colunas){

	 String acum="";
	for(int cont=0;cont<n;cont++){
		
		acum+=" "+colunas[cont].CT()+"= '"+colunas[cont].D()+"' and";
		
	}	
	Where="where "+acum.substring(0, acum.length()-3);

	return sl(colunas);


	} 
public synchronized LinhasL slWU(colunaLT...colunas){

	 String acum="";
	for(int cont=0;cont<colunas.length;cont++){
		
		acum+=" "+colunas[cont].CT()+"= '"+colunas[cont].D()+"' and";
		
	}	
	Where="where "+acum.substring(0, acum.length()-3);

	return sl(colunas);


	} 



public synchronized LinhasL slWL(List<colunaLT> l, colunaLT...colunas){

 String acum="";
for(int cont=0;cont<l.size();cont++){
	
	acum+=" "+l.get(cont).CT()+" like '%"+l.get(cont).D()+"%' and";
	
}	
Where="where "+acum.substring(0, acum.length()-3);

return sl(colunas);


} 
public synchronized LinhasL slWL1(List<colunaLT> l, colunaLT...colunas){

	 String acum="";
	for(int cont=0;cont<l.size();cont++){
		
		acum+=" "+l.get(cont).CT()+" like '"+l.get(cont).D()+"%' and";
		
	}	
	Where="where "+acum.substring(0, acum.length()-3);

	return sl(colunas);


	} 
public synchronized LinhasL slWLO(List<colunaLT> l, colunaLT...colunas){

	 String acum="";
	for(int cont=0;cont<l.size();cont++){
		
		acum+=" "+l.get(cont).CT()+" like '%"+l.get(cont).D()+"%' or";
		
	}	
	Where="where "+acum.substring(0, acum.length()-2);

	return sl(colunas);


	} 
	public synchronized LinhasL slWLO1(List<colunaLT> l, colunaLT...colunas){

		 String acum="";
		for(int cont=0;cont<l.size();cont++){
			
			acum+=" "+l.get(cont).CT()+" like '"+l.get(cont).D()+"%' or";
			
		}	
		Where="where "+acum.substring(0, acum.length()-2);

		return sl(colunas);


		} 


	
	public synchronized void dlT( colunaLT...colunas){

		 String acum="";
		for(int cont=0;cont<colunas.length;cont++){
			String cd="=";
			String ct="and";
			String cp1="";
			String cp2="";
			if(colunas[cont].mtipo==colunas[cont].like){
				cd=" like ";
				cp1="%";
				cp2="%";
			}else if(colunas[cont].mtipo==colunas[cont].like1){
				
				cd=" like ";
			 
				cp2="%";
				
				
			}
			
	      if(colunas[cont].ct==colunas[cont].or){
				
			 ct="or";
				
				
			}
			
			acum+=" "+colunas[cont].C()+" "+cd+" '"+cp1+colunas[cont].D()+cp2+"' "+ct+"";
			
		}	
		Where="where "+acum.substring(0, acum.length()-3);

		  d(colunas);


		} 
	
	
	
	
	
	
	public  synchronized void  d(colunaLT...colunas){
		String query="";

	 
	 	query="delete from "+colunas[0].T()+" "+Where+" "+LIMIT;
	 	//Log.d("query", "query "+query);
		Where="";
		Distinct="";
		LIMIT="";
		banco.execSQL(query);
		
	}
	
	 
	public synchronized void dWEx(List<colunaLT> ex, colunaLT...colunas){
boolean pula=false;
		 String acum="";
		for(int cont=0;cont<colunas.length;cont++){
			
			
		pula=false;
			for(int ce=0;ce<ex.size();ce++){
			if(ex.get(ce).C().contentEquals(colunas[cont].C())&&
					ex.get(ce).T().contentEquals(colunas[cont].T())||
					(colunas[cont].D()+"").contentEquals("null")){
			pula=true;
			}
			}
			if(pula)continue;
			acum+=" "+colunas[cont].CT()+" = '"+colunas[cont].D()+"' and";
			}	
		
		Where="where "+acum.substring(0, acum.length()-3);
	Log.d("Menagem", "Menagem"+Where);
 d(colunas);


		} 
	
	
	
	public synchronized void dWEx(colunaLT ex, colunaLT...colunas){

		 String acum="";
		for(int cont=0;cont<colunas.length;cont++){
			
			if(ex.C().contentEquals(colunas[cont].C())&&
					ex.T().contentEquals(colunas[cont].T()))continue;
			
			acum+=" "+colunas[cont].CT()+" = '"+colunas[cont].D()+"' and";
			}	
		
		Where="where "+acum.substring(0, acum.length()-3);
  d(colunas);


		} 
	
	
	public synchronized void dW(colunaLT...colunas){

		 String acum="";
		for(int cont=0;cont<colunas.length;cont++){
			
			acum+=" "+colunas[cont].CT()+" = '"+colunas[cont].D()+"' and";
			
		}	
		Where="where "+acum.substring(0, acum.length()-3);

		
		  d(colunas);


		} 


public synchronized void dWL(colunaLT...colunas){

	 String acum="";
	for(int cont=0;cont<colunas.length;cont++){
		
		acum+=" "+colunas[cont].CT()+" like '%"+colunas[cont].D()+"%' and";
		
	}	
	Where="where "+acum.substring(0, acum.length()-3);

	  d(colunas);


	} 
public synchronized void dWL1(colunaLT...colunas){

	 String acum="";
	for(int cont=0;cont<colunas.length;cont++){
		
		acum+=" "+colunas[cont].CT()+" like '"+colunas[cont].D()+"%' and";
		
	}	
	Where="where "+acum.substring(0, acum.length()-3);

	  d(colunas);


	} 

public synchronized void dWLO(colunaLT...colunas){

	 String acum="";
	for(int cont=0;cont<colunas.length;cont++){
		
		acum+=" "+colunas[cont].CT()+" like '%"+colunas[cont].D()+"%' or";
		
	}	
	Where="where "+acum.substring(0, acum.length()-2);

	  d(colunas);


	} 
	public synchronized void dWLO1(colunaLT...colunas){

		 String acum="";
		for(int cont=0;cont<colunas.length;cont++){
			
			acum+=" "+colunas[cont].CT()+" like '"+colunas[cont].D()+"%' or";
			
		}	
		Where="where "+acum.substring(0, acum.length()-2);

		  d(colunas);


		} 



	public List ls(colunaLT...colunas){
return	Arrays.asList(colunas);
	}


	public colunaLT[] l(String tabela){


		ArrayList<colunaLT> ar=new ArrayList<colunaLT>();
		for(int cont = 0; cont< BancoL.tabelas.size(); cont++){

			if(BancoL.tabelas.get(cont).T().contentEquals(tabela)){
				ar.add(BancoL.tabelas.get(cont));
			}

		}

		colunaLT[] arT=new colunaLT[ar.size()];
		for(int cont=0;cont<ar.size();cont++){

			arT[cont]=ar.get(cont);

		}


		return arT;
	}

	public colunaLT[] lNull(String tabela){


		ArrayList<colunaLT> ar=new ArrayList<colunaLT>();
		for(int cont = 0; cont< BancoL.tabelas.size(); cont++){

			if(BancoL.tabelas.get(cont).T().contentEquals(tabela)){
				ar.add(BancoL.tabelas.get(cont));
			}

		}

		colunaLT[] arT=new colunaLT[ar.size()];
		for(int cont=0;cont<ar.size();cont++){
			ar.get(cont).Dado(null);
			arT[cont]=ar.get(cont);

		}


		return arT;
	}

public colunaLT[] lISinc(String tabela){
	
	
	ArrayList<colunaLT> ar=new ArrayList<colunaLT>();
	for(int cont = 0; cont< BancoL.tabelas.size(); cont++){
		
		if(BancoL.tabelas.get(cont).T().contentEquals(tabela)){
			if(!BancoL.tabelas.get(cont).autoincrement&& BancoL.tabelas.get(cont).sinc)
			ar.add(BancoL.tabelas.get(cont));
			
		}
		
	}
	
	colunaLT[] arT=new colunaLT[ar.size()];
	for(int cont=0;cont<ar.size();cont++){
		
		arT[cont]=ar.get(cont);
		
	}
	
	
	return arT;
}

public colunaLT[] lI(String tabela){
	
	
	ArrayList<colunaLT> ar=new ArrayList<colunaLT>();
	for(int cont = 0; cont< BancoL.tabelas.size(); cont++){
		
		if(BancoL.tabelas.get(cont).T().contentEquals(tabela)){
			if(!BancoL.tabelas.get(cont).autoincrement)
			ar.add(BancoL.tabelas.get(cont));
			
		}
		
	}
	
	colunaLT[] arT=new colunaLT[ar.size()];
	for(int cont=0;cont<ar.size();cont++){
		
		arT[cont]=ar.get(cont);
		
	}
	
	
	return arT;
}
class montaTabela{
	String div= BancoL.div;
	StringBuffer buf=new StringBuffer();;
	String monta_tabela[];

 	public montaTabela( ){
	 
		for(int cont = 0; cont< BancoL.tabelas.size(); cont++){
			System.gc();
			if(buf.length()==0){
 				
 				buf.append(BancoL.tabelas.get(cont).T()+"");
			}else{
				   boolean temJa=false;
                   for(int contexe=0;contexe<buf.toString().split(div).length;contexe++){
	String table=buf.toString().split(div)[contexe];
                   
               
               if(table.contentEquals(BancoL.tabelas.get(cont).T())){
temJa=true;
break;
               }
               
                   }
                   if(!temJa){
            	buf.append(div+ BancoL.tabelas.get(cont).T());

                   }
		    
			}
			}
	 
		monta_tabela=new String[buf.toString().split(div).length];
		
		for(int contexe=0;contexe<buf.toString().split(div).length;contexe++){
			String table=buf.toString().split(div)[contexe];
		boolean temJa=true;
			for(int cn=0;cn<tabelasN.size();cn++){
		if(tabelasN.get(cn).contentEquals(table)){
			temJa=false;
			break;
		}
				
			}
			
			if(temJa){
				tabelasN.add(table);	
				
			}
		
		String exec="create table if not exists "+table+" (";
		String pselect="";
		String pcreate="";
		
		for(int cont = 0; cont< BancoL.tabelas.size(); cont++){
			
			

			if(BancoL.tabelas.get(cont).T().contentEquals(table)){
			
				if(BancoL.tabelas.get(cont).E()){
				
					exec+=""+ BancoL.tabelas.get(cont).C()+" "+ BancoL.tabelas.get(cont).P()+",";
					
					pselect+=(""+ BancoL.tabelas.get(cont).C()+",");

					pcreate+=(""+ BancoL.tabelas.get(cont).C()+" "+ BancoL.tabelas.get(cont).P()+",");
				}
				}
		}
		 
	   pselect=pselect.substring(0,pselect.length()-1);
	   
	   pcreate="("+pcreate.substring(0, pcreate.length()-1)+")";
		
  		banco.execSQL(  exec.substring(0, exec.length()-1)+")");
		boolean drop=false;
		for(int cont = 0; cont< BancoL.tabelas.size(); cont++){
			if(BancoL.tabelas.get(cont).T().contentEquals(table)){
				BancoL.tabelas.get(cont).pselect=pselect;
				BancoL.tabelas.get(cont).pcreate=pcreate;
				
				if(BancoL.tabelas.get(cont).E())
				{
					if(!existeColuna(BancoL.tabelas.get(cont).T(), BancoL.tabelas.get(cont).C()))
					{
				banco.execSQL("ALTER TABLE "+ BancoL.tabelas.get(cont).T()+" "
								+ "ADD COLUMN "+ BancoL.tabelas.get(cont).C()+" "+ BancoL.tabelas.get(cont).P()+"");
					  
 						}
				}else{
					
					if(existeColuna(BancoL.tabelas.get(cont).T(), BancoL.tabelas.get(cont).C()))
					{//ALTER TABLE table_name  DROP COLUMN column_name;
						/*Banco.pri.banco.execSQL("ALTER TABLE "+Banco.tabelas.get(cont).T()+" "
								+ " DROP COLUMN "+Banco.tabelas.get(cont).C()+" ");*/
						
						drop=true;
					}
					
				}
				
				
			}
		}
		
		if(drop){
		
	banco.execSQL("create table if not exists temporario_"+table +" "+pcreate);
		banco.execSQL("insert into temporario_"+table +" select "+pselect+"  from "+table);
banco.execSQL("drop table "+table +" ");
		banco.execSQL("create table "+table +" "+pcreate);
		banco.execSQL("insert into "+table +" select "+pselect+" from  temporario_"+table);
		banco.execSQL("drop table temporario_"+table+" ");
	
			/*
			BEGIN TRANSACTION;
			CREATE TEMPORARY TABLE t1_backup(a,b);
			INSERT INTO t1_backup SELECT a,b FROM t1;
			DROP TABLE t1;
			CREATE TABLE t1(a,b);
			INSERT INTO t1 SELECT a,b FROM t1_backup;
			DROP TABLE t1_backup;
			COMMIT;*/
			
			
		}
		
		
		}
		
		
		buf.setLength(0);
		buf=null;
		System.gc();
		 Log.d("BancoT", "BancoT "+ BancoL.tabelas.size()+" "+ BancoL.tabelasN.size());

 	}
		
 	

	private boolean existeColuna(String tabela, String coluna) {
		try {
			Cursor mCursor =banco.rawQuery( "SELECT * FROM " + tabela + " LIMIT 1", null);

			if (mCursor.getColumnIndex(coluna) == -1){
				mCursor.close();
				return false;
					
			}
			 	
				 mCursor.close();


			return true;

		} catch (Exception Exp) {
			return false;
		}
	}
 	

 	
	}
public BancoL clone()  {
    try {
    	
		return (BancoL) super.clone();
		
	} catch (CloneNotSupportedException e) {
		 
		e.printStackTrace();
		return null;
	}
}
}





