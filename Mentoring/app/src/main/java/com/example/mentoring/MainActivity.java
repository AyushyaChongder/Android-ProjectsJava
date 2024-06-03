package com.example.mentoring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
Button go_to_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go_to_login=findViewById(R.id.get_started);
        go_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(MainActivity.this, MentorHomepage.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
                else {
                    Intent intent=new Intent(MainActivity.this,ChoiceActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}