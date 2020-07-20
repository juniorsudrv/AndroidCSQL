package com.conexao.csql.classesgenericas;


import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class spinner  {
public final void setTexts(Context con, Spinner sp, String texto[]){
	
	
	 //clube = new String[]{"Corinthians", "São Paulo", "Flamengo", "Palmeiras", "Vasco", "Botafogo", "Cruzeiro", "Internacional", "Ceará", "Figueirense", "Coritiba", "Fluminense", "Grêmio", "Santos", "Atlético-MG", "Bahia", "Atlético-GO", "Avaí", "América-MG", "Atlético-PR"};    
	    
	    if(texto==null||sp==null)return;
     ArrayAdapter<String> adp = new ArrayAdapter<String>(con, android.R.layout.simple_list_item_1, texto);
   
     sp.setAdapter(adp);
     
     
}
	
public void setSelectedText(Spinner sp, String texto){
	String myString = texto; //the value you want the position for

    ArrayAdapter myAdap = (ArrayAdapter) sp.getAdapter(); //cast to an ArrayAdapter

    int spinnerPosition = myAdap.getPosition(myString);

    //set the default according to value
    sp. setSelection(spinnerPosition);
	
}
}