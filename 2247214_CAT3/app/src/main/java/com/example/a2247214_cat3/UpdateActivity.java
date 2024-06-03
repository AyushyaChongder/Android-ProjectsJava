package com.example.a2247214_cat3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText newNameEditText, newPasswordEditText, newEmailEditText;
    Spinner newPostSpinner;
    Button updateButton;
    DatabaseHelper databaseHelper;
    String email; // Assuming you pass the email of the user to update.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        newNameEditText = findViewById(R.id.name);
        newEmailEditText = findViewById(R.id.email);
        newPasswordEditText = findViewById(R.id.password);
        newPostSpinner = findViewById(R.id.spinner);
        updateButton = findViewById(R.id.login);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newPostSpinner.setAdapter(adapter);
        adapter.add("Student");
        adapter.add("Professor");


        databaseHelper = DatabaseHelper.getInstance(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = newNameEditText.getText().toString().trim();
                String newEmail = newEmailEditText.getText().toString().trim();
                String newPassword = newPasswordEditText.getText().toString().trim();
                String selectedPost = newPostSpinner.getSelectedItem().toString();

                if (newName.isEmpty() || newPassword.isEmpty() || selectedPost.isEmpty() || newEmail.isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isUpdated = databaseHelper.updateUser(email, newName, newPassword, selectedPost);

                    if (isUpdated) {
                        Toast.makeText(UpdateActivity.this, "Details updated successfully", Toast.LENGTH_SHORT).show();
                        // Optionally, navigate back to the profile or another screen
                        //finish();
                    } else {
                        Toast.makeText(UpdateActivity.this, "Failed to update details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
