package com.example.hope;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private EditText locationEditText;
    private EditText dateEditText;
    private EditText messageEditText;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        locationEditText = findViewById(R.id.locationEditText);
        dateEditText = findViewById(R.id.dateEditText);
        messageEditText = findViewById(R.id.messageEditText);
        Button addActivityButton = findViewById(R.id.addActivityButton);

        databaseHelper = new DatabaseHelper(this);

        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addActivity();
            }
        });
    }




    private void addActivity() {
        String location = locationEditText.getText().toString();
        String date = dateEditText.getText().toString();
        String message = messageEditText.getText().toString();

        if (!location.isEmpty() && !date.isEmpty() && !message.isEmpty()) {
            // Retrieve the volunteer's ID (you need to implement this method)
            int volunteerId = getIntent().getIntExtra("volunteerId", -1);// Replace with the actual method to get the volunteer's ID

            if (volunteerId != -1) {
                boolean success = databaseHelper.insertActivity(volunteerId, location, date, message);

                if (success) {
                    Toast.makeText(this, "Activity added successfully", Toast.LENGTH_SHORT).show();
                    // Clear input fields
                    locationEditText.setText("");
                    dateEditText.setText("");
                    messageEditText.setText("");
                } else {
                    Toast.makeText(this, "Failed to add activity", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Volunteer ID not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

    }


