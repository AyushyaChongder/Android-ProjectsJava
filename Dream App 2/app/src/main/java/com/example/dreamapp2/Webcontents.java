package com.example.dreamapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webcontents extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webcontents);

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        String value =intent.getStringExtra("key");

        if (value != null) {
            webView.loadUrl(value);
            webView.setWebViewClient(new WebViewClient());
        }
    }
}