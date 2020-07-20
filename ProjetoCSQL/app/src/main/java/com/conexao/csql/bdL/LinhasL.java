package com.conexao.csql.bdL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

@SuppressLint("NewApi")
public   class LinhasL implements Cloneable {
	public BancoL bancoL =null;
	public Cursor cursor=null;
	int ncoluna=0;
	int nlinhas=0;
	int vez=0;
	int colunarealT=0;
	public   int cont=-1;
	public String table="";
	public colunaLT colunas[]=null;
	public colunaLT colunasrealT[]=null;
	public String consulta="";
	public String ordena="desc";
	public String novaconsulta="";
	public Context pri=null;
	
	@Override
	public void finalize(){
		try{
			
			cursor.close();
		}catch(Exception e){}
		
		
	}
	public synchronized void add(Context pri, Cursor cursor, BancoL bancoL, String tabs, colunaLT colunas[], String consulta){
		this.pri=pri;
		this.cursor=cursor;
		this.bancoL = bancoL;
		this.table=tabs;
		ncoluna=cursor.getColumnCount();
		nlinhas=cursor.getCount();
		cursor.moveToFirst();
		this.colunas=colunas;
		this.consulta=consulta;
		montacolunaR();
		
		
		
	}
	public synchronized void add(Context pri, Cursor cursor, BancoL bancoL, String tabs ){
		this.pri=pri;
		this.cursor=cursor;
		this.bancoL = bancoL;
		this.table=tabs;
		ncoluna=cursor.getColumnCount();
		nlinhas=cursor.getCount();
		cursor.moveToFirst();
		montacolunaR();
	}
	
	
	public synchronized void reOrdena(String ordena, colunaLT...colunas){
		StringBuffer ordem=new StringBuffer("order by ");
		
		for(int cont=0;cont<colunas.length;cont++){
			
			if(cont+1==colunas.length){
				ordem.append(colunas[cont].C()+" "+ordena+" ");

				
			}else
			ordem.append(colunas[cont].C()+" "+ordena+", ");
			
			
		} 
		
		
		novaconsulta=consulta.replaceAll(BancoLT.div, ordem.toString());
		
cursor= bancoL.banco.rawQuery(novaconsulta, null);
cursor.moveToFirst();
vez=0;
cont=-1;

	}
	
	
	private void montacolunaR(){
	 
	 
		
		 ArrayList<colunaLT> co=new ArrayList<colunaLT>();
		int cs=0;
		for(int cont=0;cont<numcoluna();cont++){
			if(!getNaTabela(cont)){
				
				continue;
				
			}
			 
			co.add(colunas[cont]);
		 
		}
		
		colunasrealT= co.toArray(new colunaLT[co.size()]);
		cursor.moveToFirst();
		vez=0;
		cont=-1;
		
	}
	
	public synchronized int numcolunaRT(){
		
		return colunasrealT.length;
		
	}
	
	public synchronized int numcoluna(){
		
		return ncoluna;
	}
	public synchronized int numlinha(){
		return size();
		
	}
	
	public synchronized int size(){
		 
		return nlinhas;
		
	}
	
public synchronized boolean real(int cont){
	
	if(cursor.getType(cont)== Cursor.FIELD_TYPE_FLOAT){
		
		return true;
	}
	
	return false;
}


public synchronized boolean next(){
	if(size()>0&&vez==0){
		vez=1;
		cont++;
		

        for(int contL=0;contL<colunas.length;contL++){


colunas[contL].Dado(getS(colunas[contL]));
}
		return true;
	}
		boolean bol=cursor.moveToNext();
	if(!bol){
		vez=0;
		cont=-1;
	cursor.moveToFirst();
	}else		{
		
		cont++;
	}

    for(int contL=0;bol&&contL<colunas.length;contL++){


colunas[contL].Dado(getS(colunas[contL]));
}
	
return bol;
	
}
	
	public synchronized boolean getNaTabela(int ncoluna){

		 if(colunas!=null){
			 
			 return (colunas[ncoluna].natabela&&colunas[ncoluna].natabelausuario)?true:false;
			 
		 }
		 return false;	
		 
	
	
	}
	
	public synchronized String getC(int ncoluna){

		 if(colunas!=null){
			 
			 return colunas[ncoluna].coluna;
			 
		 }
		return cursor.getColumnName(ncoluna);
	}

	public synchronized String getT(int ncoluna){

		 if(colunas!=null){
			 
			 return colunas[ncoluna].tipo;
			 
		 }
		return "";
	}
	
	
	
	public synchronized String getPF(int ncoluna){

		 if(colunas!=null){
			 
			 return colunas[ncoluna].nomePref;
			 
		 }
		return cursor.getColumnName(ncoluna);
	}
	
	public synchronized String getSN(colunaLT c, String tx){

		String dC=(c.getSoma())?c.T()+c.C():c.C();

		
	if(( get(c,dC)+"").contentEquals("null")){
			
			return tx;
		}
		
		return get(c,c.C())+"";
		}

	public synchronized String getSN(int coluna, String tx){
		if( (get(coluna)+"").contentEquals("null")){
			
			return tx;
		}
		
		 return get(coluna)+"";

				
			}
public synchronized String getS(int coluna){
		
		
 return get(coluna)+"";

		
	}
	
public synchronized String getSDF(colunaLT c){

	 String dt[]=(get(c,c.C())+"").split("/");
	 if(dt.length<3)return (get(c,c.C())+"");
return dt[2]+"/"+dt[1]+"/"+dt[0] ;
}
public synchronized String getSDF(int c){

	 
	 String dt[]=(get(c)+"").split("/");
	 if(dt.length<3)return (get(c)+"");

	 return dt[2]+"/"+dt[1]+"/"+dt[0] ;
}

public synchronized double getD(int ncoluna){

String cv=get(ncoluna)+"";
	if(cv.contains("null"))cv="0";
	
	if(cv.length()==0)return 0;;
	
	return Double.parseDouble(cv);
}
public synchronized double getD(colunaLT c, String ncoluna){

String cv=get(c,ncoluna)+"";
	if(cv.contains("null"))cv="0";
	
	return Double.parseDouble(cv);
}
public synchronized String getCFG(colunaLT ncoluna){

	 
	if(getS(ncoluna).contains("true")||getS(ncoluna).contains("True"))
		{
		
		
		return "SIM";
		}

	if(getS(ncoluna).contains("false")||
			getS(ncoluna).contains("False"))
	{
	
	
	return "NÃO";
	}
		 
	return getS(ncoluna);
}

public synchronized Boolean getB(colunaLT ncoluna){

	 
	if(getS(ncoluna).contains("true")||getS(ncoluna).contains("True"))
		return true;


		 
	return false;
}

public synchronized String getBC(colunaLT ncoluna){

	 
	if(getS(ncoluna).contains("true")||getS(ncoluna).contains("True"))
		return "ON";


		 
	return "OFF";
}

public synchronized Boolean getB(int ncoluna){

	 
	if(getS(ncoluna).contains("true")||getS(ncoluna).contains("True"))
		return true;


		 
	return false;
}
public synchronized Boolean getB(colunaLT c, String ncoluna){
if(getS(c,ncoluna).contains("true")||getS(c,ncoluna).contains("True"))
	return true;


	 
return false;

}
public synchronized String getS(colunaLT c, String ncoluna){

	 
	return get(c,ncoluna)+"";
}


public synchronized Object get(colunaLT c, String ncoluna){

	if(size()==0)return null;
	if(cont==-1)next();



	if(c.tipo.contentEquals(BancoLT.blob))
	{
		try{

			return cursor.getBlob(cursor.getColumnIndex(ncoluna));
		}catch(Exception e){
			e.printStackTrace(); Log.d("","ErroBD "+e.toString()+" "+c.C());

		}


	}

	if(c.tipo.contentEquals(BancoLT.real)) {
		try{
			return cursor.getDouble(cursor.getColumnIndex(ncoluna));
		}catch(Exception e){
			e.printStackTrace(); Log.d("","ErroBD "+e.toString()+" "+c.C());

		}
	}


	try {
		return cursor.getString(cursor.getColumnIndex(ncoluna));
	} catch (Exception e) {
		e.printStackTrace();
		Log.d("", "ErroBD " + e.toString() + " " + c.C());

	}
	return null;
}

public synchronized int getSI(colunaLT c){
if(getS(c).contentEquals("null"))return -1;
	 
return Integer.parseInt(get(c)+"");
}
 
public synchronized int getSI(int c){
if(getS(c).contentEquals("null"))return -1;
	 
return Integer.parseInt(get(c)+"");
}
public synchronized double getSD(colunaLT c){

	 
return Double.parseDouble(get(c,c.C())+"");
}

public synchronized double getD(colunaLT c){
	
String dC=(c.getSoma())?c.T()+c.C():c.C();
 
String cv=getS(c,dC);
if(cv.contains("null"))cv="0";

Log.d("ErroMeu","ErroMeu"+getS(c));
return Double.parseDouble(cv.replaceAll(",", "."));
}



public synchronized colunaLT getCLT(colunaLT c){
	String dC=(c.getSoma())?c.T()+c.C():c.C();

 
return (c.Dado(get(c,dC)+""));
}

public synchronized String formtaDtH(String d){
	try{ 
 String dt[]=d.split(" ")[0].split("-");
 String dh=d.split(" ")[1];
return dh+" "+dt[2]+"-"+dt[1]+"-"+dt[0] ;
	}catch(Exception e){
		return " ";
		
	}
}
public synchronized String getSFD(colunaLT c){
	String dC=(c.getSoma())?c.T()+c.C():c.C();
String d=get(c,dC)+"";
 String dt[]=d.split(" ")[0].split("-");
 String dh=d.split(" ")[1];
return dh+" "+dt[2]+"-"+dt[1]+"-"+dt[0] ;
}


public synchronized String getS(colunaLT c){
	String dC=(c.getSoma())?c.T()+c.C():c.C();

 
return get(c,dC)+"";
}
public synchronized String getSN(colunaLT c){
	String dC=(c.getSoma())?c.T()+c.C():c.C();

 
return getS(c,dC).replaceAll("null", "");
}


public synchronized Object get(colunaLT c){
	String dC=(c.getSoma())?c.T()+c.C():c.C();

	
if(size()==0)return null;
if(cont==-1)next();
	Log.d("Leu Blob","Leu Blob"+c.T());

if(c.tipo.contentEquals(BancoLT.blob))
{
	try{

		return cursor.getBlob(cursor.getColumnIndex(dC));
	}catch(Exception e){
		e.printStackTrace(); Log.d("","ErroBD "+e.toString()+" "+c.C());

	}


}

	if(c.tipo.contentEquals(BancoLT.real)) {
		try{
			return cursor.getDouble(cursor.getColumnIndex(dC));
		}catch(Exception e){
			e.printStackTrace(); Log.d("","ErroBD "+e.toString()+" "+c.C());

		}
	}


	try {
		return cursor.getString(cursor.getColumnIndex(dC));
	} catch (Exception e) {
		e.printStackTrace();
		Log.d("", "ErroBD " + e.toString() + " " + c.C());

	}
return null;
}



	public synchronized Object get(int coluna){
	
		if(size()==0)return null;
		if(cont==-1)next();
 
		if(colunas[coluna].tipo.contentEquals(BancoLT.real)){
		//	Log.d("Entrou Aqui X","Entrou Aqui X "+cursor.getFloat(coluna));
	
		try{
			return cursor.getFloat(coluna);
			}catch(Exception e){
				e.printStackTrace(); Log.d("","ErroBD "+e.toString()+" "+coluna);
				
			}

		
		}
		try{
		return cursor.getString(coluna);
		}catch(Exception e){
			e.printStackTrace(); Log.d("","ErroBD "+e.toString()+" "+coluna);
			
		}
		
		return null;
	}
	
	public synchronized String getS(int linha, int coluna){
		cursor.moveToPosition(linha);
		
			 return (String)get(coluna);
		
	}
	
	
	public synchronized Object linha(int linha, int coluna){
		cursor.moveToPosition(linha);

		return get(coluna);
		
	}
	public synchronized String linhaS(int linha, int coluna){
 		cursor.moveToPosition(linha);
		try{
			return cursor.getString(coluna);
			}catch(Exception e){
				e.printStackTrace(); Log.d("","ErroBD "+e.toString()+" "+coluna);
				
			}
			 return null;
		
	}
	
	public synchronized ArrayList ar(colunaLT...c){
		cursor.moveToFirst();
		ArrayList l=new ArrayList();
	while(next()){
		String acum="";
		for(int cont=0;cont<c.length;cont++){
			
			acum+=get( c[cont],c[cont].C())+"\n";
			
			
		}
		
		l.add(acum);
		
	}
	cursor.moveToFirst();
	return l;
		
	}
	
	
	public synchronized String[] av(colunaLT c ){
		vez=0;
		cursor.moveToFirst();
		StringBuffer acum=new StringBuffer();
	while(next()){
		 
		for(int cont=0;cont<numcoluna();cont++){
			if(c.CT().contentEquals(colunas[cont].CT()))
			{
				acum.append(get(cont)+ BancoLT.div);
			
			}
			
			
		}
		
 		
	}
	vez=0;
	cursor.moveToFirst();
	return acum.toString().split(BancoLT.div);
		
	}
	
	public synchronized ArrayList ar( ){
		vez=0;
		cursor.moveToFirst();
		ArrayList l=new ArrayList();
	while(next()){
		StringBuffer acum=new StringBuffer();
		for(int cont=0;cont<numcoluna();cont++){
			
			acum.append(get(cont)+", ");
			
			
		}
		
		l.add(acum.toString());
		
	}
	cursor.moveToFirst();
	vez=0;
	return l;
		
	}
	
	public synchronized ArrayList arC( ){
		vez=0;
		cursor.moveToFirst();
		ArrayList l=new ArrayList();
	while(next()){
		StringBuffer acum=new StringBuffer();
		 
		for(int cont=0;cont<numcoluna();cont++){
			
			acum.append(getC(cont)+" "+get(cont)+", ");
			
			
		}
		
		l.add(acum.toString());
		
	}
	vez=0;
	cursor.moveToFirst();
	return l;
		
	}
	
	public synchronized void setColunas(){
		vez=0;
		cursor.moveToFirst();
 	while(next()){
 		 
		for(int cont=0;cont<numcoluna();cont++){
			
			colunas[cont].Dado(getS(colunas[cont]));
			
			
		}
		
		 
		break;
	}
	vez=0;
	cursor.moveToFirst();
	}
	public synchronized void dA(){
		if(size()==0)return;
		String del="delete from "+table+" where ";
		String acum="";
		
		for(int cont=0;cont<numcoluna();cont++){
		acum+=" "+cursor.getColumnName(cont)+"='"+cursor.getString(cont)+"' and";	
		}
		acum=acum.substring(0, acum.length()-3);
		bancoL.banco.execSQL(del+acum);
	}
 
	
	
    
    
	public synchronized LinhasL clone()  {
	    try {
	    	
			return (LinhasL) super.clone();
			
		} catch (CloneNotSupportedException e) {
			 
			e.printStackTrace();
			return null;
		}
	}
 
	
}
