// HomePage.java
package com.example.sharepref;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    TextView nameTextView, emailTextView, passwordTextView;
    EditText newPasswordEditText, newPhoneEditText;
    Button saveDetailsButton, deleteAccountButton;
    DatabaseHelper databaseHelper;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        nameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.eml);
        passwordTextView = findViewById(R.id.pwd);
        newPasswordEditText = findViewById(R.id.new_password);
        newPhoneEditText = findViewById(R.id.editTextPhone);
        saveDetailsButton = findViewById(R.id.button);
        deleteAccountButton = findViewById(R.id.button2);

        databaseHelper = new DatabaseHelper(this);

        userEmail = getIntent().getStringExtra("user_email");
        if (userEmail != null) {
            loadUserData(userEmail); // Load user data
        }

        saveDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPass = newPasswordEditText.getText().toString();
                String newPhone = newPhoneEditText.getText().toString();

                if (databaseHelper.updatePasswordAndPhone(userEmail, newPass, newPhone)) {
                    Toast.makeText(HomePage.this, "Details updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomePage.this, "Failed to update details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEmail != null && databaseHelper.deleteUserByEmail(userEmail)) {
                    Toast.makeText(HomePage.this, "Account deleted successfully", Toast.LENGTH_SHORT).show();
                    // Add code here to navigate back to the login screen or perform other necessary actions
                } else {
                    Toast.makeText(HomePage.this, "Failed to delete account", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadUserData(String email) {
        Cursor cursor = databaseHelper.getUserByEmail(email);
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));

            nameTextView.setText(name);
            emailTextView.setText(email);
            passwordTextView.setText(password);
            newPhoneEditText.setText(phone);

            cursor.close();
        }
    }

}
