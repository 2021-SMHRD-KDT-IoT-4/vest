package com.example.smartvest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;

public class QRScan extends AppCompatActivity {
    private Button btn_qrscan_inout,btn_qrscan_safe;


    //qr code scanner object
    private IntentIntegrator qrScan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);



        //View Objects
        btn_qrscan_inout=findViewById(R.id.btn_qrscan_inout);
        btn_qrscan_safe=findViewById(R.id.btn_qrscan_safe);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        qrScan.setPrompt("QR CODE  확인중!!");
        //qrScan.setOrientationLocked(false);
        qrScan.initiateScan();



        //button onClick
        btn_qrscan_inout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });

        btn_qrscan_safe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scan option

            }
        });

    }
}