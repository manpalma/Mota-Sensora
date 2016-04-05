package com.example.manuel_mac.lector_nfc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class resultado extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        mTextView = (TextView) findViewById(R.id.textResultado);

        //Recoger el dato leído y mostrarlo
        mTextView.setText(getIntent().getStringExtra("dato"));
    }

    //Al hacer click volver al Activity principal
    public void clickVolver(View v){
        startActivity(new Intent(this, MainActivity.class));
    }
}
