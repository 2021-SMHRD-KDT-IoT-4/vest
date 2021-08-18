package com.example.smartvest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class WorkerMainActivity extends AppCompatActivity implements CircleProgressBar.ProgressFormatter {

    private static final String DEFAULT_PATTERN = "%d%%";

    int a =0 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_main);
        CircleProgressBar circleProgressBar = findViewById(R.id.cpb_circlebar);
        CircleProgressBar circleProgressBar2 = findViewById(R.id.cpb_circlebar2);
        CircleProgressBar circleProgressBar3 = findViewById(R.id.cpb_circlebar3);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("vest").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("박성민", document.getId() + " => " + document.getData().get("sensor").toString());
                        Log.d("테스트3", document.getId() + " => " + document.getData());
                        a = Integer.parseInt(document. getData().get("sensor").toString());
                        break;
                    }

                } else {
                    Log.w("테스트3", "Error getting documents.", task.getException());
                }

                circleProgressBar.setProgress(a);  // 해당 퍼센트를 적용
                circleProgressBar2.setProgress(45);  // 해당 퍼센트를 적용
                circleProgressBar3.setProgress(65);  // 해당 퍼센트를 적용
            }
        });




    }// onCreate()..


    @Override
    public CharSequence format(int progress, int max) {
        return String.format(DEFAULT_PATTERN, (int) ((float) progress / (float) max * 100));
    }

}// MainActivity Class..