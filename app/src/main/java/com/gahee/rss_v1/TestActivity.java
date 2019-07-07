package com.gahee.rss_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gahee.rss_v1.unsplash.UnsplashUtils;


public class TestActivity extends AppCompatActivity {

    private static final String TAG = TestActivity.class.getSimpleName();
    private static final String BASE_URL = "http://rss.cnn.com/rss/";

    private TextView textView;
    private Button button;
    private StringBuffer desc;
    private ImageView imageView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        textView = findViewById(R.id.tv_test);
        button = findViewById(R.id.btn_test);
        imageView = findViewById(R.id.img_test);
        webView = findViewById(R.id.test_webview);

        UnsplashUtils unsplashUtils = new UnsplashUtils(this, imageView);
        unsplashUtils.getRandomPhotoBasedOnQuery("tigers");

        webView.loadUrl("https://edition.cnn.com/videos/weather/2019/07/05/daily-weather-forecast-severe-storms-weekend-weather-heat-rain.cnn");

    }




}
