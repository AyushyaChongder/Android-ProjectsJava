package com.example.mentoring;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StudentSignupActivity extends AppCompatActivity {
Button signup_stu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);
        signup_stu=findViewById(R.id.student_signup);
        signup_stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudentSignupActivity.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}