package com.conexao.csql.caixasdedialogo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.conexao.csql.MainActivity;
import com.conexao.csql.R;


public class DialogoEntradaNumero {

	MainActivity pri;
	
	public DialogoEntradaNumero(final MainActivity pri, String titulo){
		
		this.pri=pri;
		

		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(pri);
		View promptsView = li.inflate(R.layout.dialogoentradanumero, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				pri);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.textoapenas);
	 ((TextView) promptsView
				.findViewById(R.id.titulo)).setText(titulo);;

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
                                                int id) {
							 acaoRetorno(userInput.getText().toString());
								 
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
                                                int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}



public void acaoRetorno(String ret){
	
	
	
}

}
