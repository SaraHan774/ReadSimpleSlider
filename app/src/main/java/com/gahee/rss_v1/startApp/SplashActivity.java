package com.gahee.rss_v1.startApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.gahee.rss_v1.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //put animations on the image views of splash screen here

        //check if the user is new to this app

        //if already a user of this app, then navigate to MainTabActivity

        Intent intent = new Intent(getApplicationContext(), StartActivity.class);

//        try{
//            Thread.sleep(1500);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }

        startActivity(intent);
        finish();

 //gone from the back stack
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
