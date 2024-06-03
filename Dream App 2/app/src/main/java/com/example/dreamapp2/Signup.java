package com.example.dreamapp2;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    private Button signup;
    private TextInputLayout emailL, passwordL, passwordAgainL;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setTitle("Signup Here");
        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#525252"));
        actionBar.setBackgroundDrawable(colorDrawable);

        signup = findViewById(R.id.signupBtn);
        emailL = findViewById(R.id.email);
        passwordL = findViewById(R.id.password);
        passwordAgainL = findViewById(R.id.passwordAgain);
        auth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password, passwordAgain;
                email = emailL.getEditText().getText().toString();
                password = passwordL.getEditText().getText().toString();
                passwordAgain = passwordAgainL.getEditText().getText().toString();

                Log.d("Document", "email :" + email + " password :" + password + " password again : " + passwordAgain);

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(passwordAgain)) {
                    Toast.makeText(Signup.this, "Empty credentials. Try again", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.length()<=6 || passwordAgain.length()<=6) {
                        Toast.makeText(Signup.this, "Password too small", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (password.equals(passwordAgain)) {
                            registerUser(email, password);
                        }
                        else {
                            Toast.makeText(Signup.this, "Password do not match", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Signup.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Signup.this, LoggedIn.class));
                    finish();
                }
                else {
                    Toast.makeText(Signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
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