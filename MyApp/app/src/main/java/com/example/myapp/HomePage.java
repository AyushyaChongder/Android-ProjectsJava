package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    Button btn;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        String username=getIntent().getStringExtra("username");
        name=findViewById(R.id.username);
        btn=findViewById(R.id.button2);
        name.setText(username);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==btn)
                {
                    Toast.makeText(HomePage.this,"Redirecting to Landing Page.....",Toast.LENGTH_SHORT).show();
                    Intent intent1=new Intent(HomePage.this, LandingPage.class);
                    startActivity(intent1);
                }
                else {
                    Toast.makeText(HomePage.this,"Error!!!!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}