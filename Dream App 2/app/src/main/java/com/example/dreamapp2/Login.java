package com.example.dreamapp2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private TextInputLayout emailL, passwordL;
    private Button login, signup;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login Here");
        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#525252"));
        actionBar.setBackgroundDrawable(colorDrawable);

        emailL = findViewById(R.id.email);
        passwordL = findViewById(R.id.password);
        login = findViewById(R.id.loginBtn);
        signup = findViewById(R.id.signupBtn);
        auth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Signup.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = emailL.getEditText().getText().toString();
                password = passwordL.getEditText().getText().toString();
                Log.d("DocumentOfLogin", "email : " + email + " password : " + password);
                loginUser(email, password);
            }
        });
    }

    private void loginUser(String email, String password) {
        Log.d("DocumentOfLoginUser", "email : " + email + " password : " + password);
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.d("DocumentOfLoginUserSuccess", "email : " + email + " password : " + password);
                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this, LoggedIn.class));
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overlay, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();

        if (id==R.id.settings) {
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        } else if (id==R.id.exit) {
            finishAffinity();
        }
        return super.onOptionsItemSelected(item);
    }
}