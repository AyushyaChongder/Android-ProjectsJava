package com.example.mentoring;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginSignUp extends AppCompatActivity {
    Button login_btn, signup_btn;
    TextView stu_men;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);
        String buttonText=getIntent().getStringExtra("buttonText");
        login_btn = findViewById(R.id.login);
        signup_btn = findViewById(R.id.signup);
        stu_men = findViewById(R.id.choice);
        stu_men.setText(buttonText);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the text in the TextView
                String choice_text=stu_men.getText().toString();
                if(choice_text.equalsIgnoreCase("Mentor")==true)
                {
                    Intent intent = new Intent(LoginSignUp.this, MentorLoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(LoginSignUp.this, StudentLoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String choice_text=stu_men.getText().toString();
                if(choice_text.equalsIgnoreCase("Mentor")==true)
                {
                    Intent intent = new Intent(LoginSignUp.this, MentorSignupActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(LoginSignUp.this, StudentSignupActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
