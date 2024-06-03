package com.example.rasoi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class login extends AppCompatActivity {
TextView uname,pwd;
Button lgn_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname=findViewById(R.id.username);
        pwd=findViewById(R.id.password);
        lgn_btn=findViewById(R.id.login);
        lgn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_name=uname.getText().toString();
                String passwd=pwd.getText().toString();
                if(v==lgn_btn)
                {

                    if(u_name.equals(" ")==false && passwd.equals("123456")==true)
                    {
                        Snackbar.make(v, "You have logged in successfully!", Snackbar.LENGTH_LONG).show();
                        //after sign up redirect to homepage
                        Intent intent=new Intent(login.this, homepage.class);
                        intent.putExtra("uname", u_name);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(login.this,"Please fill in the fields correctly!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}