package com.conexao.csql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.content.res.Configuration;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.conexao.csql.Banco.ConexaoG;

import com.conexao.csql.Banco.ConexaoMysql5;
import com.conexao.csql.MenuFloat.FloatingActionButton;
import com.conexao.csql.MenuFloat.menuFloatV;

import com.conexao.csql.Threads.Tr;
import com.conexao.csql.bdL.BancoLT;
import com.conexao.csql.bdL.LinhasL;
import com.conexao.csql.caixasdedialogo.DialogoMapa;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.mercadopago.android.px.core.MercadoPagoCheckout;
import com.mercadopago.android.px.model.Payment;
import com.mercadopago.android.px.model.exceptions.MercadoPagoError;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class MainActivity extends AppCompatActivity {

    public ConexaoG cg =null;

    public boolean imagemPerfil=true;

public int onde=-3;
    public String propostaIDC="";
public boolean tembiometria=false;
    public   View telaatual =null;
    public static String ss="OlaVida";
    public int opMap=0;
    public DialogoMapa   di=null;
    public  byte[] byteImg=null, byteAssinatura=null;
    public LatLng latlng=null;
//TEST-5c4cf867-7f6d-42a2-9e56-a714d9122531
    //TEST-7022787646381843-022716-25d65e340be879856a9a09ba75885f86-30677343
    public     int PICK_IMAGE = 1;
    public Cadastro cad=null;
    public menuFloatV mn = null;
    public FusedLocationProviderClient fusedLocationClient;
    public LatLng uL = null;
    public MainActivity pr = null;
public String usuarioLogado=null;

    byte i[]=null;
    public FloatingActionButton fabButton3=null;
public com.conexao.csql.bdM.BancoT b=null;

BancoLT l=null;
public ConversaeProposta cepr=null;
    Uri audioFileUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

l=new BancoLT(this);

   /*     final FloatingActionButton fabButton3 = new FloatingActionButton.Builder(this)
                .withDrawable(getResources()
                        .getDrawable(R.drawable.ic_launcher_background))
                .withButtonColor(Color.WHITE)
                .withGravity(Gravity.BOTTOM|Gravity.LEFT)
                .withMargins(5, 0, 5, 8 )
                .create();*/




        pr = this;
        if ( Build.VERSION.SDK_INT >= 23  &&
                (
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.CHANGE_WIFI_STATE)
                                != PackageManager.PERMISSION_GRANTED)

                         ){


            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.CHANGE_WIFI_STATE,
                            Manifest.permission.INTERNET },
                    1000);

        } else {
            // already permission granted
        }
/*
new Tr(pr){


    public void Durante(){


        String bdNomeL="testefasoft";
        String bdNome;
        String host="db4free.net";
        String porta="3306";
        String usuario="testefasoft";
        String senha="testefasoft";
        LinhasL r=pr.l.slsW(pr.l.banco_id.Dado("1"));
        BancoLT lt=pr.l;
        Mensagem("","'"+r.getS(lt.banco_nomebanco).contentEquals(bdNomeL)+"' '"+
                r.getS(lt.banco_host).contentEquals(host)+"' '"+  r.getS(lt.banco_porta).contentEquals(porta)
                +"' '"+ r.getS(lt.banco_usuario).contentEquals(usuario)+"' '"+
                r.getS(lt.banco_senha).contentEquals(senha)+"' '"+r.getS(lt.banco_senha)+"' '"+senha+"'");

        setUsoGeralB(new ConexaoMysql5().Conecta(host, porta, bdNomeL, usuario, senha,pr));
    }

    public void Depois() {


        Mensagem("",getUsoGeralB()+"");
    }
};

        if(true)return;*/
        if(null==savedInstanceState||null==savedInstanceState.getSerializable("Onde")) {

    new ListaConexoes(pr, R.layout.listaconexoes);

}
   if(true)return;



       // cobrarPagamento("30677343-c3bac176-5776-4d1a-8e0f-0bf30c2d089f");
      //  if(true)return;
//b.insertUp(null,(b.msgproposta_usuariovendedor.Dado("a")));
   // b.LimpaBanco();
      //  b.insertUp(null,b.cadastro_nome.Dado("NNN"));
        //b.insertUp(null,b.produto_ID.Dado("XX"));

//b.d(b.msgconversa_usuarioDE);
    //   Mensagem("",""+b.sls(b.cadastro).arC().toString());

        if ( Build.VERSION.SDK_INT >= 23  &&
                (  ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED

                || ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) !=
                        PackageManager.PERMISSION_GRANTED) ){


            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_WIFI_STATE
                            , Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.READ_EXTERNAL_STORAGE,

                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.USE_BIOMETRIC},
                    1000);

        } else {
            // already permission granted
        }






        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(pr, new OnSuccessListener<Location>() {


                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        Log.d("GLS", "GLS " + location);
                        if (location != null) {
                            uL = new LatLng(location.getLatitude(), location.getLongitude());
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("GLS", "GLS " + e);
            }
        });




 /*
        if ( Build.VERSION.SDK_INT >= 23 &&
                checkSelfPermission(     android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            //shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION);
//	shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_COARSE_LOCATION);

            requestPermissions(
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.INTERNET,
                            android.Manifest.permission.ACCESS_NETWORK_STATE,
                            android.Manifest.permission.WRITE_GSERVICES  ,
                            android.Manifest.permission.CHANGE_WIFI_STATE,
                            android.Manifest.permission.ACCESS_WIFI_STATE,
                            android.Manifest.permission.CHANGE_WIFI_STATE,
                            android.Manifest.permission.RECEIVE_BOOT_COMPLETED,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.ACCESS_NETWORK_STATE,
                            android.Manifest.permission.READ_PHONE_STATE,
                            android.Manifest.permission.CALL_PHONE,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.RECEIVE_BOOT_COMPLETED,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            	android.Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                    0);
//return  ;
        }
*/











        /*(@NonNull final String publicKey,
            @NonNull final CheckoutPreference checkoutPreference,
            @NonNull final PaymentConfiguration paymentConfiguration)
            "243962506-0bb62e22-5c7b-425e-a0a6-c22d0f4758a9"*/








        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("Biometria","App can authenticate using biometrics.");
                tembiometria=true;
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.d("Biometria","No biometric features available on this device.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.d("Biometria","Biometric features are currently unavailable.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Log.d("Biometria","The user hasn't associated any biometric credentials " +
                        "with their account.");
                break;
        }





    }
    @Override
    protected void onSaveInstanceState(Bundle outState){

        super.onSaveInstanceState(outState); //Salva Activity

        outState.putSerializable("ConexaoG", cg);
        outState.putSerializable("Onde", onde);
        MensagemT("","OnSaved");
     }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState); //Restaura o Activity

         setContentView(R.layout.listaconexoes);

           if(savedInstanceState.getSerializable("ConexaoG")!=null)
            cg= (ConexaoG) savedInstanceState.getSerializable("ConexaoG");
MensagemT("",""+(int)savedInstanceState.getSerializable("Onde"));
           switch ((int)savedInstanceState.getSerializable("Onde")){
               case -1:{

                 iniLogin("-1",false);
                   break;
               }
               case 0:{

                   new ListaConexoes(pr, R.layout.listaconexoes);
                   break;
               }
               case 1:{

                   new Tr(true,pr,"Ajustando Tela!","Estabelecendo conexão, atualizando  lista de bancos!"){
                       @Override
                       public void Durante(){


                       }
                       @Override
                       public void Depois(){


                               new ListaBancos(pr, R.layout.listageral);


                       }

                   };
                   break;
               }
               case 2:{


                   new Tr(true, pr, "Ajustando Tela!", "Atualizando Puxando Tabelas!") {

                       @Override
                       public void Durante() {

                       }

                       @Override
                       public void Depois() {


                           new ListaTables(pr, R.layout.listaconexoes, cg.getBanco());
                       }


                   };
                   break;
               }
               case 3:{

                   new Tr(true,pr,"Ajustando Tela!","Fazendo consulta!"){

                       @Override
                       public void Durante(){
                           //setObjetosd( pr.cg.retornaTodaSql(pr.cg.getBanco(),pr.cg.getTable().toString()));
                       }
                       @Override
                       public void Depois(){

                           new ListaQuery(pr,R.layout.listageral,pr.cg.getBanco(),pr.cg.getTable(),pr.cg.retornaTodaSql());
                       }


                   };
                   break;
               }


           };

        MensagemT("","OnRestored");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        MensagemT("","Changed");
    }

    public boolean ConectaG( ConexaoG cg,String nomebanco, String host, String porta, String usuario, String senha){

        //host, porta, bdNomeL, usuario, senha,pr)
        pr.cg =cg;

        return pr.cg.Conecta(host,porta,nomebanco, usuario, senha,pr);


    }

    public void cobrarPagamento(String ID){
/*  new MercadoPagoCheckout.Builder("TEST-5a3399aa-fba2-40c8-b99a-7508f10320c2", "187081368-b89bb61d-71bb-4b11-9c33-b8a6b14fb0d2")
                .build()
                .startPayment(this, 1000);*/

        new MercadoPagoCheckout
                .Builder("TEST-5c4cf867-7f6d-42a2-9e56-a714d9122531",
                ID)
                . build()
                .startPayment(pr, 111);
    }



    private Handler handler = new Handler();

    private Executor executor = new Executor() {
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };





    public void showBiometricPrompt() {

        if(!tembiometria)return;

        BiometricPrompt.PromptInfo promptInfo =
                new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Acesso biometrico")
                        .setSubtitle("Acesse com sua biometria digital")
                        .setNegativeButtonText("Cancelar")
                        .build();

        BiometricPrompt biometricPrompt = new BiometricPrompt(MainActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                        "Erro de autenticação " + errString, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                BiometricPrompt.CryptoObject authenticatedCryptoObject =
                        result.getCryptoObject();



            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Autenticação falhou",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        // Displays the "log in" prompt.
        biometricPrompt.authenticate(promptInfo);
    }

NovaConexao log=null;
    public void iniLogin(String id, boolean direto){

    setContentView(R.layout.loginoffshore);

 log=   new NovaConexao(pr,R.layout.loginoffshore, id, direto);


}





    public void novaTela(int l){

        telaatual =  getLayoutInflater().inflate(l, null);// aqui seu layout

        telaatual.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboardFrom(pr, telaatual);
                return false;
            }
        });
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) ( findViewById(android.R.id.content))).getChildAt(0);
        viewGroup.removeAllViews();
        viewGroup.addView(telaatual,new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT));


    }
    public   void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public List<Address> retornaMeu(double lat, double lot) {


        try {
            return getStringFromLocation(lat, lot);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    public static List<Address> getStringFromLocation(double lat, double lng)
            throws ClientProtocolException, IOException, JSONException {
System.gc();
        String address = String
                .format(Locale.getDefault(),
                        "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&sensor=false&key=AIzaSyBnNvzPblk4TyZFsijWa6gLIH-joNIWcO8");
        HttpGet httpGet = new HttpGet(address);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();

        List<Address> retList = null;

        response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        InputStream stream = entity.getContent();
        int b;
        while ((b = stream.read()) != -1) {
            stringBuilder.append((char) b);
        }
        byte ptext[] = stringBuilder.toString().getBytes("ISO-8859-1");
        String value = new String(ptext, "UTF-8");


        JSONObject jsonObject = new JSONObject(value);

        retList = new ArrayList<Address>();

        if ("OK".equalsIgnoreCase(jsonObject.getString("status"))) {
            JSONArray results = jsonObject.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject result = results.getJSONObject(i);
                String indiStr = result.getString("formatted_address");
                Address addr = new Address(Locale.getDefault());
                addr.setAddressLine(0, indiStr);
                retList.add(addr);
            }
        }

        return retList;
    }


    public void Mensagem(final String titulo, final String texto) {
        pr.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                AlertDialog.Builder mensagem = new AlertDialog.Builder(pr);
                mensagem.setTitle(titulo);
                mensagem.setMessage(texto);
                mensagem.setNeutralButton("OK", null);
                mensagem.show();
            }
        });


    }


    public void MensagemT(final String titulo, final String texto) {
     //Toast.makeText(pr,titulo+" "+texto,Toast.LENGTH_LONG).show();


    }
    public void MensagemOFF(final String titulo, final String texto) {
        pr.runOnUiThread(new Runnable() {

            @Override
            public void run() {



            }
        });


    }

    private Location getLastBestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return null;
        }
        Location locationGPS = (((LocationManager) this
                .getSystemService(LOCATION_SERVICE)).getLastKnownLocation(LocationManager.GPS_PROVIDER));
        Location locationNet = (((LocationManager) this
                .getSystemService(LOCATION_SERVICE)).getLastKnownLocation(LocationManager.NETWORK_PROVIDER));

        long GPSLocationTime = 0;
        if (null != locationGPS) {

            GPSLocationTime = locationGPS.getTime();

            return locationGPS;
        }

        long NetLocationTime = 0;

        if (null != locationNet) {
            NetLocationTime = locationNet.getTime();
        }

        if ( 0 < GPSLocationTime - NetLocationTime ) {

            return locationGPS;
        }
        else {

            return locationNet;
        }
    }







    @Override
    public void finish(){




        super.finish();
    }


public void ExecutaAudio(byte[] bytes){
    try {
    File tempMp3 = File.createTempFile("teste", "", getCacheDir());
    tempMp3.deleteOnExit();
    FileOutputStream fos = new FileOutputStream(tempMp3);

        fos.write(bytes);

    fos.close();
    MediaPlayer mediaPlayer = new MediaPlayer();
    mediaPlayer.reset();
    ByteArrayInputStream input = new ByteArrayInputStream(bytes);
    FileInputStream fi = new FileInputStream(tempMp3);

    mediaPlayer.setDataSource(fi.getFD());

    mediaPlayer.prepare();
    mediaPlayer.start();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public   void resolveCheckoutResult(final Activity context, final int requestCode, final int resultCode,
                                             final Intent data, final int reqCodeCheckout) {
        if (requestCode == 111) {
            if (resultCode == MercadoPagoCheckout.PAYMENT_RESULT_CODE) {
                final Payment payment = (Payment) data.getSerializableExtra(MercadoPagoCheckout.EXTRA_PAYMENT_RESULT);
                new Tr(pr){
                    @Override
                    public void Durante(){


                        b.insertUp(b.ls(b.pg_propostaID.Dado(propostaIDC)),
                                b.pg_estado.Dado(payment.getPaymentStatus()),
                                b.pg_pgid.Dado(payment.getId()));

                    }
                };
                Mensagem("",payment.getId()+"Resultado del pago: " + payment.getPaymentStatus());
                //Done!
            } else if (resultCode == RESULT_CANCELED) {
                if (data != null && data.getExtras() != null
                        && data.getExtras().containsKey(MercadoPagoCheckout.EXTRA_ERROR)) {
                    final MercadoPagoError mercadoPagoError =
                            (MercadoPagoError) data.getSerializableExtra(MercadoPagoCheckout.EXTRA_ERROR);
                    Mensagem("","Error: " + mercadoPagoError.getMessage());
                    //Resolve error in checkout
                } else {
                    //Resolve canceled checkout
                }
            }
        }
    }

}
