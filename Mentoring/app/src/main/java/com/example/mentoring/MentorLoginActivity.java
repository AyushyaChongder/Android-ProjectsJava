package com.example.mentoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MentorLoginActivity extends AppCompatActivity {
    Button login_btn;
    EditText men_email, men_password;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentorlogin);
        login_btn=findViewById(R.id.student_signup);
        men_email = findViewById(R.id.mentor_email);
        men_password = findViewById(R.id.mentor_password);
        auth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email, password;
                email = men_email.getText().toString();
                password = men_password.getText().toString();
                loginUser(email, password);
            }
        });
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MentorLoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MentorLoginActivity.this, MentorHomepage.class);
                startActivity(intent);
            }
        });
    }
}