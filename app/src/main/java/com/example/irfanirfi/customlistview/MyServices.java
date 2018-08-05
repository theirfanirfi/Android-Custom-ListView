package com.example.irfanirfi.customlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyServices extends AppCompatActivity {
    Button start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_services);
        start  = (Button) findViewById(R.id.startBtn);
        stop  = (Button) findViewById(R.id.stopBtn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyServices.this,NotficationServie.class);
                startService(i);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyServices.this,NotficationServie.class);
                stopService(i);
            }
        });
    }
}
