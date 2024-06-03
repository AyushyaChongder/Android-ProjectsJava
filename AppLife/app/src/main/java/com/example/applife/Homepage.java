package com.example.applife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Homepage extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    TextView lifecycleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Log.d(TAG, "onCreate() called");
        Toast.makeText(this, "onCreate() called", Toast.LENGTH_SHORT).show();

        lifecycleTextView = findViewById(R.id.lifecycle_text);

        FloatingActionButton fab = findViewById(R.id.play_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Homepage.this,Landingpage.class);
                startActivity(intent);
                appendToLifecycleText("FAB Clicked");


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
        appendToLifecycleText("onStart() called");
        Toast.makeText(this, "onStart() called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
        appendToLifecycleText("onResume() called");
        Toast.makeText(this, "onResume() called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
        appendToLifecycleText("onPause() called");
        Toast.makeText(this, "onPause() called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
        appendToLifecycleText("onStop() called");
        Toast.makeText(this, "onStop() called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
        appendToLifecycleText("onDestroy() called");
        Toast.makeText(this, "onDestroy() called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
        appendToLifecycleText("onRestart() called");
        Toast.makeText(this, "onRestart() called", Toast.LENGTH_SHORT).show();
    }

    private void appendToLifecycleText(String text) {
        String currentText = lifecycleTextView.getText().toString();
        currentText += "\n" + text;
        lifecycleTextView.setText(currentText);
    }
}
