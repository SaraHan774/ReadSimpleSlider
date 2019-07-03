package com.gahee.rss_v1.unsplash;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Unsplash {

    String BASE_URL = "https://api.unsplash.com";
//https://api.unsplash.com/photos/?client_id=YOUR_ACCESS_KEY
    @GET("/photos/random/")
    Call<RandomPhoto> getRandomPhoto(@QueryMap Map<String, String> params);
}
