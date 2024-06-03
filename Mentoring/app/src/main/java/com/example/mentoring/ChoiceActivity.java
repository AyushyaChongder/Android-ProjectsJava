package com.example.mentoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {
    Button men_login, stu_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        men_login = findViewById(R.id.mentor);
        stu_login = findViewById(R.id.student);

        men_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btn_text = men_login.getText().toString();
                Intent intent = new Intent(ChoiceActivity.this, LoginSignUp.class);
                intent.putExtra("buttonText", btn_text); // Pass the button text as an extra
                startActivity(intent);
            }
        });

        stu_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btn_text = stu_login.getText().toString();
                Intent intent = new Intent(ChoiceActivity.this, LoginSignUp.class);
                intent.putExtra("buttonText", btn_text); // Pass the button text as an extra
                startActivity(intent);
            }
        });
    }
}
