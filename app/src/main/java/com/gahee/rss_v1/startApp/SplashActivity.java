package com.gahee.rss_v1.startApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gahee.rss_v1.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
    }
}
