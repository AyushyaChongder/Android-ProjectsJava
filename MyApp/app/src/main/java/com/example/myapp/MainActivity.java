package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Button btn;
    EditText username;
    EditText password;
    EditText email;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= (Button)findViewById(R.id.button);
        username=(EditText) findViewById(R.id.editTextText);
        email=(EditText) findViewById(R.id.editTextTextEmailAddress);
        password=(EditText) findViewById(R.id.editTextTextPassword2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=username.getText().toString();
                String pwd=password.getText().toString();
                String eid=email.getText().toString();
                if(v==btn)
                {

                    if(uname.equals("")==false && pwd.equals("")==false)
                    {
                        Toast.makeText(MainActivity.this,"You have signed up successfully!",Toast.LENGTH_SHORT).show();
                        //after sign up redirect to homepage
                        Intent intent=new Intent(MainActivity.this,HomePage.class);
                        intent.putExtra("username", uname);
//                        intent.putExtra("email", eid);
//                        intent.putExtra("password", pwd);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Please fill in the fields!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}