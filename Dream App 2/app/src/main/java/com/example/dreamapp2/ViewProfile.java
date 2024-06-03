package com.example.dreamapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ViewProfile extends AppCompatActivity {
    private FloatingActionButton fab;
    private ImageView profilePicture;
    private TextView usernameTextView, stateTextView, genderTextView, emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        setTitle("My profile");
        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#525252"));
        actionBar.setBackgroundDrawable(colorDrawable);

        fab = findViewById(R.id.go_back);
        profilePicture = findViewById(R.id.profilePicture);
        usernameTextView = findViewById(R.id.usernameHead);
        stateTextView = findViewById(R.id.stateHead);
        genderTextView = findViewById(R.id.genderHead);
        emailTextView = findViewById(R.id.emailHead);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProfile.this, LoggedIn.class));
                finish();
            }
        });

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://dream-app-62cc8-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference databaseReference = firebaseDatabase.getReference("profiles");
        DatabaseReference profileReference = databaseReference.child(userID);

        profileReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String username = snapshot.child("Username").getValue(String.class);
                    String state = snapshot.child("State").getValue(String.class);
                    String gender = snapshot.child("Gender").getValue(String.class);
                    String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                    Log.d("Details", "Username : " + username + " State : " + state + " Gender : " +gender + "Email : " + email);

                    usernameTextView.setText("Username : " + username);
                    stateTextView.setText("State : " + state);
                    genderTextView.setText("Gender : " + gender);
                    emailTextView.setText("Email : " + email);

                    if (gender.equals("Male")) {
                        profilePicture.setImageResource(R.drawable.ryan);
                    }
                    else  {
                        profilePicture.setImageResource(R.drawable.ana);
                    }
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ViewProfile.this);
                    builder.setTitle("Profile Not Found");
                    builder.setMessage("No profile information found for this user.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    builder.setCancelable(false);
                    builder.show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
            startActivity(new Intent(ViewProfile.this, LoggedIn.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}