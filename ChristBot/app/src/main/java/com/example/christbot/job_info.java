package com.example.christbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class job_info extends AppCompatActivity {
Button ga,hpa;
ImageView cu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);
        ga=findViewById(R.id.g_apply_btn);
        hpa=findViewById(R.id.hp_apply_btn);
        cu=findViewById(R.id.logo1);
        ga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkedInProfileUrl= "https://www.linkedin.com/company/google/?originalSubdomain=in";
                Uri linkedInProfileUri = Uri.parse(linkedInProfileUrl);
                Intent linkedInIntent = new Intent(Intent.ACTION_VIEW, linkedInProfileUri);
                startActivity(linkedInIntent);
            }
        });
        hpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkedInProfileUrl1= "https://www.linkedin.com/company/hewlett-packard-enterprise/";
                Uri linkedInProfileUri1 = Uri.parse(linkedInProfileUrl1);
                Intent linkedInIntent1 = new Intent(Intent.ACTION_VIEW, linkedInProfileUri1);
                startActivity(linkedInIntent1);
            }
        });
        cu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(job_info.this, Homepage.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.main_menu, menu);

        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_item_id) {
            // Handle click on "Home" menu item
                openActivity(Homepage.class);
            return true;
        } else if (itemId == R.id.menu_item_id1) {
            // Handle click on "About" menu item
            openActivity(MainActivity.class);
            return true;
        } else if (itemId == R.id.menu_item_id2) {
            // Handle click on "Aptitude Tests" menu item
            openActivity(testpage.class);
            return true;
        } else if (itemId == R.id.menu_item_id3) {
            // Handle click on "Weekly Seminar" menu item
            openActivity(seminar.class);
            return true;
        } else if (itemId == R.id.menu_item_id4) {
            // Handle click on "Recent Jobs" menu item
            openActivity(job_info.class);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

}