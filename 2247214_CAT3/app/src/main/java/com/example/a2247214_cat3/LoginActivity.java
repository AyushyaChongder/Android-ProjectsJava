package com.example.a2247214_cat3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.a2247214_cat3.databinding.ActivityLoginBinding; // Import the correct binding class.

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding; // Use the correct binding class name.
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater()); // Inflate the binding layout.
        setContentView(binding.getRoot());

        databaseHelper = DatabaseHelper.getInstance(this);

        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();
                if (email.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);
                    if (checkCredentials) {
                        Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomepageActivity.class); // Assuming Onboard1 is the correct activity name.
                        intent.putExtra("email", email);
                        startActivity(intent);
                        // Create a new instance of the Profile fragment
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        TextView login = findViewById(R.id.textView4);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
