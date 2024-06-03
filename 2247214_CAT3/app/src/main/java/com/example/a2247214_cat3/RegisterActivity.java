package com.example.a2247214_cat3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.a2247214_cat3.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Spinner postSpinner = binding.spinner;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, // Use "this" to get the context of the activity
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ((Spinner) postSpinner).setAdapter(adapter);

        adapter.add("Student");
        adapter.add("Professor");

        databaseHelper = DatabaseHelper.getInstance(this);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.name.getText().toString();
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();
                String selectedPost = postSpinner.getSelectedItem().toString(); // Get the selected Spinner value

                if (name.equals("") || password.equals("") || email.equals("")) {
                    Toast.makeText(RegisterActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    if (!databaseHelper.checkEmail(email)) {
                        Boolean insert = databaseHelper.insertData(name, email, password, selectedPost); // Assign selectedPost to post variable
                        if (insert) {
                            Toast.makeText(RegisterActivity.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            // Create bundle and pass email to Profile fragment
                        } else {
                            Toast.makeText(RegisterActivity.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
