package com.example.christbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class testpage extends AppCompatActivity {
TextView pt1,pt2,check_1,check_2;
ImageView cu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testpage);
        pt1=findViewById(R.id.pt_1);
        pt2=findViewById(R.id.pt_2);
        check_1=findViewById(R.id.check1);
        check_2=findViewById(R.id.check2);
        cu=findViewById(R.id.logo1);
        check_1.setText("");
        check_2.setText("");
        cu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(testpage.this, Homepage.class);
                startActivity(intent);
            }
        });
        pt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val="Attempted";
                String googleFormUrl = "https://www.indiabix.com/online-test/aptitude-test/1";
                Uri googleFormUri = Uri.parse(googleFormUrl);
                Intent googleFormIntent = new Intent(Intent.ACTION_VIEW, googleFormUri);
                startActivity(googleFormIntent);
                check_1.setText(val);
            }
        });
        pt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val1="Attempted";
                String googleFormUrl1 = "https://www.indiabix.com/online-test/aptitude-test/2";
                Uri googleFormUri = Uri.parse(googleFormUrl1);
                Intent googleFormIntent = new Intent(Intent.ACTION_VIEW, googleFormUri);
                startActivity(googleFormIntent);
                check_2.setText(val1);
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