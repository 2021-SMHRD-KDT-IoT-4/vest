package com.example.smartvest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class M_MainActivity extends AppCompatActivity {

        Button btn_qr, btn_info, btn_gps;


    String provider;
    double longitude ;
    float latitude ;
    float altitude ;

    List<UserVO> data;

    String db_url;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_main);

        btn_qr = findViewById(R.id.btn_qr);
        btn_gps = findViewById(R.id.btn_gps);
        btn_info = findViewById(R.id.btn_info);

        btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),QrActivity.class);
                startActivity(intent);

            }
        });

        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),InfoActivity.class);
                startActivity(intent);

            }
        });

        btn_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ManagerActivity.class);
                db_url="https://smartvest-2881b-default-rtdb.firebaseio.com/";
                database = FirebaseDatabase.getInstance(db_url);
                myRef = database.getReference("user");

                myRef.child("latitude").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        latitude =dataSnapshot.getValue(float.class);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError Error) {

                    }
                });
                myRef.child("longitude").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        longitude =dataSnapshot.getValue(Double.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                myRef.child("altitude").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        altitude =dataSnapshot.getValue(float.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                myRef.child("provider").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        provider =dataSnapshot.getValue(String.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



                intent.putExtra("provider",provider);
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude",longitude);
                intent.putExtra("altitude",altitude);
                startActivity(intent);

            }
        });






    }
}