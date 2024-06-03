package com.example.dreamapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Landing extends AppCompatActivity {
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        loginBtn=findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(Landing.this, LoggedIn.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
                else {
                    startActivity(new Intent(Landing.this, Login.class));
                    finish();
                }
            }
        });
    }
}