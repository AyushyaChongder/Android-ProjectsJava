package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class displaypage extends AppCompatActivity {
TextView uid1,uid2,uid3,uid4,start_dt,end_dt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaypage);
        String uname = getIntent().getStringExtra("name");
        String uemail = getIntent().getStringExtra("email");
        String uphone = getIntent().getStringExtra("phone");
        String uage = getIntent().getStringExtra("age");
        String ustart_date=getIntent().getStringExtra("start_d");
        String uend_date=getIntent().getStringExtra("end_d");
        uid1 = findViewById(R.id.dname);
        uid1.setText(uname);
        uid2 = findViewById(R.id.demail);
        uid2.setText(uemail);
        uid3 = findViewById(R.id.dphone);
        uid3.setText(uphone);
        uid4 = findViewById(R.id.dage);
        uid4.setText(uage);
        start_dt=findViewById(R.id.startdate);
        start_dt.setText(ustart_date);
        end_dt=findViewById(R.id.enddate);
        end_dt.setText(uend_date);


    }
}