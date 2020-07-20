package com.conexao.csql.caixasdedialogo;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.conexao.csql.MainActivity;


public class DialogoExibicao {
public int qid=0;
	MainActivity pri;

	public DialogoExibicao(final MainActivity pri, String titulo,String msg ){
		
		this.pri=pri;
		
		  AlertDialog.Builder builder = new AlertDialog.Builder(pri);
	  	   
	        builder.setTitle(titulo);
	         
	        builder.setMessage(msg);
	       
	        builder.setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface arg0, int arg1) {
	            
	            	acaoSim();
	  			
	            }
	        });
	        //define um bot�o como negativo.

	        builder.create().show();

	}



	public void acaoNao(){
		
		
		
	}
	
public void acaoSim(){
	
	
	
}

}
