package com.conexao.csql.caixasdedialogo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.conexao.csql.MainActivity;
import com.conexao.csql.R;


public class DialogoFazProposta {

	MainActivity pri;

	public DialogoFazProposta(final MainActivity pri, final String ID, final String fornecedorS,
                              final String produtoS, String quantidadeS, String valorS){
		
		this.pri=pri;
		

		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(pri);
		View promptsView = li.inflate(R.layout.dialogoprposta, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				pri);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText fornecedor = (EditText) promptsView
				.findViewById(R.id.fornecedor);
		final EditText produto = (EditText) promptsView
				.findViewById(R.id.produto);
		final EditText quantidade = (EditText) promptsView
				.findViewById(R.id.quantidade);
		final EditText valor = (EditText) promptsView
				.findViewById(R.id.valor);

		fornecedor.setText(fornecedorS);
		produto.setText(produtoS);
		quantidade.setText(quantidadeS);
	valor.setText(valorS);

		fornecedor.setEnabled(false);
		produto.setEnabled(false);
		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
                                                int id) {
							 acaoRetorno(ID,fornecedorS,produtoS,quantidade.getText().toString(),
                                     valor.getText().toString());
								 
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



public void acaoRetorno( final String ID, String fornecedorS,
                        String produtoS, String quantidadeS, String valorS){
	
	
	
}

}
