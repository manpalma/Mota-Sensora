package com.example.manuel_mac.lector_nfc;

 import android.app.Activity;
 import android.content.Intent;
 import android.nfc.NfcAdapter;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.Toast;


    public class MainActivity extends Activity {

        //Button mbutton;

        NfcAdapter mNfcAdapter;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

            //mbutton = (Button) findViewById(R.id.button);


        //Hacemos comprobaciones para ver si es posible utilizar la aplicación en el dispositivo

            if (mNfcAdapter == null) {
                // Stop, necesitamos NFC
                Toast.makeText(this, "Este dispositivo no dispone de NFC", Toast.LENGTH_LONG).show();
                finish();
                return;

            }

            if (!mNfcAdapter.isEnabled()) {
                Toast.makeText(this, "NFC está desconectado!!!!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Dispositvo listo para leer", Toast.LENGTH_SHORT).show();
            }
        }


        public void clickBoton(View v){
            startActivity(new Intent(this,Intermedia.class));
        }
    }




