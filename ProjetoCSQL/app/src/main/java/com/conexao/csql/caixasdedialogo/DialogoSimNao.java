package com.conexao.csql.caixasdedialogo;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.conexao.csql.MainActivity;


public class DialogoSimNao {
public int qid=0;
	MainActivity pri;
	
	public DialogoSimNao(final MainActivity pri, String titulo ){
		
		this.pri=pri;
		
		  AlertDialog.Builder builder = new AlertDialog.Builder(pri);
	  	   
	        builder.setTitle("Alerta!");
	         
	        builder.setMessage(titulo);
	       
	        builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface arg0, int arg1) {
	            
	            	acaoSim();
	  			
	            }
	        });
	        //define um bot�o como negativo.
	        builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface arg0, int arg1) {
	            acaoNao();
	            
	            }
	        });
	        builder.create().show();

	}



	public void acaoNao(){
		
		
		
	}
	
public void acaoSim(){
	
	
	
}

}
