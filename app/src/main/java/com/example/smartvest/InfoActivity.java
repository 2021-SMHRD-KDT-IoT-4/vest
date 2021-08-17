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
import java.util.Map;

public class InfoActivity extends AppCompatActivity {

    ListView i_lv;
    List<InfoVO> data;
    Button btn_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference productRef = db.collection("InfoWrite");

        i_lv = findViewById(R.id.i_lv);
        data = new ArrayList<>();
        btn_write = findViewById(R.id.btn_write);

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InfoWriteActivity.class);
                startActivity(intent);
            }
        });


        //CollectionReference 는 파이어스토어의 컬렉션을 참조하는 객체다.
        productRef = db.collection("InfoWrite").document("docu").collection("제목");
        //get()을 통해서 해당 컬렉션의 정보를 가져온다.
        productRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //작업이 성공적으로 마쳤을때
                if (task.isSuccessful()) {
                    //컬렉션 아래에 있는 모든 정보를 가져온다.
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //document.getData() or document.getId() 등등 여러 방법으로
                        //데이터를 가져올 수 있다.
                        Map a =  document.getData();
                        String b =  document.getId();
                        Log.v("테스트",String.valueOf(a));
                        Log.v("테스트",b);
                    }
                    //그렇지 않을때
                } else {

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