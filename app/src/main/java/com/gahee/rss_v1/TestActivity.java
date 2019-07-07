package com.gahee.rss_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gahee.rss_v1.CNN.CnnAPI;
import com.gahee.rss_v1.CNN.TagRss;
import com.gahee.rss_v1.CNN.TagItem;
import com.gahee.rss_v1.unsplash.UnsplashUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveSpaceFeed();
            }
        });

        UnsplashUtils unsplashUtils = new UnsplashUtils(this, imageView);
        unsplashUtils.getRandomPhotoBasedOnQuery("tigers");

        webView.loadUrl("https://edition.cnn.com/videos/weather/2019/07/05/daily-weather-forecast-severe-storms-weekend-weather-heat-rain.cnn");

    }

    private void retrieveTechFeed(){
        //read url using retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        CnnAPI cnnAPI = retrofit.create(CnnAPI.class);

        Call<TagRss> call = cnnAPI.getEditionTechnologyFeed();

        call.enqueue(new Callback<TagRss>() {

            @Override
            public void onResponse(Call<TagRss> call, Response<TagRss> response) {
                Log.d(TAG, "channel - title : " + response.body().getChannel().getTitle());
                Log.d(TAG, "description : " + response.body().getChannel().getItem().get(1).getDescription());

                List<TagItem> items = response.body().getChannel().getItem();
                desc = new StringBuffer();
                for(int i = 0; i < items.size(); i++){
                    desc.append(i + " : " + response.body().getChannel().getItem().get(i).getDescription() + "\n");
            //read data from the network, and store the data in a list
            //send this list to the rv adapter
            //

                }

                textView.setText(desc.toString());

            }

            @Override
            public void onFailure(Call<TagRss> call, Throwable t) {
                Log.e(TAG, "onFailure : Unable to retrieve RSS : " + t.getMessage());
            }
        });

    }

    private void retrieveSpaceFeed(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        CnnAPI cnnAPI = retrofit.create(CnnAPI.class);

        Call<TagRss> call = cnnAPI.getEditionSpaceFeed();

        call.enqueue(new Callback<TagRss>() {

            @Override
            public void onResponse(Call<TagRss> call, Response<TagRss> response) {
                Log.d(TAG, "channel - title : " + response.body().getChannel().getTitle());
                Log.d(TAG, "description : " + response.body().getChannel().getItem().get(1).getDescription());

                List<TagItem> items = response.body().getChannel().getItem();
                desc = new StringBuffer();
                for(int i = 0; i < items.size(); i++){
                    desc.append(i + " : " + response.body().getChannel().getItem().get(i).getDescription() + "\n");

                }

                textView.setText(desc.toString());

            }

            @Override
            public void onFailure(Call<TagRss> call, Throwable t) {
                Log.e(TAG, "onFailure : Unable to retrieve RSS : " + t.getMessage());
            }
        });
    }





}
