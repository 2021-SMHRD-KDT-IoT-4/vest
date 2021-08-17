package com.example.smartvest;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dinuscxj.progressbar.CircleProgressBar;


public class Worker_Main_Activity extends AppCompatActivity implements CircleProgressBar.ProgressFormatter {

    private static final String DEFAULT_PATTERN = "%d%%";

    TextView tv_worker_id, tv_weather,tv_co,tv_ch4,tv_lpg;
       ImageButton btn_qr,btn_worker_notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_main);
        tv_worker_id=findViewById(R.id.tv_worker_id);
        tv_weather=findViewById(R.id.tv_weather);
        tv_co=findViewById(R.id.tv_co);
        tv_ch4=findViewById(R.id.tv_ch4);
        tv_lpg=findViewById(R.id.tv_lpg);
        btn_qr=findViewById(R.id.btn_qr);
        btn_worker_notice=findViewById(R.id.btn_worker_notice);


        CircleProgressBar circleProgressBar = findViewById(R.id.cpb_circlebar);
        CircleProgressBar circleProgressBar2 = findViewById(R.id.cpb_circlebar2);
        CircleProgressBar circleProgressBar3 = findViewById(R.id.cpb_circlebar3);

        circleProgressBar.setProgress(10);  // 해당 퍼센트를 적용
        circleProgressBar2.setProgress(50);
        circleProgressBar3.setProgress(30);

        btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Worker_Main_Activity.this,QRScan.class);
                startActivity(intent);

            }
        });

    }// onCreate()..



    @Override
    public CharSequence format(int progress, int max) {
        return String.format(DEFAULT_PATTERN, (int) ((float) progress / (float) max * 100));
    }

}// MainActivity Class..