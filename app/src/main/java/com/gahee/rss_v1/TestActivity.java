package com.gahee.rss_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gahee.rss_v1.CNN.FeedAPIcnn;
import com.gahee.rss_v1.CNN.FeedCnn;
import com.gahee.rss_v1.CNN.Item;
import com.gahee.rss_v1.unsplash.RandomPhoto;
import com.gahee.rss_v1.unsplash.Unsplash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = TestActivity.class.getSimpleName();
    private static final String BASE_URL = "http://rss.cnn.com/rss/";
    private static final String UNSPLASH_BASE_URL = "https://api.unsplash.com";
    private static final String ACCESS_KEY = BuildConfig.unsplashAccessKey;//Unsplash AccessKey goes here
    private TextView textView;
    private Button button;
    private StringBuffer desc;
    private String randomPhotoUrl;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        textView = findViewById(R.id.tv_test);
        button = findViewById(R.id.btn_test);
        imageView = findViewById(R.id.img_test);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveSpaceFeed();
            }
        });

        Map<String, String> params = new HashMap<>();
        params.put("query", "birds");
        params.put("client_id", ACCESS_KEY);

        Log.d(TAG, "hashmap : "  + params);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UNSPLASH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Unsplash unsplash = retrofit.create(Unsplash.class);
        final Call<RandomPhoto> randomPhotoCall = unsplash.getRandomPhoto(params);
        randomPhotoCall.enqueue(new Callback<RandomPhoto>() {
            @Override
            public void onResponse(Call<RandomPhoto> call, Response<RandomPhoto> response) {
                 randomPhotoUrl = response.body().getUrLs().getFull();
                Log.d(TAG, "connection successful (unsplash) randomPhotoUrl : " + randomPhotoUrl );
                loadImageFromGlide();
            }

            @Override
            public void onFailure(Call<RandomPhoto> call, Throwable t) {
                Log.d(TAG, "failed to fetch data from unsplash");
            }
        });
        //Glide executes before the completion of onResponse() -> Retrofit
    }

    private void retrieveTechFeed(){
        //read url using retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        FeedAPIcnn feedAPIcnn = retrofit.create(FeedAPIcnn.class);

        Call<FeedCnn> call = feedAPIcnn.getEditionTechnologyFeed();

        call.enqueue(new Callback<FeedCnn>() {

            @Override
            public void onResponse(Call<FeedCnn> call, Response<FeedCnn> response) {
                Log.d(TAG, "channel - title : " + response.body().getChannel().getTitle());
                Log.d(TAG, "description : " + response.body().getChannel().getItem().get(1).getDescription());

                List<Item> items = response.body().getChannel().getItem();
                desc = new StringBuffer();
                for(int i = 0; i < items.size(); i++){
                    desc.append(i + " : " + response.body().getChannel().getItem().get(i).getDescription() + "\n");

                }

                textView.setText(desc.toString());

            }

            @Override
            public void onFailure(Call<FeedCnn> call, Throwable t) {
                Log.e(TAG, "onFailure : Unable to retrieve RSS : " + t.getMessage());
            }
        });


    }

    private void retrieveSpaceFeed(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        FeedAPIcnn feedAPIcnn = retrofit.create(FeedAPIcnn.class);

        Call<FeedCnn> call = feedAPIcnn.getEditionSpaceFeed();

        call.enqueue(new Callback<FeedCnn>() {

            @Override
            public void onResponse(Call<FeedCnn> call, Response<FeedCnn> response) {
                Log.d(TAG, "channel - title : " + response.body().getChannel().getTitle());
                Log.d(TAG, "description : " + response.body().getChannel().getItem().get(1).getDescription());

                List<Item> items = response.body().getChannel().getItem();
                desc = new StringBuffer();
                for(int i = 0; i < items.size(); i++){
                    desc.append(i + " : " + response.body().getChannel().getItem().get(i).getDescription() + "\n");

                }

                textView.setText(desc.toString());

            }

            @Override
            public void onFailure(Call<FeedCnn> call, Throwable t) {
                Log.e(TAG, "onFailure : Unable to retrieve RSS : " + t.getMessage());
            }
        });
    }

    private void loadImageFromGlide(){
        Glide.with(this).load(randomPhotoUrl).into(imageView);
    }



}
