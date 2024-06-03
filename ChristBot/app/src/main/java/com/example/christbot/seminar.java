package com.example.christbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class seminar extends AppCompatActivity {
ImageView cu;
ImageView cl1,cl2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar);
        cu=findViewById(R.id.logo1);
        cl1=findViewById(R.id.test_icon1);
        registerForContextMenu(cl1);
        cl2=findViewById(R.id.test_icon2);
        registerForContextMenu(cl1);
        cu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(seminar.this, Homepage.class);
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
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // you can set menu header with title icon etc

        // add menu items
        menu.add(0, v.getId(), 0, "Open");
        menu.add(0, v.getId(), 0, "Notify");
    }

    // menu item select listener
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Open") {
            String webpageUrl = "https://meet.google.com/rkw-ciks-zuu";
            Uri webpageUri = Uri.parse(webpageUrl);
            Intent webpageIntent = new Intent(Intent.ACTION_VIEW, webpageUri);
            startActivity(webpageIntent);
        }
        else if (item.getTitle() == "Notify") {
            Toast.makeText(this, "You will be notified for future Seminars", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}