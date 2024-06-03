package com.example.cat3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilePage extends AppCompatActivity {
    TextView nameTextView, emailTextView, passwordTextView;
    EditText newPasswordEditText;
    Button saveDetailsButton, deleteAccountButton;
    DatabaseHelper databaseHelper;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        nameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.eml);
        passwordTextView = findViewById(R.id.pwd);
        newPasswordEditText = findViewById(R.id.new_password);
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


                if (databaseHelper.updatePasswordAndPhone(userEmail, newPass)) {
                    Toast.makeText(ProfilePage.this, "Details updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfilePage.this, "Failed to update details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEmail != null && databaseHelper.deleteUserByEmail(userEmail)) {
                    Toast.makeText(ProfilePage.this, "Account deleted successfully", Toast.LENGTH_SHORT).show();
                    // Add code here to navigate back to the login screen or perform other necessary actions
                } else {
                    Toast.makeText(ProfilePage.this, "Failed to delete account", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadUserData(String email) {
        Cursor cursor = databaseHelper.getUserByEmail(email);
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));

            nameTextView.setText(name);
            emailTextView.setText(email);
            passwordTextView.setText(password);

            cursor.close();
        }
    }

}
