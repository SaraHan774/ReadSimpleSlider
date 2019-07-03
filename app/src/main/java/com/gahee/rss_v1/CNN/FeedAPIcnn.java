package com.gahee.rss_v1.CNN;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedAPIcnn {

    String BASE_URL = "http://rss.cnn.com/rss/";

    @GET("edition_technology.rss")
    Call<Feedcnn> getEditionTechnologyFeed();

    @GET("edition_space.rss")
    Call<Feedcnn> getEditionSpaceFeed();

    @GET("edition_world.rss")
    Call<Feedcnn> getEditionWorldFeed();

    @GET("edition_africa.rss")
    Call<Feedcnn> getEditionAfricaFeed();

    @GET("edition_americas.rss")
    Call<Feedcnn> getEditionAmericasFeed();

    @GET("edition_asia.rss")
    Call<Feedcnn> getEditionAsiaFeed();

    @GET("edition_europe.rss")
    Call<Feedcnn> getEditionEuropeFeed();

    @GET("edition_meast.rss")
    Call<Feedcnn> getEditionMiddleEastFeed();

    @GET("edition_us.rss")
    Call<Feedcnn> getEditionUSFeed();

    @GET("money_news_international.rss")
    Call<Feedcnn> getMoneyNewsInternationalFeed();

    @GET("edition_entertainment.rss")
    Call<Feedcnn> getEditionEntertainmentFeed();

    @GET("edition_sport.rss")
    Call<Feedcnn> getEditionSportFeed();

    @GET("edition_football.rss")
    Call<Feedcnn> getEditionFootballFeed();

    @GET("edition_golf.rss")
    Call<Feedcnn> getEditionGolfFeed();

    @GET("edition_motorsport.rss")
    Call<Feedcnn> getEditionMotorSportFeed();

    @GET("edition_tennis.rss")
    Call<Feedcnn> getEditionTennisFeed();

    @GET("edition_travel.rss")
    Call<Feedcnn> getEditionTravelFeed();

    @GET("cnn_freevideo.rss")
    Call<Feedcnn> getFreeVideoFeed();

    @GET("cnn_latest.rss")
    Call<Feedcnn> getCnnLatestFeed();


}
