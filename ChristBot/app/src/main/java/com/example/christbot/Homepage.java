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
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Homepage extends AppCompatActivity {
   ImageView logo,p1,p2,e1,e2;
   TextView uid,apt_test,sem,job;
   FloatingActionButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        String regno=getIntent().getStringExtra("regno");
        uid=findViewById(R.id.RegNo);
        uid.setText(regno);
        logo=findViewById(R.id.logo);
        apt_test=findViewById(R.id.aptitude_tests);
        sem=findViewById(R.id.seminar_info);
        job=findViewById(R.id.job_details);
        p1=findViewById(R.id.phone1);
        p2=findViewById(R.id.phone2);
        e1=findViewById(R.id.email1);
        e2=findViewById(R.id.email2);
        button = findViewById(R.id.fabtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initializing the popup menu and giving the reference as current context
                PopupMenu popupMenu = new PopupMenu(Homepage.this, button);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int itemId = menuItem.getItemId();
                        // Toast message on menu item clicked
                        if(itemId==R.id.logout)
                        {
                            Toast.makeText(Homepage.this, "Logging you out.......", Toast.LENGTH_SHORT).show();
                            openActivity(MainActivity.class);

                        }
                        else if(itemId==R.id.close)
                        {
                            Toast.makeText(Homepage.this, "Closing App.......", Toast.LENGTH_SHORT).show();
                            finishAffinity();
                        }

                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String webpageUrl = "https://kp.christuniversity.in/KnowledgePro/StudentLogin.do";
                Uri webpageUri = Uri.parse(webpageUrl);
                Intent webpageIntent = new Intent(Intent.ACTION_VIEW, webpageUri);
                startActivity(webpageIntent);
            }
        });
        apt_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Homepage.this, testpage.class);
                startActivity(intent);
            }
        });
        sem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Homepage.this, seminar.class);
                startActivity(intent);
            }
        });
        job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Homepage.this, job_info.class);
                startActivity(intent);
            }
        });
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber1 = "6204081417";
                Uri phoneUri = Uri.parse("tel:" + phoneNumber1);
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, phoneUri);
                startActivity(dialIntent);
            }
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber2 = "9523835835";
                Uri phoneUri1 = Uri.parse("tel:" + phoneNumber2);
                Intent dialIntent1 = new Intent(Intent.ACTION_DIAL, phoneUri1);
                startActivity(dialIntent1);
            }
        });
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress1 = "singh.aniket1406@gmail.com";
                Uri emailUri = Uri.parse("mailto:" + emailAddress1);
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, emailUri);
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject of the email"); Optional subject
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body of the email"); Optional body
                emailIntent.setPackage("com.google.android.gm"); // Specify Gmail package name
                startActivity(emailIntent);
                //startActivity(Intent.createChooser(emailIntent, "Send email"));
            }
        });
        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress2 = "harsh00var@gmail.com";
                Uri emailUri1 = Uri.parse("mailto:" + emailAddress2);
                Intent emailIntent1 = new Intent(Intent.ACTION_SENDTO, emailUri1);
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject of the email"); Optional subject
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body of the email"); Optional body
                emailIntent1.setPackage("com.google.android.gm"); // Specify Gmail package name
                startActivity(emailIntent1);
                //startActivity(Intent.createChooser(emailIntent1, "Send email"));
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
                openActivity(MainActivity.class);

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