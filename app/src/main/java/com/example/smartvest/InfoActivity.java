package com.example.smartvest;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfoActivity extends AppCompatActivity {

    ListView i_lv;
    List<InfoVO> data;
    Button btn_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        i_lv = findViewById(R.id.info_lv);
        data = new ArrayList<>();
        btn_write = findViewById(R.id.btn_write);




        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InfoWriteActivity.class);
                startActivity(intent);
            }
        });


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //get()을 통해서 해당 문서의 정보를 가져온다.
        db.collection("InfoWrite")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                data.add(new InfoVO(document.getId(),document.getData().get("제목").toString()));
                                Log.d("테스트11", document.getId() + " => " + document.getData().get("제목").toString());
                            }
                        } else {
                            Log.w("테스트00", "Error getting documents.", task.getException());
                        }
                    }
                });

        data.add(new InfoVO("현장 A구역 유독가스 유출로 접근 금지", "2021-06-15"));
        data.add(new InfoVO("광주연구개발특구 첨단3지구 대행개발사업자 공모 관련 질의 답변", "2021-07-01"));
        data.add(new InfoVO("2022년 시민참여예산사업 선정 온라인투표", "2021-07-15"));
        data.add(new InfoVO("공공기관 청렴도 측정 관련 개인정보 제3자 제공사항 알림", "2021-08-01"));
        data.add(new InfoVO("2021년 중장기 경영전략 고도화 수립 용역", "2021-08-13"));
        data.add(new InfoVO("대한건설협회, LH와 상생협력 간담회 개최", "2021-08-31"));


        InfoAdapter adapter = new InfoAdapter(getApplicationContext(),
                R.layout.infolist, data);
        i_lv.setAdapter(adapter);


    }
}