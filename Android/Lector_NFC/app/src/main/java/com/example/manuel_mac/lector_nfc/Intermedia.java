package com.example.manuel_mac.lector_nfc;


import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;


public class Intermedia extends Activity {

    private static final String TAG = "NfcDemo";
    public static final String MIME_TEXT_PLAIN = "text/plain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedia);

        handleIntent(getIntent());
    }


    //Gestionamos la tag encontrada
    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {

            String type = intent.getType();
            if (MIME_TEXT_PLAIN.equals(type)) {
/*
                    Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                    new NdefReaderTask().execute(tag);
                    */
                NdefMessage[] messages = null;
                Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                if (rawMsgs != null) {
                    messages = new NdefMessage[rawMsgs.length];
                    for (int i = 0; i < rawMsgs.length; i++) {
                        messages[i] = (NdefMessage) rawMsgs[i];
                    }
                }
                if(messages[0] != null) {
                    String result="";
                    byte[] payload = messages[0].getRecords()[0].getPayload();
                    // this assumes that we get back am SOH followed by host/code
                    for (int b = 1; b<payload.length; b++) { // skip SOH
                        result += (char) payload[b];
                    }
                    //mTextView.setText("Tag leída: "+result);

                    Toast.makeText(this, "Leído!!!!", Toast.LENGTH_LONG).show();


                    Intent intentR = new Intent (this,resultado.class);
                    intentR.putExtra("dato",result);
                    startActivity(intentR);

                }

            } else {
                Log.d(TAG, "Wrong mime type: " + type);
            }
        } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {

            // In case we would still use the Tech Discovered Intent
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String[] techList = tag.getTechList();
            String searchedTech = Ndef.class.getName();

            for (String tech : techList) {
                if (searchedTech.equals(tech)) {
                        /*new NdefReaderTask().execute(tag);*/
                    NdefMessage[] messages = null;
                    Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                    if (rawMsgs != null) {
                        messages = new NdefMessage[rawMsgs.length];
                        for (int i = 0; i < rawMsgs.length; i++) {
                            messages[i] = (NdefMessage) rawMsgs[i];
                        }
                    }
                    if(messages[0] != null) {
                        String result="";
                        byte[] payload = messages[0].getRecords()[0].getPayload();
                        // this assumes that we get back am SOH followed by host/code
                        for (int b = 1; b<payload.length; b++) { // skip SOH
                            result += (char) payload[b];
                        }
                        //mTextView.setText("Tag leída: "+result);
                        Intent intentR2 = new Intent (this,resultado.class);
                        intentR2.putExtra("dato",result);
                        startActivity(intentR2);

                    }
                    break;
                }
            }
        }
    }

}
