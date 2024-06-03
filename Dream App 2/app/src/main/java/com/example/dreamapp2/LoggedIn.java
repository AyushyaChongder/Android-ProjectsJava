package com.example.dreamapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class LoggedIn extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FloatingActionButton movieBtn, musicBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
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
        navigationView.setCheckedItem(R.id.nav_home);
        setTitle("Welcome User");
        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#525252"));
        actionBar.setBackgroundDrawable(colorDrawable);

        movieBtn = findViewById(R.id.movies_btn);
        musicBtn = findViewById(R.id.music_btn);

        movieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new MovieFragment());
            }
        });

        musicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new MusicFragment());
            }
        });
    }



    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logged_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();

        if (id==R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(LoggedIn.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoggedIn.this, Landing.class));
            finish();
        } else if (id == R.id.add_profile) {
            registerForContextMenu(findViewById(R.id.add_profile));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.profile) {
            Toast.makeText(LoggedIn.this, "Add profile clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoggedIn.this, AddProfile.class));
            finish();
        } else if (itemId == R.id.view_profile) {
            Toast.makeText(LoggedIn.this, "View profile clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoggedIn.this, ViewProfile.class));
            finish();
        }
        return super.onContextItemSelected(item);
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

        if (itemId == R.id.nav_home) {
            // Do nothing for "Home" item
        } else if (itemId == R.id.nav_logout) {
            Intent intent = new Intent(LoggedIn.this, Landing.class);
            startActivity(intent);
        } else if (itemId == R.id.nav_viewprofile) {
            Intent intent = new Intent(LoggedIn.this, ViewProfile.class);
            startActivity(intent);
        }
        else if (itemId == R.id.nav_editprofile) {
            Intent intent = new Intent(LoggedIn.this, AddProfile.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}