package com.conexao.csql.caixasdedialogo;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.conexao.csql.MainActivity;

import java.util.ArrayList;


public class DialogoOpcoesLabelsMulti {
public int qid=0;
	MainActivity pri;
	public String opcoes[]=null;
	AlertDialog.Builder alertDialogBuilder=null;

	public void novasOpcoes(String opcoes[]){

		this.opcoes=opcoes;
		alertDialogBuilder.setSingleChoiceItems(opcoes, qid, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
			qid=which;

			}
			});


	}


	ArrayList<String> selecoes=new ArrayList<String>();
	public DialogoOpcoesLabelsMulti(final MainActivity pri, String titulo,

                                    String opcoes1[]){
		this.opcoes=opcoes1;
		this.pri=pri;
		
qid=0;
		// get prompts.xml view
  

		   alertDialogBuilder = new AlertDialog.Builder(
				pri);
		
 			alertDialogBuilder.setTitle(titulo)
				// Specify the list array, the items to be selected by default (null for none),
				// and the listener through which to receive callbacks when items are selected
				.setMultiChoiceItems(opcoes, new boolean[]{false}, new DialogInterface.OnMultiChoiceClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which, boolean isChecked) {

						if(isChecked)
						{
							selecoes.add(opcoes[which]);


						}else{

							selecoes.remove(opcoes[which]);

						}

						qid=which;
					}
				})

				// Set the action buttons
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					 acaoRetorno(selecoes);

				}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {

				}
				});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}



public void acaoRetorno(ArrayList<String> ret){
	
	
	
}

}
