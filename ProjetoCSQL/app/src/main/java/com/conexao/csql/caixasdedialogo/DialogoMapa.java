package com.conexao.csql.caixasdedialogo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.conexao.csql.MainActivity;
import com.conexao.csql.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class DialogoMapa {
Marker p=null;
public LayoutInflater li=null;
	MainActivity pri;
	public  boolean foi=false;
	private GoogleMap mMap;
	public DialogoMapa(final MainActivity pri,final String titulo){
		
		this.pri=pri;


		// get prompts.xml view
		  li = LayoutInflater.from(pri);
		View promptsView = li.inflate(R.layout.mapa, null);


		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				pri);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);
    endereco=promptsView.findViewById(R.id.endereco);
		endereco.setText(titulo);
		SupportMapFragment mapFragment = (SupportMapFragment) pri.getSupportFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(new OnMapReadyCallback() {
			@Override
			public void onMapReady(GoogleMap googleMap) {
				mMap = googleMap;
				if(pri.uL!=null  ) {
					// Add a marker in Sydney, Australia, and move the camera.
					LatLng sydney = pri.uL;
					p = mMap.addMarker(new MarkerOptions().position(sydney).title(titulo));
					mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


				}else{

					LatLng sydney = new LatLng(-17.7706651,-50.804229);
					p = mMap.addMarker(new MarkerOptions().position(sydney).title(titulo));
					mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


				}

				mMap.animateCamera( CameraUpdateFactory.zoomTo( 10.0f ) );

				mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
					@Override
					public void onMapClick(LatLng latLng) {

					final	LatLng sydney = latLng;
						p.setPosition(sydney) ;
						mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
						pri.uL=latLng;

               new Thread(new Runnable() {
                   @Override
                   public void run() {
                     final String f=  pri.retornaMeu(sydney.latitude,
                               sydney.longitude).get(0).getAddressLine(0);
                     pri.runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             endereco.setText(f);
                         }
                     });

                   }
               }).start();



					}
				});
			}
		});





		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
                                                int id) {
							  acaoRetorno(pri.uL,endereco.getText().toString());
								 
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
		  alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();

	}
    AlertDialog alertDialog=null;
    public void show(){

    alertDialog.show();
}
	TextView endereco=null;
	public void updateView(String texto){
		endereco.setText(texto);
		p.setPosition(pri.uL);
		mMap.moveCamera(CameraUpdateFactory.newLatLng(pri.uL));
	}
public void acaoRetorno(LatLng ln,String ret){
	
	
	
}

}
