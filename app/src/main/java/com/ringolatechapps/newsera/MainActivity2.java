package com.ringolatechapps.newsera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity2 extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String link = getIntent().getStringExtra("link");

        webView = findViewById(R.id.web_id);
        webView.loadUrl(link);

    }
}