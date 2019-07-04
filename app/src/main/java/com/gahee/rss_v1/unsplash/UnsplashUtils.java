package com.gahee.rss_v1.unsplash;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gahee.rss_v1.BuildConfig;
import com.gahee.rss_v1.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnsplashUtils {

    private static final String TAG = UnsplashUtils.class.getSimpleName();
    private static final String UNSPLASH_BASE_URL = "https://api.unsplash.com";
    private static final String ACCESS_KEY = BuildConfig.unsplashAccessKey;//Unsplash AccessKey goes here

    private String randomPhotoUrl;
    private Activity activity;
    private ImageView imageView;

    public UnsplashUtils(Activity activity, ImageView imageView){
        this.activity = activity;
        this.imageView = imageView;
    }

    public void getRandomPhotoBasedOnQuery(String query) {
        Map<String, String> params = new HashMap<>();
        params.put("query", query);
        params.put("client_id", ACCESS_KEY);

        Log.d(TAG, "hashmap : " + params);

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
                Log.d(TAG, "connection successful (unsplash) randomPhotoUrl : " + randomPhotoUrl);
                loadImageFromGlide();
            }

            @Override
            public void onFailure(Call<RandomPhoto> call, Throwable t) {
                Log.d(TAG, "failed to fetch data from unsplash");
            }
        });
    }

    private void loadImageFromGlide(){
        Glide.with(activity)
                .load(randomPhotoUrl)
                .placeholder(R.drawable.android)//temporary placeholder for testing
                .into(imageView);
    }

}
