package com.example.christbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText regno;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= (Button)findViewById(R.id.login_btn);
        regno=(EditText) findViewById(R.id.regno);
        password=(EditText) findViewById(R.id.password);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rno=regno.getText().toString();
                String pwd=password.getText().toString();
                if(v==btn)
                {

                    if(rno.equals("2247146")==true && pwd.equals("39278049")==true)
                    {
                        Toast.makeText(MainActivity.this,"You have logged in successfully!",Toast.LENGTH_SHORT).show();
                        //after sign up redirect to homepage
                        Intent intent=new Intent(MainActivity.this, Homepage.class);
                        intent.putExtra("regno", rno);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Please fill in the fields correctly!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}