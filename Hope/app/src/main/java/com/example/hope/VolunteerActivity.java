package com.example.hope;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.FragmentTransaction;

import com.example.hope.fragments.FriendsFragment;

public class VolunteerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        Button addActivityButton = findViewById(R.id.addActivity);
        Button viewActivityButton = findViewById(R.id.viewActivity);
        viewActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VolunteerActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace the current fragment with the ActivitiesFragment
                FriendsFragment activitiesFragment = new FriendsFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, activitiesFragment); // Use your fragment container ID
                transaction.addToBackStack(null); // Optional: Add to back stack
                transaction.commit();
            }
        });
    }
}
