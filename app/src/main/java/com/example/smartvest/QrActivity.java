package com.example.smartvest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class QrActivity extends AppCompatActivity {

    ListView lv_qr;
    List<QrVO> data;
    Button btn_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        lv_qr = findViewById(R.id.lv_qr);
        btn_write = findViewById(R.id.btn_write);

        data = new ArrayList<>();
// Write a message to the database
        String qrurl = "https://vest-11534-default-rtdb.firebaseio.com/";
        String qrurl1 = "https://smartvest-2881b-default-rtdb.firebaseio.com/";

        FirebaseDatabase database = FirebaseDatabase.getInstance(qrurl);
        DatabaseReference myRef = database.getReference("message");




                //myRef.setValue("Hello, World!");
               // String msg = etMsg.getText().toString();
                data.add(new QrVO("광주산업", "유연진",
                        "일반작업",
                        "이수",
                        "출근"));
                myRef.push().setValue(data);


        /*for(int i = 0; i < 10; i++) {
            data.add(new QrVO("광주산업", "유연진"+i,
                    "일반작업",
                    "이수",
                    "출근"));
        }*/

        QrAdapter adapter = new QrAdapter(getApplicationContext(),
                R.layout.qrlist,data);
        lv_qr.setAdapter(adapter);
    }
}