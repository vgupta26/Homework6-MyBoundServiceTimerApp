package com.example.vasugupta.homework6_myboundservicetimerapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button strservbtn, stpservbtn, tstartbtn, tstopbtn, tresetbtn;
    TextView tvalue;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;

    Handler handler;

    int Seconds, Minutes, MilliSeconds ;
    Runnable runnable = new Runnable() {
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = (int) (UpdateTime % 1000);
            tvalue.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));
            handler.postDelayed(this, 0);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        strservbtn = (Button) findViewById(R.id.mainstart);
        stpservbtn = (Button) findViewById(R.id.mainstop);
        tstartbtn = (Button) findViewById(R.id.watchstart);
        tresetbtn = (Button) findViewById(R.id.watchreset);
        tstopbtn = (Button) findViewById(R.id.watchstop);
        tvalue = (TextView) findViewById(R.id.timevalue);
        handler = new Handler() ;
        tstartbtn.setEnabled(false);
        tstopbtn.setEnabled(false);
        tresetbtn.setEnabled(false);

        strservbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startService(new Intent(getBaseContext(), Myfirstservice.class));
                tvalue.setVisibility(View.VISIBLE);

                //            Myfirstservice helloService = new Myfirstservice();
                tstartbtn.setEnabled(true);
                tstopbtn.setEnabled(true);

               // Toast.makeText(getApplicationContext(),"Start server btn", Toast.LENGTH_SHORT).show();
            }});
        stpservbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getBaseContext(), Myfirstservice.class));

               // Toast.makeText(getApplicationContext(),"Stop server btn", Toast.LENGTH_SHORT).show();
                tstartbtn.setEnabled(false);
                tstopbtn.setEnabled(false);
                tresetbtn.setEnabled(false);
                tvalue.setVisibility(View.INVISIBLE);
            }
        });
        tstartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                tresetbtn.setEnabled(false);
               // Toast.makeText(getApplicationContext(),"Clock Start", Toast.LENGTH_SHORT).show();
                }});
        tstopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeBuff += MillisecondTime;
                handler.removeCallbacks(runnable);
                tresetbtn.setEnabled(true);
           //     Toast.makeText(getApplicationContext(),"Clock Stop", Toast.LENGTH_SHORT).show();
            }});
        tresetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;
                tvalue.setText("00:00:00");
               // Toast.makeText(getApplicationContext(),"Clock reset", Toast.LENGTH_SHORT).show();
            }});}}

