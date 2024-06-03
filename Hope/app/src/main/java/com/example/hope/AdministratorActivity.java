package com.example.hope;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class AdministratorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    // Define your EditText fields for location, date, and message
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private EditText locationEditText;
    private EditText dateEditText;
    private EditText messageEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        locationEditText = findViewById(R.id.locationEditText);
        dateEditText = findViewById(R.id.dateEditText);
        messageEditText = findViewById(R.id.messageEditText);
        //navigation drawer
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar_icon);
        setSupportActionBar(toolbar);//toolbar



        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_administrator);


        Button sendNotificationButton = findViewById(R.id.btnPushInfo);
        sendNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    // Get the input values
                    Intent intent = getIntent();
                    String location = locationEditText.getText().toString();
                    String date = dateEditText.getText().toString();
                    String message = messageEditText.getText().toString();

                    // Send the push notification
                    sendPushNotification(getApplicationContext(), "New Activity", "Location: " + location + "\nDate: " + date + "\nMessage: " + message);

                    // Clear the input fields
                    locationEditText.setText("");
                    dateEditText.setText("");
                    messageEditText.setText("");
                }
            });
        }

    // Function to insert activity details into the database using your DatabaseHelper
    private boolean insertActivity(int volunteerId,String location, String date, String message) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        return databaseHelper.insertActivity(volunteerId, location, date, message);
    }


    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("myChannelId", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @SuppressLint("MissingPermission")
    private void sendPushNotification(Context context, String title, String message) {
        createNotificationChannel(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "myChannelId")
                .setSmallIcon(R.drawable.notification_icon) // Replace with your notification icon
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build()); // You can use a unique notification ID
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        final int itemId = menuItem.getItemId();

        if (itemId == R.id.nav_administrator) {
            // Do nothing for "Home" item
        } else if (itemId == R.id.nav_Volunteer) {
            Intent intent = new Intent(AdministratorActivity.this, VolunteerActivity.class);
            startActivity(intent);
        }


        else if (itemId == R.id.nav_share) {
            Toast.makeText(this, "Your Profile is Shared!", Toast.LENGTH_SHORT).show();
        }
        else if (itemId == R.id.nav_logout) {
            // Handle "Share" click
            Toast.makeText(this, "Logging you out", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AdministratorActivity.this, MainActivity.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
