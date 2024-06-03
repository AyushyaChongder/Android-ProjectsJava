package com.example.dreamapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivLogo = findViewById(R.id.ivLogo);

        //Load animation
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        ivLogo.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Landing.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}