package com.example.smartvest;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {

    ListView lv;
    List<InfoVO> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        lv = findViewById(R.id.lv);
        data = new ArrayList<InfoVO>();

        for (int i = 1; i < 10; i++){
            data.add(new InfoVO("공지사항"+i,
                    "2021-08-0"+i));
        }


        InfoAdapter adapter = new InfoAdapter(getApplicationContext(),
                R.layout.infolist, data);

        lv.setAdapter(adapter);

    }
}