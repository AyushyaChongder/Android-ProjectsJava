package com.example.hope;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {
    Button started;
    Dialog currentDialog;
    DatabaseHelper databaseHelper;
    NotificationManagerCompat notificationManagerCompat;
    Notification notification;
    String name;
    String loggedInEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        started = findViewById(R.id.bottomsheet1);
        started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(true); // Show sign-up bottom sheet
            }
        });
    }

    private void showDialog(boolean isSignUp) {
        if (currentDialog != null && currentDialog.isShowing()) {
            currentDialog.dismiss(); // Dismiss the currently displayed dialog
        }

        currentDialog = new Dialog(this);
        currentDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        currentDialog.setContentView(isSignUp ? R.layout.signup_bottom_sheet : R.layout.login_bottom_sheet);
        currentDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        currentDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        currentDialog.getWindow().getAttributes().windowAnimations = R.style.DialoAnimation;
        currentDialog.getWindow().setGravity(Gravity.BOTTOM);

        if (isSignUp) {
            Button signupButton = currentDialog.findViewById(R.id.signup_main);
            signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle signup here
                    EditText nameEditText = currentDialog.findViewById(R.id.signup_name);
                    EditText emailEditText = currentDialog.findViewById(R.id.signup_email);
                    EditText passwordEditText = currentDialog.findViewById(R.id.signup_password);

                    String name = nameEditText.getText().toString();
                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    EditText roleEditText = currentDialog.findViewById(R.id.signup_role);
                    String role = roleEditText.getText().toString();


                    if (name.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Kindly fill in all the fields!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!databaseHelper.checkEmail(email)) {
                            if (databaseHelper.insertData(name, email, password, role)) {
                                showNotification("Signup Successful");
                                currentDialog.dismiss();
                            } else {
                                Toast.makeText(MainActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
            Button loginButton1 = currentDialog.findViewById(R.id.login); // Login button on signup bottom sheet
            loginButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(false); // Show login bottom sheet
                }
            });
        } else {
            Button loginButton = currentDialog.findViewById(R.id.login_main);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle login here
                    EditText emailEditText = currentDialog.findViewById(R.id.login_email);
                    EditText passwordEditText = currentDialog.findViewById(R.id.login_password);

                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                    if (databaseHelper.checkEmailPassword(email, password)) {
                        Cursor cursor = databaseHelper.getUserByEmail(email);
                        if (cursor != null && cursor.moveToFirst()) {
                            name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                            loggedInEmail = email;
                        }
                        String role = cursor.getString(cursor.getColumnIndexOrThrow("role"));

                        // Check the user's role and perform role-specific actions
                        if ("Volunteer".equals(role)) {
                            // Navigate to the volunteer activity
                            Intent intent = new Intent(MainActivity.this, VolunteerActivity.class);
                            intent.putExtra("user_email", email); // Pass the user's email as an extra
                            startActivity(intent);
                        } else if ("Administrator".equals(role)) {
                            // Navigate to the administrator activity
                            Intent intent = new Intent(MainActivity.this, AdministratorActivity.class);
                            intent.putExtra("user_email", email); // Pass the user's email as an extra
                            startActivity(intent);
                        }

                        showNotification("Welcome " + name + "!" + " You have successfully logged in!");
                        currentDialog.dismiss();
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }

                    databaseHelper.close();
                }
            });
        }

        currentDialog.show();
    }

    @SuppressLint("MissingPermission")
    private void showNotification(String message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "mych")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Login/Signup Status")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notification = builder.build();

        notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, notification);

    }

    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel("mych", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}
