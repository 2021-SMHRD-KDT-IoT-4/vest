package com.example.smartvest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {

    ListView lv;
    List<InfoVO> data;
    Button btn_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference productRef = db.collection("InfoWrite");

        lv = findViewById(R.id.lv);
        data = new ArrayList<InfoVO>();
        btn_write = findViewById(R.id.btn_write);

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InfoWriteActivity.class);
                startActivity(intent);
            }
        });

        //Set up the ArrayList
        data = new ArrayList<InfoVO>();

        //set the Adapter
        InfoAdapter adapter = new InfoAdapter(getApplicationContext(),
                R.layout.infolist, data);

        lv.setAdapter(adapter);

        db.collection("InfoWrite").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<InfoVO> lv = new ArrayList<>();
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        InfoVO info = document.toObject(InfoVO.class);
                        data.add(info);
                    }
                    ListView lvlv = (ListView)findViewById(R.id.lv);
                    InfoAdapter adapter = new InfoAdapter(getApplicationContext(),
                            R.layout.infolist, data);

                    lvlv.setAdapter(adapter);
                } else {
                    Log.d("InfoActivity", "Error getting documents: ", task.getException());
                }
            }
        });


    }
}